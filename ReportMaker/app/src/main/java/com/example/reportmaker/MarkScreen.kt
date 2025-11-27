package com.example.reportmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarkScreen(
    modifier: Modifier = Modifier,
    subjects: List<String>,
    marks: Map<String, String>,
    errors: Map<String, String>,
    isNextEnabled: Boolean,
    onMarkChange: (String, String) -> Unit,
    onNextClicked: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Enter Marks") })
        }
    ) { padding ->
        Column(
            modifier = modifier.padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier.padding(16.dp)) {
                    subjects.forEach { subject ->
                        OutlinedTextField(
                            value = marks[subject] ?: "",
                            onValueChange = { onMarkChange(subject, it) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number
                            ),
                            label = { Text(subject) },
                            isError = errors[subject]?.isNotBlank() == true,
                            supportingText = {
                                if (errors[subject]?.isNotBlank() == true) {
                                    Text(text = errors[subject] ?: "", color = Color.Red)
                                }
                            },
                            modifier = modifier.fillMaxWidth()
                                .padding(vertical = 6.dp)
                        )
                    }
                }
            }

            Button(
                onClick = onNextClicked,
                enabled = isNextEnabled,
                shape = RoundedCornerShape(50),
                modifier = modifier.align(Alignment.End).height(50.dp)
            ) {
                Text("Next")
            }
        }
    }
}