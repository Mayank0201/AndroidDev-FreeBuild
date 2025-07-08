package com.example.reportmaker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MarkScreen(
    modifier: Modifier=Modifier,
    subjects: List<String>,
    marks: Map<String, String>,
    errors: Map<String, String>,
    isNextEnabled: Boolean,
    onMarkChange: (String, String) -> Unit,
    onNextClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        subjects.forEach { subject ->
            OutlinedTextField(
                value = marks[subject] ?: "",
                onValueChange = { onMarkChange(subject, it) },
                label = { Text(subject) },
                isError = errors[subject]?.isNotBlank() == true,
                supportingText = {
                    if (errors[subject]?.isNotBlank() == true) {
                        Text(text = errors[subject] ?: "", color = Color.Red)
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }

        Spacer(modifier=modifier.height(24.dp))

        Button(
            onClick = onNextClicked,
            enabled = isNextEnabled,
            modifier = modifier.align(Alignment.End)
        ) {
            Text("Next")
        }
    }
}