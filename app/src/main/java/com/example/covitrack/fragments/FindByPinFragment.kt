package com.example.covitrack.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covitrack.adapter.VaccineSessionAdapter
import com.example.covitrack.databinding.FindByPinFragmentBinding
import com.example.covitrack.model.SessionsItem
import com.example.covitrack.repository.Repository
import com.example.covitrack.viewModel.CoviViewModel
import com.example.covitrack.viewModel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.util.*

class FindByPinFragment: Fragment() {
    lateinit var binding: FindByPinFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FindByPinFragmentBinding.inflate(inflater,container,false)

        binding.errorText.visibility = View.GONE
        binding.vaccinationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = VaccineSessionAdapter(requireContext())
        binding.vaccinationRecyclerView.adapter = adapter

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val dateBuilder = MaterialDatePicker.Builder.datePicker()
        dateBuilder.setTitleText("Select Date")
        val startPicker = dateBuilder.build()
        var date = ""

        binding.datePickerBtn.setOnClickListener(View.OnClickListener {
            startPicker.show(parentFragmentManager,"Date_Picker")
        })

        startPicker.addOnPositiveButtonClickListener(MaterialPickerOnPositiveButtonClickListener {
            calendar.time = Date(it)
            date = "${calendar.get(Calendar.DAY_OF_MONTH)}/" +
                    "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
        })

        binding.getSessionsBtn.setOnClickListener(View.OnClickListener {
            if(binding.pinCode.text?.isNotEmpty()!! && date.isNotEmpty()){
                val pinCode:String = binding.pinCode.text.toString()
                val viewModelFactory = ViewModelFactory(Repository())
                val viewModel = ViewModelProvider(this,viewModelFactory).get(CoviViewModel::class.java)

                viewModel.getVaccineByPinCode(pinCode,date)
                viewModel.vaccineResponse.observe(viewLifecycleOwner, Observer {
                    if(it.sessions?.isEmpty()!!){
                        binding.errorText.visibility = View.VISIBLE
                        adapter.updateList(it.sessions as List<SessionsItem>)
                    }
                    else{
                        binding.errorText.visibility = View.GONE
                        adapter.updateList(it.sessions as List<SessionsItem>)
                    }
                })
            }
            else{
                binding.pinCode.error = "Invalid Input"
            }
        })
        return binding.root
    }
}