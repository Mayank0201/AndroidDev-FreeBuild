package com.example.reportmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinalScreen(
    modifier: Modifier = Modifier,
    onRestartClicked: () -> Unit,
    viewModel: ReportViewModel
) {
    val name = viewModel.name.value
    val id = viewModel.id.value
    val marks = viewModel.finalMarks.value
    val result = viewModel.getPassOrFail()
    val total = viewModel.getTotalMarks()
    val resultColor = if (result == "Pass") Color(0xFF4CAF50) else Color(0xFFF44336)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Report Card") })
        }
    ) { padding ->
        Column(
            modifier = modifier.padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text("📄 Report Card", style = MaterialTheme.typography.headlineSmall)

                    Text("Name: $name", style = MaterialTheme.typography.bodyLarge)
                    Text("ID: $id", style = MaterialTheme.typography.bodyLarge)

                    Spacer(Modifier.height(12.dp))

                    marks?.let {
                        Text("Marks", style = MaterialTheme.typography.titleMedium)
                        Text("Maths: ${it.maths}")
                        Text("English: ${it.english}")
                        Text("ICT: ${it.ict}")
                        Text("Physics: ${it.physics}")
                        Text("Biology: ${it.biology}")
                        Text("Chemistry: ${it.chemistry}")

                        Spacer(modifier.height(12.dp))
                        Text("Total Marks: $total", style = MaterialTheme.typography.titleMedium)
                        Text("Result: $result", color = resultColor, style = MaterialTheme.typography.titleLarge)
                    } ?: Text("Marks not available")
                }
            }

            Button(
                onClick = {
                    viewModel.resetAll()
                    onRestartClicked()
                },
                shape = RoundedCornerShape(50),
                modifier = modifier.fillMaxWidth(0.5f).height(50.dp)
            ) {
                Text("Restart")
            }
        }
    }
}