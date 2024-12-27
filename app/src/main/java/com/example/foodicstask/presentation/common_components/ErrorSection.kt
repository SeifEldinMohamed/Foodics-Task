package com.example.foodicstask.presentation.common_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.foodicstask.R
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.model.CustomDatabaseExceptionUiModel
import com.example.foodicstask.presentation.theme.LightGray
import com.example.foodicstask.presentation.theme.LightGreen
import com.example.foodicstask.presentation.utils.getErrorMessage

@Composable
fun ErrorSection(
    onRefreshButtonClicked: () -> Unit,
    customApiErrorExceptionUiModel: CustomApiExceptionUiModel? = null,
    customDatabaseExceptionUiModel: CustomDatabaseExceptionUiModel? = null
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error_animation))
    val errorMessage =  getErrorMessage(
        apiError = customApiErrorExceptionUiModel,
        databaseError = customDatabaseExceptionUiModel
    )
    Column(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth()
                .height(340.dp)
        )

        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(bottom = 10.dp)
        )

        Text(
            text = errorMessage ?: stringResource(id = R.string.unknown_exception_message),
            style = MaterialTheme.typography.bodyLarge,
            color = LightGray,
        )

        Spacer(modifier = Modifier.height(80.dp))

        OutlinedButton(
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent),
            border = BorderStroke(2.dp, LightGreen),
            modifier = Modifier
                .testTag("RetryButton")
                .fillMaxWidth(0.8f),
            onClick = {
                onRefreshButtonClicked()
            }
        ) {
            Text(
                text = stringResource(id = R.string.retry),
                fontSize = 18.sp,
                color = LightGreen,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewNoInternetConnection() {
    ErrorSection(
        onRefreshButtonClicked = {},
        customApiErrorExceptionUiModel = CustomApiExceptionUiModel.Network
    )
}

