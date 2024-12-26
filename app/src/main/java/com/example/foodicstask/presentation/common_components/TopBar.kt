package com.example.foodicstask.presentation.common_components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodicstask.R
import com.example.foodicstask.presentation.theme.FoodicsTaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        modifier = Modifier.padding(end = 16.dp),
        title = {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        actions = {
                IconWithText(
                    iconRes = R.drawable.ic_tables,
                    text = "03"
                )

                Spacer(modifier = Modifier.width(16.dp))

                IconWithText(
                    iconRes = R.drawable.ic_profile,
                    text = "02"
                )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    FoodicsTaskTheme {
        TopBar()
    }
}