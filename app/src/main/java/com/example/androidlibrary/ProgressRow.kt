package com.example.androidlibrary

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun ProgressRow() {

    val deviceWidth = LocalConfiguration.current.screenWidthDp

    val shimmerColors = listOf(
        Color.DarkGray.copy(alpha = 0.6f),
        Color.DarkGray.copy(alpha = 0.2f),
        Color.DarkGray.copy(alpha = 0.6f),
    )

    var isAnimated by remember {
        mutableStateOf(false)
    }
    var widthState by remember {
        mutableStateOf(0.dp)
    }
    val transition = rememberInfiniteTransition()


    val transLateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2500,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = transLateAnim.value, y = transLateAnim.value)
    )

    LaunchedEffect(key1 = widthState, key2 = isAnimated) {
        if (isAnimated) {
            delay(100)
            if (widthState < deviceWidth.dp) {
                widthState += 1.dp
            } else {
                widthState = 0.dp
            }
        }
    }

    val width = animateDpAsState(
        targetValue = widthState,
        animationSpec = tween(durationMillis = 100)
    )

    Column {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.height(50.dp))
            }
            Row(
                modifier = Modifier
                    .width(width = width.value)
                    .background(color = Color.LightGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(
                    modifier = Modifier
                        .height(50.dp)
                        .width(widthState)
                        .background(brush)
                )
            }
        }
        Button(
            onClick = { isAnimated = !isAnimated },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(text = "Play/paus")
        }
    }


}