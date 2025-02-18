package com.example.notas

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnboardingScreen(navController: NavController) {
    // Lista de items de onboarding
    val items = listOf(
        OnboardingItem(
            R.drawable.boarding1,
            stringResource(id = R.string.on_boarding1_title),
            stringResource(id = R.string.on_boarding1_body)
        ),
        OnboardingItem(
            R.drawable.boarding2,
            stringResource(id = R.string.on_boarding2_title),
            stringResource(id = R.string.on_boarding2_body)
        ),
        OnboardingItem(
            R.drawable.boarding3,
            stringResource(id = R.string.on_boarding3_title),
            stringResource(id = R.string.on_boarding3_body)
        )
    )

    val pagerState = rememberPagerState { items.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Carrusel de páginas
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPage(item = items[page])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))

            PageIndicator(
                currentPage = pagerState.currentPage,
                totalPages = items.size,
                modifier = Modifier.weight(1f)
            )

            TextButton(
                onClick = {
                    if (pagerState.currentPage < items.size - 1) {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {

                        navController.navigate("login") {
                            popUpTo("onboarding") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = if (pagerState.currentPage == items.size - 1) "Finalizar" else "Siguiente",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
fun PageIndicator(
    currentPage: Int,
    totalPages: Int,
    modifier: Modifier = Modifier,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unselectedColor: Color = Color.Gray.copy(alpha = 0.5f)
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.Center) {
        repeat(totalPages) { index ->
            val size by animateDpAsState(
                targetValue = if (index == currentPage) 12.dp else 8.dp
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(
                        color = if (index == currentPage) selectedColor else unselectedColor
                    )
            )
        }
    }
}

@Composable
fun OnboardingPage(item: OnboardingItem) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = "Onboarding Image",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = item.title,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = item.description,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
    }
}

data class OnboardingItem(
    val imageRes: Int,
    val title: String,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}



