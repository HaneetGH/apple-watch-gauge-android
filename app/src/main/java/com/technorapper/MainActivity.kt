/*******************************************************************************
 * Copyright 2018 Evstafiev Konstantin

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/

package com.technorapper

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.technorapper.gauge.Range


import com.technorapper.ui.main.MainFragment
import kotlinx.android.synthetic.main.fragment_multi_gauge.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        /*if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }*/


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
        multiGauge.setFirstString("x")
        multiGauge.addSecondRange(range2)
        multiGauge.addSecondRange(rangeGrad2)
        multiGauge.secondString = "y";
        multiGauge.addThirdRange(range3)
        multiGauge.addThirdRange(rangeGrad3)
        multiGauge.threeString = "z";
        multiGauge.addForthRange(range4)
        multiGauge.addForthRange(rangeGrad4)
        multiGauge.fourString = "zz";

    }

}
