package com.example.foodicstask.presentation.screens.tables_screen.components.shimmer_animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.utils.prepareShimmerBrush

@Composable
fun AnimateShimmerCategoriesBar() {
    val brush = prepareShimmerBrush()
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
        ,
    ) {
        items(8) {
            CategoryBarItemShimmer(brush)
        }
    }
}


@Composable
private fun CategoryBarItemShimmer(brush: Brush) {
    Spacer(
        modifier = Modifier
            .padding(end = 16.dp)
            .height(40.dp)
            .width(80.dp)
            .background(brush)
    )
}

@Composable
@Preview(showBackground = true)
fun CategoryBarItemShimmerPreview() {
    CategoryBarItemShimmer(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}