package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                    Lemonade()
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    Scaffold(topBar = {
        LemonadeTopBar(
            onRestart = { result = 1 }
        )
    }
    ) {
            innerPadding ->
        Img(
            modifier = modifier.padding(innerPadding),
            result = result,
            onResultChange = { result = it }
        )
    }
}

@Composable
fun Img(
    modifier: Modifier = Modifier,
    result: Int,
    onResultChange: (Int) -> Unit
) {
    val img = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val t = when (result) {
        1 -> R.string.first
        2 -> R.string.second
        3 -> R.string.third
        else -> R.string.fourth
    }

    val progress = result / 4f

    Column(
        modifier = modifier.fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LinearProgressIndicator(
            progress = { progress },
            modifier = modifier.fillMaxWidth()
                .padding(bottom = 24.dp),
            color = Color(0xFFFFD54F)
        )

        Card(
            modifier = modifier.fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF8E1))
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(img),
                    contentDescription = stringResource(R.string.img1),
                    contentScale = ContentScale.Fit,
                    modifier = modifier.fillMaxWidth()
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(
                            3.dp,
                            Color(red = 105, green = 205, blue = 216),
                            RoundedCornerShape(16.dp)
                        )
                        .clickable {
                            when (result) {
                                1 -> onResultChange(2)
                                2 -> {
                                    val a = (0..3).random()
                                    if (a == 1) onResultChange(result + a)
                                }

                                4 -> onResultChange(1)
                                else -> onResultChange(result + 1)
                            }
                        }
                )

                Spacer(modifier = modifier.height(12.dp))

                Text(
                    text = stringResource(t),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeTopBar(onRestart: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "🍋 " + stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFFFFD54F)
        ),
        actions = {
            IconButton(onClick = onRestart) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Restart",
                    tint = Color.White
                )
            }
        }
    )
}

//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LemonadeTheme {
//        Lemonade()
//    }
//}