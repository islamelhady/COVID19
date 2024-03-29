package com.elhady.covid19.ui.global

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elhady.covid19.R
import com.elhady.covid19.data.model.Timeline
import com.elhady.covid19.databinding.ItemChartBinding
import java.text.SimpleDateFormat
import java.util.*


class ChartAdapter : ListAdapter<Timeline, ChartAdapter.ChartViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ChartViewHolder(
        ItemChartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ChartViewHolder, position: Int) =
        holder.bind(getItem(position))


    class ChartViewHolder(private val binding: ItemChartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val mFormat = SimpleDateFormat("MM/dd/yy", Locale.ENGLISH)

        fun bind(timeline: Timeline) {
//            val entriesCases = ArrayList<Entry>()
//            val entriesDeaths = ArrayList<Entry>()
//            val entriesRecoveries = ArrayList<Entry>()

//            timeline.cases?.entries?.forEach {
//                val date = mFormat.parse(it.key)
//                entriesCases.add(Entry(date.time.toFloat(), it.value.toFloat()))
//            }

//            timeline.deaths?.entries?.forEach {
//                val date = mFormat.parse(it.key)
//                entriesDeaths.add(Entry(date.time.toFloat(), it.value.toFloat()))
//            }

//            timeline.recovered?.entries?.forEach {
//                val date = mFormat.parse(it.key)
//                entriesRecoveries.add(Entry(date.time.toFloat(), it.value.toFloat()))
//            }

//            setupCombinedChart(
//                binding.lineChartCombined,
//                entriesCases,
//                entriesDeaths,
//                entriesRecoveries
//            )
//            setupChart(
//                binding.lineChartCases,
//                entriesCases,
//                "Covid-19 Cases",
//                R.color.dark_red,
//                R.color.light_red
//            )
//            setupChart(
//                binding.lineChartDeaths,
//                entriesDeaths,
//                "Covid-19 Deaths",
//                R.color.dark_yellow,
//                R.color.light_yellow
//            )
//            setupChart(
//                binding.lineChartRecoveries,
//                entriesRecoveries,
//                "Covid-19 Recoveries",
//                R.color.dark_green,
//                R.color.light_green
//            )

//            val adRequestOne = AdRequest.Builder().build()
//            binding.adViewOne.loadAd(adRequestOne)
//
//            val adRequestTwo = AdRequest.Builder().build()
//            binding.adViewTwo.loadAd(adRequestTwo)
//
//            val adRequestThree = AdRequest.Builder().build()
//            binding.adViewThree.loadAd(adRequestThree)
//
//            val adRequestFour = AdRequest.Builder().build()
//            binding.adViewFour.loadAd(adRequestFour)

        }

//        private fun setupCombinedChart(
//            chart: LineChart,
//            entriesCases: ArrayList<Entry>,
//            entriesDeaths: ArrayList<Entry>,
//            entriesRecoveries: ArrayList<Entry>
//        ) {
//            val mFormatPretty = SimpleDateFormat("dd MMM", Locale.ENGLISH)
//
//            //Part1
//            val data1 = LineDataSet(entriesCases, "Covid-19 Cases")
//            val data2 = LineDataSet(entriesDeaths, "Covid-19 Deaths")
//            val data3 = LineDataSet(entriesRecoveries, "Covid-19 Recoveries")
//
//            //Part2
//            data1.setDrawValues(false)
//            data1.setDrawCircles(false)
//            data1.lineWidth = 3f
//            data1.color = chart.context.resources.getColor(R.color.dark_red)
//            data1.highLightColor = chart.context.resources.getColor(R.color.light_yellow)
//
//            data2.setDrawValues(false)
//            data2.setDrawCircles(false)
//            data2.lineWidth = 3f
//            data2.color = chart.context.resources.getColor(R.color.dark_yellow)
//            data2.highLightColor = chart.context.resources.getColor(R.color.light_yellow)
//
//            data3.setDrawValues(false)
//            data3.setDrawCircles(false)
//            data3.lineWidth = 3f
//            data3.color = chart.context.resources.getColor(R.color.dark_green)
//            data3.highLightColor = chart.context.resources.getColor(R.color.light_yellow)
//
//            //Part3
//            chart.xAxis.labelRotationAngle = 0f
//            chart.xAxis.granularity = 20000f
//            chart.xAxis.setDrawGridLines(false)
//            chart.xAxis.valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    return mFormatPretty.format(Date(value.toLong()))
//                }
//            }
//
//            val leftAxis = chart.axisLeft
//            leftAxis.gridLineWidth = 1f
//            leftAxis.axisLineWidth = 1f
//            leftAxis.textColor = chart.context.resources.getColor(R.color.textColor)
//
//            //Part4
//            chart.data = LineData(listOf(data1, data2, data3))
//
//            //Part7
//            chart.axisRight.isEnabled = false
//            chart.xAxis.axisMaximum = entriesCases.last().x + 2000f
//            chart.xAxis.axisMinimum = entriesCases.map { it.x }.min() ?: 0f
//            chart.description.isEnabled = false
//            chart.xAxis.textColor = chart.context.resources.getColor(R.color.textColor)
//            chart.legend.textColor = chart.context.resources.getColor(R.color.textColorSecondary)
//
//            //Part8
//            chart.setTouchEnabled(true)
//            chart.setPinchZoom(true)
//
//            //Part9
//            chart.description.text = "Days"
//            chart.setNoDataText("No cases yet!")
//
//            //Part10
//            binding.lineChartCases.animateX(1500, Easing.EaseInExpo)
//
//            //Part11
//            val markerView = CustomMarker(binding.root.context, R.layout.marker_view)
//            chart.marker = markerView
//        }


//        private fun setupChart(
//            chart: LineChart,
//            entries: ArrayList<Entry>,
//            label: String,
//            color: Int,
//            fillColor: Int
//        ) {
//            val mFormatPretty = SimpleDateFormat("dd MMM", Locale.ENGLISH)
//
//            //Part1
//            val data = LineDataSet(entries, label)
//
//            //Part2
//            data.setDrawValues(false)
//            data.setDrawFilled(true)
//            data.setDrawCircles(false)
//            data.lineWidth = 3f
//            data.color = chart.context.resources.getColor(color)
//            data.fillColor = chart.context.resources.getColor(fillColor)
//            data.highLightColor = chart.context.resources.getColor(R.color.light_yellow)
//
//            //Part3
//            chart.xAxis.labelRotationAngle = 0f
//            chart.xAxis.granularity = 20000f
//            chart.xAxis.setDrawGridLines(false)
//            chart.xAxis.valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    return mFormatPretty.format(Date(value.toLong()))
//                }
//            }
//
//            val leftAxis = chart.axisLeft
//            leftAxis.gridLineWidth = 1f
//            leftAxis.axisLineWidth = 1f
//            leftAxis.textColor = chart.context.resources.getColor(R.color.textColor)
//
//            //Part4
//            chart.data = LineData(data)
//
//            //Part7
//            chart.axisRight.isEnabled = false
//            chart.xAxis.axisMaximum = entries.last().x + 2000f
//            chart.xAxis.axisMinimum = entries.map { it.x }.min() ?: 0f
//            chart.description.isEnabled = false
//            chart.xAxis.textColor = chart.context.resources.getColor(R.color.textColor)
//            chart.legend.textColor = chart.context.resources.getColor(R.color.textColorSecondary)
//
//            //Part8
//            chart.setTouchEnabled(true)
//            chart.setPinchZoom(true)
//
//            //Part9
//            chart.description.text = "Days"
//            chart.setNoDataText("No cases yet!")
//
//            //Part10
//            binding.lineChartCombined.animateX(1500, Easing.EaseInExpo)
//            binding.lineChartCases.animateX(1500, Easing.EaseInExpo)
//            binding.lineChartDeaths.animateX(1500, Easing.EaseInExpo)
//            binding.lineChartRecoveries.animateX(1500, Easing.EaseInExpo)
//
//            //Part11
//            val markerView = CustomMarker(binding.root.context, R.layout.marker_view)
//            chart.marker = markerView
//        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Timeline>() {
            override fun areItemsTheSame(oldItem: Timeline, newItem: Timeline): Boolean =
                oldItem.cases == newItem.cases

            override fun areContentsTheSame(oldItem: Timeline, newItem: Timeline): Boolean =
                oldItem == newItem

        }
    }
}