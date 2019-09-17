
package com.technorapper.ui.gauge


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.technorapper.R
import com.technorapper.gauge.Range


import kotlinx.android.synthetic.main.fragment_full_gauge.*


class FullGauge : Fragment() {


    companion object {
        fun newInstance() = FullGauge()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_gauge, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val range = Range()
        range.color = Color.parseColor("#1a1a1a")
        range.from = 0.0
        range.to = 50.0

        val range2 = Range()
        range2.color = Color.parseColor("#1a1a1a")
        range2.from = 50.0
        range2.to = 100.0

        val range3 = Range()
        range3.color = Color.parseColor("#1a1a1a")
        range3.from = 100.0
        range3.to = 150.0

        val range4 = Range()
        range3.color = Color.parseColor("#1a1a1a")
        range3.from = 100.0
        range3.to = 150.0

        fullGauge.minValue = 0.0
        fullGauge.maxValue = 150.0
        fullGauge.value = 35.0


        fullGauge.addRange(range)
        fullGauge.addRange(range2)
        fullGauge.addRange(range3)
        fullGauge.addRange(range4)


        fullGauge.isUseRangeBGColor = false
        fullGauge.isDisplayValuePoint = false




    }


}
