package chn.phm.presentation.screens.history.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import chn.phm.domain.model.fashionly.FashionlyResultDomain
import chn.phm.presentation.base.theme.BackgroundLight
import coil.compose.rememberAsyncImagePainter

@Composable
fun HistoryCard(fashionlyResult: FashionlyResultDomain) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .background(color = BackgroundLight)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row {
            HistoryImage(
                imageSource = fashionlyResult.resultUrl,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                HistoryImage(
                    imageSource = fashionlyResult.modelImageUri,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
                HistoryImage(
                    imageSource = fashionlyResult.clothImageUri,
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun HistoryImage(
    modifier: Modifier = Modifier,
    imageSource: String
) {

    val painter = if (imageSource.startsWith("http://") || imageSource.startsWith("https://")) {
        // If the source is a URL, use the rememberAsyncImagePainter
        rememberAsyncImagePainter(imageSource)
    } else {
        // If the source is a URI, use the rememberImagePainter from the Coil library
        rememberAsyncImagePainter(Uri.parse(imageSource))
    }

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
    )
}
