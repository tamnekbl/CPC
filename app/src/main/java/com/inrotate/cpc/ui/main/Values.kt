package com.inrotate.cpc.ui.main

import java.math.BigDecimal

data class CropValues(
    val superLow: ValueUnit = ValueUnit(),
    val low: ValueUnit = ValueUnit(),
    val medium: ValueUnit = ValueUnit(),
    val high: ValueUnit = ValueUnit(),
    val coast1: BigDecimal = BigDecimal.ZERO,
    val coast2: BigDecimal = BigDecimal.ZERO
) {
    data class ValueUnit(
        val lowerLimit: BigDecimal = BigDecimal.ZERO,
        val percent: BigDecimal = BigDecimal.ZERO
    )

    fun getPercentage(value: BigDecimal): BigDecimal {
        return when {
            value >= superLow.lowerLimit && value < low.lowerLimit -> superLow.percent.divide(BigDecimal(2))
            value >= low.lowerLimit && value < medium.lowerLimit -> low.percent.divide(BigDecimal(2))
            value >= medium.lowerLimit && value < high.lowerLimit -> medium.percent.divide(BigDecimal(2))
            value >= high.lowerLimit -> high.percent.divide(BigDecimal(2))
            else -> BigDecimal.ZERO
        }
    }
}


val crops = mapOf(
    "corn" to CropValues(
        superLow = CropValues.ValueUnit(BigDecimal("0.0"), BigDecimal("0.0")),
        low = CropValues.ValueUnit(BigDecimal("3.5"), BigDecimal("4.6")),
        medium = CropValues.ValueUnit(BigDecimal("6.3"), BigDecimal("6.8")),
        high = CropValues.ValueUnit(BigDecimal("8.6"), BigDecimal("10.0")),
        coast1 = BigDecimal(15),
        coast2 = BigDecimal(18)
    ),
    "soybeans" to CropValues(
        superLow = CropValues.ValueUnit(BigDecimal("0.0"), BigDecimal("0.0")),
        low = CropValues.ValueUnit(BigDecimal("2.0"), BigDecimal("4.5")),
        medium = CropValues.ValueUnit(BigDecimal("3.1"), BigDecimal("6.4")),
        high = CropValues.ValueUnit(BigDecimal("4.9"), BigDecimal("9.1")),
        coast1 = BigDecimal(35),
        coast2 = BigDecimal(60)
    ),
    "sunflower" to CropValues(
        superLow = CropValues.ValueUnit(BigDecimal("0.0"), BigDecimal("0.0")),
        low = CropValues.ValueUnit(BigDecimal("1.0"), BigDecimal("4.6")),
        medium = CropValues.ValueUnit(BigDecimal("1.7"), BigDecimal("6.7")),
        high = CropValues.ValueUnit(BigDecimal("2.9"), BigDecimal("9.5")),
        coast1 = BigDecimal(47),
        coast2 = BigDecimal(85)
    )
)