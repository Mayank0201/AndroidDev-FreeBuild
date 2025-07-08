package com.example.reportmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    viewmodel: ReportViewModel,
    onNextButtonClicked: () -> Unit
) {

    var name by rememberSaveable { mutableStateOf("") }
    var id by rememberSaveable { mutableStateOf("") }

    val nameError = viewmodel.nameError.value
    val idError = viewmodel.idError.value

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                viewmodel.setName(it)
            },
            label = { Text("Name") },
            isError = nameError.isNotBlank(),
            supportingText = {
                if (nameError.isNotBlank()) {
                    Text(nameError)
                }
            }
        )

        OutlinedTextField(
            value = id,
            onValueChange = {
                id = it
                viewmodel.setId(it)
            },
            label = { Text("ID") },
            isError = idError.isNotBlank(),
            supportingText = {
                if (idError.isNotBlank()) {
                    Text(idError)
                }
            }
        )

        Button(
            onClick = {
                viewmodel.setName(name)
                viewmodel.setId(id)

                if (nameError.isBlank() && idError.isBlank()) {
                    onNextButtonClicked()
                }
            },
            enabled = name.isNotBlank() && id.isNotBlank()
        ) {
            Text("Next")
        }
    }
}