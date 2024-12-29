package com.example.foodicstask.presentation.screens.tables_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.request.ImageRequest
import com.example.foodicstask.R
import com.example.foodicstask.presentation.common_components.LoadableAsyncImage
import com.example.foodicstask.presentation.screens.tables_screen.model.FoodItemUiModel
import com.example.foodicstask.presentation.screens.tables_screen.preview_data.fakeFoodItemUiModel
import com.example.foodicstask.presentation.theme.DarkRed
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@Composable
fun FoodItem(
    foodItemUiModel: FoodItemUiModel,
    quantityInCart: Int,
    onFoodCardClick: (FoodItemUiModel) -> Unit,
) {
    Box(
        modifier = Modifier.padding(12.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onFoodCardClick(foodItemUiModel)
                },
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LoadableAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(foodItemUiModel.image)
                        .crossfade(true)
                        .build(),
                    placeholderResId = R.drawable.ic_food_placeholder,
                    contentDescription = foodItemUiModel.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )

                Text(
                    text = foodItemUiModel.name,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = Bold),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = foodItemUiModel.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(R.string.price),
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Text(
                        text = stringResource(
                            R.string.price_with_SAR_currency,
                            foodItemUiModel.price
                        ),
                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = Bold),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }

        if (quantityInCart > 0)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 10.dp, y = (-8).dp)
                    .size(24.dp)
                    .background(
                        color = DarkRed,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = quantityInCart.toString(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                )
            }
    }
}


@Preview
@Composable
fun PreviewFoodCard() {
    FoodicsTaskTheme {
        FoodItem(
            foodItemUiModel = fakeFoodItemUiModel,
            quantityInCart = 1,
            onFoodCardClick = {}
        )
    }
}
