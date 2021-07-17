package com.example.covitrack.activities

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.covitrack.R
import com.example.covitrack.databinding.ActivityCovidTestBinding

class CovidTestActivity : AppCompatActivity() {
    var count = 5
    lateinit var shareprefs:SharedPreferences
    lateinit var binding: ActivityCovidTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCovidTestBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        shareprefs = getSharedPreferences("covidTest", MODE_PRIVATE)
//        val alertDialog = AlertDialog.Builder(this)
//            .setTitle("Info")
//            .setMessage("Please give accurate answers, they will help you.")
//            .setPositiveButton("Ok",null)
//            .create()
//        alertDialog.show()

        binding.doneBtn.setOnClickListener(View.OnClickListener {
            result()
        })

        binding.card2.visibility = View.GONE
        binding.card3.visibility = View.GONE

        binding.radioGroup1.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId == binding.rg1b1.id){
                binding.card2.visibility = View.VISIBLE
                binding.card3.visibility = View.VISIBLE
                count--
            }
            else{
                binding.card2.visibility = View.GONE
                binding.card3.visibility = View.GONE
            }
        }

        binding.none1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) count-- else count++
        }
        binding.none2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) count-- else count++
        }
        binding.none3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) count-- else count++
        }
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            result()
        }
        binding.doneBtn.setOnClickListener(View.OnClickListener {
            finish()

        })

    }

    private fun result(){
        val editor = shareprefs.edit()
       if(count > 2){
           editor.putString("state","risk")
           binding.okChip.backgroundTintList = applicationContext.getColorStateList(R.color.danger)
           binding.okChip.text = "Your infection risk is high, please consult a doctor as soon as possible and follow the covid guidelines"
        }
        else{
            editor.putString("state","safe")
           binding.okChip.backgroundTintList = applicationContext.getColorStateList(R.color.success)
           binding.okChip.text = "Your infection risk is low. We recommend you to stay at home and follow the covid guidelines."
       }
        editor.apply()
    }
}