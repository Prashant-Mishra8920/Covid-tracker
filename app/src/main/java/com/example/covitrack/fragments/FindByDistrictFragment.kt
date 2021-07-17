package com.example.covitrack.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covitrack.adapter.VaccineSessionAdapter
import com.example.covitrack.databinding.FindByDistrictFragmentBinding
import com.example.covitrack.repository.Repository
import com.example.covitrack.viewModel.CoviViewModel
import com.example.covitrack.viewModel.ViewModelFactory

class FindByDistrictFragment:Fragment(),AdapterView.OnItemSelectedListener{
    private lateinit var state:String
    private var stateId:Int = 0
    private lateinit var array:ArrayList<String>
    lateinit var binding: FindByDistrictFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FindByDistrictFragmentBinding.inflate(inflater,container,false)

        array = arrayListOf("Select your State","Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh",
            "Assam", "Bihar", "Chandigarh", "Chhattisgarh", "Dadra and Nagar Haveli", "Daman and Diu", "Delhi", "Goa", "Gujarat", "Haryana",
            "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand", "Karnataka", "Kerala", "Ladakh", "Lakshadweep",
            "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Puducherry", "Punjab",
            "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
            R.layout.simple_spinner_item,array)
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.stateSpinner.adapter = arrayAdapter
        binding.stateSpinner.onItemSelectedListener = this
        arrayAdapter.notifyDataSetChanged()

        return binding.root
    }

    private fun getDistrict(){
        binding.findByDistrictRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = VaccineSessionAdapter(requireContext())
        binding.findByDistrictRecyclerView.adapter = adapter
//
        val viewModelFactory = ViewModelFactory(Repository())
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CoviViewModel::class.java)
        viewModel.getDistrict()
        viewModel.vaccineDistrictResponse.observe(viewLifecycleOwner, Observer {
//            val arr = arrayListOf<String>()
//            for(i in it.districts!!){
//                arr.add(i?.districtName!!)
//            }
//            val Adapter: ArrayAdapter<String> = ArrayAdapter<String>(requireContext(),
//                R.layout.simple_spinner_item,arr)
//            Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            binding.districtSpinner.adapter = Adapter
//            Log.d("District", "onCreateView: $it")
        })
    }

    private fun StateId(state:String):Int{
        val map = mapOf<String,Int>(
            "Andaman and Nicobar Islands" to 1,
            "Andhra Pradesh" to 2,
            "Arunachal Pradesh" to 3,
            "Assam" to 4,
            "Bihar" to 5,
            "Chandigarh" to 6,
            "Chhattisgarh" to 7,
            "Dadra and Nagar Haveli" to 8,
            "Delhi" to 9,
            "Goa" to 10,
            "Gujarat" to 11,
            "Haryana" to 12,
            "Himachal Pradesh" to 13,
            "Jammu and Kashmir" to 14,
            "Jharkhand" to 15,
            "Karnataka" to 16,
            "Kerala" to 17,
            "Ladakh" to 18,
            "Lakshadweep" to 19,
            "Madhya Pradesh" to 20,
            "Maharashtra" to 21,
            "Manipur" to 22,
            "Meghalaya" to 23,
            "Mizoram" to 24,
            "Nagaland" to 25,
            "Odisha" to 26,
            "Puducherry" to 27,
            "Punjab" to 28,
            "Rajasthan" to 29,
            "Sikkim" to 30,
            "Tamil Nadu" to 31,
            "Telangana" to 32,
            "Tripura" to 33,
            "Uttar Pradesh" to 34,
            "Uttarakhand" to 35,
            "West Bengal" to 36,
            "Daman and Diu" to 37
        )
        return map.getValue(state)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent?.id == binding.stateSpinner.id){
            state = parent.getItemAtPosition(position).toString()
            if(state != "Select your State"){
//                stateId = StateId(state)
//                Toast.makeText(requireContext(), stateId, Toast.LENGTH_SHORT).show()
            }
        }
        getDistrict()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}