


package com.technorapper.ui.gauge




import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.technorapper.R
import com.technorapper.gauge.Range

import kotlinx.android.synthetic.main.fragment_multi_gauge.*


class MultiGaugeFragment : Fragment() {

    companion object {
        fun newInstance() = MultiGaugeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_gauge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val range = Range()
        range.color = Color.parseColor("#5e8fe1")
        range.from = 0.0
        range.to = 50.0


        val rangeGrad = Range()
        rangeGrad.color = Color.parseColor("#57c1e8")
        rangeGrad.from = 0.0
        rangeGrad.to = 50.0


////////////////*********************2********************************////////////////

        val range2 = Range()
        range2.color = Color.parseColor("#359240")
        range2.from = 50.0
        range2.to = 100.0


        val rangeGrad2 = Range()
        rangeGrad2.color = Color.parseColor("#6cc04a")
        rangeGrad2.from = 0.0
        rangeGrad2.to = 50.0


////////////////*********************3********************************////////////////

        val range3 = Range()
        range3.color = Color.parseColor("#a20a18")
        range3.from = 100.0
        range3.to = 150.0


        val rangeGrad3 = Range()
        rangeGrad3.color = Color.parseColor("#db000a")
        rangeGrad3.from = 0.0
        rangeGrad3.to = 50.0


////////////////*********************4********************************////////////////

        val range4 = Range()
        range4.color = Color.parseColor("#ff8939")
        range4.from = 0.0
        range4.to = 50.0


        val rangeGrad4 = Range()
        rangeGrad4.color = Color.parseColor("#fca800")
        rangeGrad4.from = 0.0
        rangeGrad4.to = 50.0


        multiGauge.minValue = 0.0
        multiGauge.maxValue = 150.0
        multiGauge.value = 35.0

        multiGauge.secondMinValue = 0.0
        multiGauge.secondMaxValue = 150.0
        multiGauge.secondValue = 75.0

        multiGauge.thirdMinValue = 0.0
        multiGauge.thirdMaxValue = 150.0
        multiGauge.thirdValue = 100.0

        multiGauge.forthMinValue = 0.0
        multiGauge.forthMaxValue = 150.0
        multiGauge.forthValue = 100.0

        multiGauge.isDisplayValuePoint = false
        multiGauge.isUseRangeBGColor = false




        multiGauge.addRange(range)
        multiGauge.addRange(rangeGrad)

        multiGauge.addSecondRange(range2)
        multiGauge.addSecondRange(rangeGrad2)

        multiGauge.addThirdRange(range3)
        multiGauge.addThirdRange(rangeGrad3)

        multiGauge.addForthRange(range4)
        multiGauge.addForthRange(rangeGrad4)
    }





}
