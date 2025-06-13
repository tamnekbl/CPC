package com.inrotate.cpc.ui.main

import com.inrotate.cpc.R
import com.inrotate.cpc.ui.navigation.BarItem
import com.inrotate.cpc.ui.navigation.NavBarItems.BarItems
import com.inrotate.cpc.utils.ViewModelBase

import java.math.BigDecimal
import java.math.RoundingMode

class MainModel(
    private val route: String
) : ViewModelBase<MainState>() {
    private var kind: BarItem? = null
    private val isSunflower = route == "sunflower"

    init {
        kind = BarItems.find { it.route == route }
        _state.value = MainState(
            title = kind?.title ?: R.string.app_name,
            isSunflower = isSunflower
        )
    }

    fun calculate(value: String) {
        if (value.isEmpty())
            return
        val culture = crops[route]
        val floatValue = BigDecimal(value)
        val percentage = crops[route]?.getPercentage(floatValue)
            ?: BigDecimal.ZERO

        val kilosPerHectare = floatValue.multiply(percentage).multiply(BigDecimal("10"))
        val tonsPer100Hectares = floatValue.multiply(percentage)
        val rublesPer100Hectares15 = tonsPer100Hectares.multiply(culture?.coast1)
        val rublesPer100Hectares18 = tonsPer100Hectares.multiply(culture?.coast2)


        _state.value = state.value?.copy(
            kilosPerHectare = kilosPerHectare.setScale(2, RoundingMode.HALF_UP).toDouble(),
            tonsPer100Hectares = tonsPer100Hectares.setScale(2, RoundingMode.HALF_UP).toDouble(),
            rublesPer100Hectares15 = rublesPer100Hectares15.setScale(2, RoundingMode.HALF_UP).toDouble(),
            rublesPer100Hectares18  = rublesPer100Hectares18.setScale(2, RoundingMode.HALF_UP).toDouble(),
            culture = culture!!
        )
    }
}




