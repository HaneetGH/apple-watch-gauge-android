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


package com.ekn.gruzer.simplegaugeexample.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.ekn.gruzer.simplegaugeexample.ui.gauge.FullGauge

import com.ekn.gruzer.simplegaugeexample.ui.gauge.MultiGaugeFragment

class Adapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {

        return MultiGaugeFragment.newInstance()
    }

    override fun getCount(): Int {
        return 1
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "MultiGauge"

        }
        return "Title"
    }
}