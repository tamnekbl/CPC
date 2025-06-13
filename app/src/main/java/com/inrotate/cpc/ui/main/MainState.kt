package com.inrotate.cpc.ui.main

import com.inrotate.cpc.R

data class MainState(
    val title: Int = R.string.app_name,
    val isSunflower: Boolean = false,
    val culture: CropValues = CropValues(),
    val kilosPerHectare: Double = 0.0,
    val tonsPer100Hectares: Double = 0.0,
    val rublesPer100Hectares15: Double = 0.0,
    val rublesPer100Hectares18: Double = 0.0
)
