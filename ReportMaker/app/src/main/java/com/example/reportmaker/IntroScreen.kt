package com.example.reportmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    viewmodel: ReportViewModel,
    onNextButtonClicked: () -> Unit
) {
    val nameError = viewmodel.nameError.value
    val idError = viewmodel.idError.value

    var name by rememberSaveable { mutableStateOf("") }
    var id by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Student Info") }
            )
        }
    ) { padding ->
        Column(
            modifier = modifier.fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = {
                            name = it
                            viewmodel.setName(it)
                        },
                        label = { Text("Name") },
                        isError = nameError.isNotBlank(),
                        supportingText = { if (nameError.isNotBlank()) Text(nameError) },
                        modifier = modifier.fillMaxWidth()
                    )

                    Spacer(modifier.height(12.dp))

                    OutlinedTextField(
                        value = id,
                        onValueChange = {
                            id = it
                            viewmodel.setId(it)
                        },
                        label = { Text("ID") },
                        isError = idError.isNotBlank(),
                        supportingText = { if (idError.isNotBlank()) Text(idError) },
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }

            Button(
                onClick = {
                    viewmodel.setName(name)
                    viewmodel.setId(id)
                    if (nameError.isBlank() && idError.isBlank()) onNextButtonClicked()
                },
                enabled = name.isNotBlank() && id.isNotBlank(),
                shape = RoundedCornerShape(50),
                modifier = modifier.fillMaxWidth(0.5f).height(50.dp)
            ) {
                Text("Next")
            }
        }
    }
}