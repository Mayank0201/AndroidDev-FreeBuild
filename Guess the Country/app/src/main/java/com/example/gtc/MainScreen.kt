package com.example.gtc


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myunscrambled.R


@Composable
fun MainScreen(modifier: Modifier=Modifier) {

    val viewmodel: MainViewModel = viewModel()
    val uiState = viewmodel.uiState.value
    var guess by remember { mutableStateOf("") }
    val isDarkTheme = isSystemInDarkTheme()
    val boxBackground = if (isDarkTheme) Color.DarkGray else Color(0xFFE0F7FA)
    val boxBorderColor = if (isDarkTheme) Color.LightGray else Color.DarkGray

    Column(
        modifier= modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement =Arrangement.Center
    ) {
        Text(
            text= stringResource(R.string.app_name),
            style= MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier= modifier.height(24.dp))

        Box(
            modifier= modifier.fillMaxWidth()
                .border(width = 2.dp, color = boxBorderColor, shape = RoundedCornerShape(16.dp))
                .background(color = boxBackground, shape = RoundedCornerShape(16.dp))
                .padding(24.dp),
            contentAlignment= Alignment.Center
        ) {
            Column(
                horizontalAlignment= Alignment.CenterHorizontally,
                verticalArrangement= Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = boxBackground),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total Score: ${uiState.score}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "${uiState.wordCount}/10",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                Text(
                    text= uiState.scrambledWord,
                    style= MaterialTheme.typography.displaySmall
                )

                Text(
                    text= uiState.originalHint,
                    style= MaterialTheme.typography.bodyMedium
                )

                TextField(
                    value= guess,
                    onValueChange= { guess = it }
                )

                Text(
                    text = viewmodel.message,
                    style = MaterialTheme.typography.labelLarge
                )

                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        viewmodel.checkAnswer(guess)
                        guess = ""
                    }) {
                        Text(text = "Guess")
                    }

                    Button(onClick = {
                        viewmodel.skipWord()
                        guess = ""
                    }) {
                        Text(text = "Skip")
                    }
                }
            }
        }
    }


        if(uiState.isGameOver){
        EndScreen(viewModel=viewmodel)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndScreen(modifier: Modifier=Modifier,viewModel: MainViewModel=viewModel()){

    val uiState=viewModel.uiState.value

    BasicAlertDialog(onDismissRequest = {}) {
        Column(
            modifier= modifier.padding(24.dp),
            horizontalAlignment= Alignment.CenterHorizontally,
            verticalArrangement= Arrangement.Center
        ) {
            Text(text = viewModel.endMessage)
            Spacer(modifier= modifier.height(8.dp))
            Text(text = "Final Score: ${uiState.score}")
            Spacer(modifier= modifier.height(16.dp))
            Button(onClick = { viewModel.resetGame() }) {
                Text("Play Again")
            }
        }
    }
}

