package chn.phm.presentation.base.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import chn.phm.presentation.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoadingDialog() {
    Dialog(onDismissRequest = { /* Dialog cannot be dismissed */ }) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading)).value,
                modifier = Modifier.size(150.dp),
                iterations = LottieConstants.IterateForever
            )
        }
    }
}
