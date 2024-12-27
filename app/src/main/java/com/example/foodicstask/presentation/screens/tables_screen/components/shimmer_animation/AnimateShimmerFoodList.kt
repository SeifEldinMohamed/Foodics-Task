package com.example.foodicstask.presentation.screens.tables_screen.components.shimmer_animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.presentation.utils.prepareShimmerBrush

@Composable
fun AnimateShimmerFoodList(
    gridCellsCount: Int
) {
    val brush = prepareShimmerBrush()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(gridCellsCount)
    ) {
        items(12) {
            FoodShimmerItem(brush = brush)
        }
    }

}

@Composable
private fun FoodShimmerItem(brush: Brush) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(brush)
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .height(18.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth(fraction = 0.6f)
                        .background(brush)

                )

                Spacer(modifier = Modifier.padding(6.dp))
                Spacer(
                    modifier = Modifier
                        .height(46.dp)
                        .padding(horizontal = 8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth()
                        .background(brush)
                        .align(Alignment.Start)
                )

                Spacer(modifier = Modifier.padding(6.dp))

                Spacer(
                    modifier = Modifier
                        .height(18.dp)
                        .padding(horizontal = 8.dp)
                        .clip(MaterialTheme.shapes.small)
                        .fillMaxWidth(fraction = 0.5f)
                        .background(brush)
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerItemPreview() {
    FoodShimmerItem(
        brush = Brush.linearGradient(
            listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f),
            )
        )
    )
}