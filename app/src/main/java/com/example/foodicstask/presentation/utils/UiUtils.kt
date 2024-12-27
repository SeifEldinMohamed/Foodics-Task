package com.example.foodicstask.presentation.utils

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun WindowSizeClass.getGridCellsCount(): Int {
    val gridCellsCountInPhones = 2
    val gridCellsCountInTablets = 4

    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> gridCellsCountInPhones
        false -> gridCellsCountInTablets
    }
}

@Composable
fun WindowSizeClass.getTopPadding(): Dp {
    return when (this.widthSizeClass == WindowWidthSizeClass.Compact) {
        true -> 16.dp
        false -> 0.dp
    }
}