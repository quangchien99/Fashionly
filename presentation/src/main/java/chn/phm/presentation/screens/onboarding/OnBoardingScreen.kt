package chn.phm.presentation.screens.onboarding

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import chn.phm.domain.model.onboarding.OnBoardingData
import chn.phm.presentation.R
import chn.phm.presentation.base.navigation.Screen
import chn.phm.presentation.base.theme.Grey300
import chn.phm.presentation.base.theme.Grey900
import chn.phm.presentation.base.theme.RedLight
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

const val TOTAL_NUMBER_OF_PAGES = 3

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .background(color = Color.White)
) {

    val viewModel: OnBoardingViewModel = hiltViewModel()

    val pagerState = rememberPagerState() {
        TOTAL_NUMBER_OF_PAGES
    }

    var navigateNext by remember { mutableStateOf(false) }

    val items = getOnBoardingData()

    if (navigateNext) {
        nextPage(pagerState)
        navigateNext = false // Reset the state after handling
    }

    Box(
        modifier = modifier.fillMaxHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top
            ) { page ->
                Column(
                    modifier = Modifier
                        .padding(60.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OnBoardingImage(
                        modifier = Modifier
                            .size(200.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                        items[page].image
                    )
                    Text(
                        text = items[page].title,
                        modifier = Modifier.padding(top = 50.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineSmall,
                    )

                    Text(
                        text = items[page].desc,
                        modifier = Modifier.padding(top = 30.dp, start = 20.dp, end = 20.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        PagerIndicator(
            modifier = Modifier.align(Alignment.BottomCenter),
            size = TOTAL_NUMBER_OF_PAGES,
            currentPage = pagerState.currentPage
        )

        Box(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            BottomSection(
                currentPager = pagerState.currentPage,
                onFinished = {
                    viewModel.markFirstTimeOpened()
                    navHostController.navigate(Screen.HomeScreen.route)
                },
                onNext = {
                    navigateNext = true
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun nextPage(pagerState: PagerState) {
    LaunchedEffect(key1 = pagerState) {
        pagerState.scrollToPage(pagerState.currentPage + 1)
    }
}

@Composable
fun OnBoardingImage(modifier: Modifier, image: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    size: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(bottom = 120.dp)
    ) {
        repeat(size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(targetValue = if (isSelected) 25.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(1.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) RedLight else Grey300.copy(alpha = 0.5f)
            )
    )
}

@Composable
fun BottomSection(
    currentPager: Int,
    onFinished: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .fillMaxWidth(),
        horizontalArrangement = if (currentPager != 2) Arrangement.SpaceBetween else Arrangement.Center
    ) {
        if (currentPager == TOTAL_NUMBER_OF_PAGES - 1) {
            OutlinedButton(
                onClick = onFinished,
                shape = RoundedCornerShape(50),
            ) {
                Text(
                    text = stringResource(id = R.string.on_boarding_get_started),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 40.dp),
                    color = Grey900
                )
            }
        } else {
            SkipNextButton(
                text = stringResource(id = R.string.on_boarding_skip),
                modifier = Modifier.padding(start = 20.dp),
                onClick = onFinished
            )
            SkipNextButton(
                text = stringResource(id = R.string.on_boarding_next),
                modifier = Modifier.padding(end = 20.dp),
                onClick = {
                    onNext.invoke()
                }
            )
        }
    }
}

@Composable
fun SkipNextButton(text: String, modifier: Modifier, onClick: () -> Unit) {
    Text(
        text = text,
        color = Color.Black,
        modifier = modifier.clickable {
            onClick.invoke()
        },
        fontSize = 18.sp,
        style = MaterialTheme.typography.bodyLarge,
        fontWeight = FontWeight.Medium
    )
}

@Composable
private fun getOnBoardingData() = listOf(
    OnBoardingData(
        R.raw.onboarding_1st,
        stringResource(id = R.string.on_boarding_screen_1st_title),
        stringResource(id = R.string.on_boarding_screen_1st_description)
    ),
    OnBoardingData(
        R.raw.onboarding_2nd,
        stringResource(id = R.string.on_boarding_screen_2nd_title),
        stringResource(id = R.string.on_boarding_screen_2nd_description)
    ),
    OnBoardingData(
        R.raw.onboarding_3rd,
        stringResource(id = R.string.on_boarding_screen_3rd_title),
        stringResource(id = R.string.on_boarding_screen_3rd_description)
    )
)
