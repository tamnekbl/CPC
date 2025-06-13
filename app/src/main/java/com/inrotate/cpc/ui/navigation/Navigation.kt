package com.inrotate.cpc.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.inrotate.cpc.R

sealed class NavRoutes(
    val title: String,
    val route: String,
) {
    data object Home : NavRoutes(title = "Кукуруза", route = "corn")
    data object Contacts : NavRoutes(title = "Соя", route = "soybeans")
    data object About : NavRoutes(title = "Подсолнечник", route = "sunflower")
}

data class BarItem(
    @StringRes
    val title: Int,
    @DrawableRes
    val image: Int,
    val route: String
)

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = R.string.corn,
            image = R.drawable.sweet_corn,
            route = "corn"
        ),
        BarItem(
            title = R.string.soybeans,
            image = R.drawable.soybean,
            route = "soybeans"
        ),
        BarItem(
            title = R.string.sunflower,
            image = R.drawable.sunflower1,
            route = "sunflower"
        )
    )
}