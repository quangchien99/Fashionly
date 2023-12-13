package chn.phm.presentation.base.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import chn.phm.presentation.R
import coil.compose.rememberAsyncImagePainter

@Composable
fun FashionlyImage(
    title: String,
    image: MutableState<Uri?>,
    defaultImageId: Int,
    onImageClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .aspectRatio(9f / 16f)
            .clip(RoundedCornerShape(12.dp))
            .background(if (image.value == null) Color.LightGray else Color.Transparent)
            .clickable(onClick = onImageClick),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if (image.value != null) {
                rememberAsyncImagePainter(model = image.value)
            } else {
                painterResource(id = defaultImageId)
            },
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.home_content_desc_select_image),
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        startY = 300f
                    )
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
