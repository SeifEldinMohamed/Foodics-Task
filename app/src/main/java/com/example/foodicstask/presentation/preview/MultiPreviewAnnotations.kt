package com.example.foodicstask.presentation.preview

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "PIXEL_3", device = Devices.PIXEL_3, showSystemUi = true)
@Preview(name = "PIXEL_4", device = Devices.PIXEL_4, showSystemUi = true)
@Preview(name = "Tablet", device = Devices.TABLET, showSystemUi = true)
@Preview(name = "Unfolded Foldable", device = Devices.FOLDABLE, showSystemUi = true)
annotation class DevicesPreviews

