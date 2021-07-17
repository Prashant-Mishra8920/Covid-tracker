package com.example.covitrack.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covitrack.R
import com.example.covitrack.adapter.VaccineSessionAdapter
import com.example.covitrack.databinding.ActivityVaccinationBinding
import com.example.covitrack.fragments.FindByDistrictFragment
import com.example.covitrack.fragments.FindByPinFragment
import com.example.covitrack.model.SessionsItem
import com.example.covitrack.repository.Repository
import com.example.covitrack.viewModel.CoviViewModel
import com.example.covitrack.viewModel.ViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.util.*

class VaccinationActivity : AppCompatActivity() {

    lateinit var binding: ActivityVaccinationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVaccinationBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.vaccinationFrame,FindByPinFragment())
//            .commit()
//
//        binding.findByPinCodeBtn.setOnClickListener(this)
//        binding.findByDistrictBtn.setOnClickListener(this)

        binding.errorText.visibility = View.GONE
        binding.vaccinationRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = VaccineSessionAdapter(this)
        binding.vaccinationRecyclerView.adapter = adapter

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val dateBuilder = MaterialDatePicker.Builder.datePicker()
        dateBuilder.setTitleText("Select Date")
        val startPicker = dateBuilder.build()
        var date = ""

        binding.datePickerBtn.setOnClickListener(View.OnClickListener {
            startPicker.show(supportFragmentManager,"Date_Picker")
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
                viewModel.vaccineResponse.observe(this, androidx.lifecycle.Observer {
                    if(it.sessions?.isEmpty()!!){
                        binding.errorText.visibility = View.VISIBLE
                        binding.viewImage.visibility = View.VISIBLE
                        adapter.updateList(it.sessions as List<SessionsItem>)
                    }
                    else{
                        binding.errorText.visibility = View.GONE
                        binding.viewImage.visibility = View.GONE
                        adapter.updateList(it.sessions as List<SessionsItem>)
                    }
                })
            }
            else{
                binding.pinCode.error = "Invalid Input"
            }
        })

    }
}