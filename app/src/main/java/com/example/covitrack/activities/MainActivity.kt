package com.example.covitrack.activities

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covitrack.R
import com.example.covitrack.databinding.ActivityMainBinding
import com.example.covitrack.repository.Repository
import com.example.covitrack.viewModel.CoviViewModel
import com.example.covitrack.viewModel.ViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var shareprefs:SharedPreferences
    var arrayOfCases:ArrayList<Entry> = arrayListOf()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        shareprefs = getSharedPreferences("covidTest", MODE_PRIVATE)

        val state = shareprefs.getString("state","")

        if(state == "safe"){
            binding.covidTestCard.setCardBackgroundColor(getColorStateList(R.color.success))
        }
        else{
            binding.covidTestCard.setCardBackgroundColor(getColorStateList(R.color.danger))
        }
        val viewModelFactory = ViewModelFactory(Repository())
        val viewModel = ViewModelProvider(this,viewModelFactory).get(CoviViewModel::class.java)
        viewModel.getCovidCases()
        viewModel.covidResponse.observe(this, Observer {
            val formatter = DecimalFormat("##,##,##0")
            binding.dailyCases.text = formatter.format(it.casesTimeSeries?.last()?.dailyconfirmed?.toInt())
            binding.total.text = formatter.format(it.casesTimeSeries?.last()?.totalconfirmed?.toInt())
            binding.recovered.text = formatter.format(it.casesTimeSeries?.last()?.totalrecovered?.toInt())
            binding.deaths.text = formatter.format(it.casesTimeSeries?.last()?.totaldeceased?.toInt())
            var xA = 0f
            for(i in it.casesTimeSeries!!){
                arrayOfCases.add(Entry(xA,i?.dailyconfirmed?.toFloat()!!))
                xA += 1f
            }
            chart()
        })

        binding.takeTestButton.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, CovidTestActivity::class.java))
        })

        binding.vaccine.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, VaccinationActivity::class.java))
        })

    }

    private fun chart(){
        val lineChart: LineChart = binding.chart

        val dataSet = LineDataSet(arrayOfCases,"chart")
        dataSet.color = getColor(R.color.danger)
        dataSet.valueTextColor = Color.BLACK
        dataSet.valueTextSize = 14f
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        dataSet.cubicIntensity = 0f
        dataSet.highLightColor = getColor(R.color.red)
        dataSet.setDrawHorizontalHighlightIndicator(false)
        dataSet.setDrawVerticalHighlightIndicator(false)
        dataSet.setDrawCircles(false)

        dataSet.setDrawFilled(true)
        dataSet.fillDrawable = AppCompatResources.getDrawable(this,R.drawable.gradient)

        val lineData = LineData(dataSet)

        lineChart.data = lineData
        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
        lineChart.setTouchEnabled(true);
        lineChart.isClickable = false;
        lineChart.isDoubleTapToZoomEnabled = false;
        lineChart.isDoubleTapToZoomEnabled = false;

        lineChart.setDrawBorders(false);
        lineChart.setDrawGridBackground(false);

        lineChart.description.isEnabled = false;
        lineChart.legend.isEnabled = false;

        lineChart.axisLeft.setDrawGridLines(false);
        lineChart.axisLeft.setDrawLabels(false);
        lineChart.axisLeft.setDrawAxisLine(false);

        lineChart.xAxis.setDrawGridLines(false);
        lineChart.xAxis.setDrawLabels(false);
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.setDrawAxisLine(false);
        lineChart.axisRight.setDrawGridLines(false);
        lineChart.axisRight.setDrawLabels(false);
        lineChart.axisRight.setDrawAxisLine(false);
    }
}