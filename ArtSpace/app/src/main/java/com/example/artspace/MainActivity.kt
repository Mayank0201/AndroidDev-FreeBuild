package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush.Companion.verticalGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Art()
            }
        }
    }
}

@Composable
fun Art(modifier: Modifier = Modifier, viewModel: ArtViewModel = viewModel()) {

    val result by viewModel.result.collectAsState()
    val configuration = LocalConfiguration.current
    val sHeight = configuration.screenHeightDp

    val img = when (result) {
        0 -> R.drawable.img_1
        1 -> R.drawable.img_2
        2 -> R.drawable.img_3
        else -> R.drawable.img_4
    }

    val title = when (result) {
        0 -> R.string.first_title
        1 -> R.string.second_title
        2 -> R.string.Third_title
        else -> R.string.Fourth_title
    }

    val name = when (result) {
        0 -> R.string.first_author
        1 -> R.string.second_author
        2 -> R.string.third_author
        else -> R.string.fourth_author
    }

    Column(
        modifier = modifier.fillMaxSize()
            .background(
                brush = verticalGradient(
                    listOf(Color(0xFFECE9E6), Color(0xFFFFFFFF))
                )
            ).padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Card(
            modifier = modifier.fillMaxWidth(0.9f)
                .height((sHeight * 0.45f).dp),
            shape = RoundedCornerShape(24.dp),
            elevation = cardElevation(8.dp)
        ) {
            Image(
                painter = painterResource(img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxSize()
            )
        }

        Spacer(modifier = modifier.height(24.dp))

        Column(
            modifier = modifier.fillMaxWidth()
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(title),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Text(
                text = stringResource(name),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }

        Spacer(modifier = modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { viewModel.prev() }, shape = RoundedCornerShape(50)) {
                Text("⬅ Previous")
            }
            Button(onClick = { viewModel.next() }, shape = RoundedCornerShape(50)) {
                Text("Next ➡")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.footer),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ArtSpaceTheme {
//        Art()
//    }
//}