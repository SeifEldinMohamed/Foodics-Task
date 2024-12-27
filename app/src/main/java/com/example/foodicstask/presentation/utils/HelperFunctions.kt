package com.example.foodicstask.presentation.utils

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.foodicstask.R
import com.example.foodicstask.presentation.model.CustomApiExceptionUiModel
import com.example.foodicstask.presentation.model.CustomDatabaseExceptionUiModel

@Composable
fun getErrorMessage(
    apiError: CustomApiExceptionUiModel?,
    databaseError: CustomDatabaseExceptionUiModel?
): String? {
    val apiErrorMessage = apiError?.let {
        when (it) {
            is CustomApiExceptionUiModel.Timeout -> stringResource(R.string.timeout_exception_message)
            is CustomApiExceptionUiModel.Network -> stringResource(R.string.no_internet_connection_exception_message)
            is CustomApiExceptionUiModel.ServiceUnreachable -> stringResource(R.string.service_unreachable_exception_message)
            is CustomApiExceptionUiModel.BadRequest -> stringResource(R.string.bad_request_exception_message)
            is CustomApiExceptionUiModel.NotFound -> stringResource(R.string.not_found_exception_message)
            is CustomApiExceptionUiModel.ServerError -> stringResource(R.string.server_exception_message)
            is CustomApiExceptionUiModel.Unauthorized -> stringResource(R.string.unauthorized_exception_message)
            is CustomApiExceptionUiModel.Unknown -> stringResource(R.string.unknown_exception_message)
        }
    }

    val databaseErrorMessage = databaseError?.let {
        when (it) {
            is CustomDatabaseExceptionUiModel.DatabaseError -> stringResource(R.string.database_exception_message)
            is CustomDatabaseExceptionUiModel.Unknown -> stringResource(R.string.unknown_exception_message)
        }
    }

    return apiErrorMessage ?: databaseErrorMessage
}

@Composable
fun prepareShimmerBrush():Brush{
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 700,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )
    return brush
}