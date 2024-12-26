package com.example.foodicstask.presentation.common_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

/**
 * Similar to [AsyncImage] but displays a placeholder image on top of actual image during load.
 *
 * for a regular `AsyncImage` is that the placeholder image will be scaled to the same size as the
 * actual image. If the placeholder bitmap is smaller than the actual image, a regular `AsyncImage`
 * would not scale the placeholder and the user notices a disruptive change of image size.
 *
 */
@Composable
fun LoadableAsyncImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
    placeholderResId: Int? = null,
    placeholderContentScale: ContentScale = ContentScale.Inside,
) {
    var isLoading by rememberSaveable(model) { mutableStateOf(true) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = model,
            contentDescription = contentDescription,
            contentScale = contentScale,
            onSuccess = { isLoading = false },
        )

        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            if (placeholderResId != null) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = placeholderResId),
                    contentDescription = contentDescription,
                    contentScale = placeholderContentScale,
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant)
                )
            } else {
                CircularProgressIndicator()
            }
        }
    }
}
