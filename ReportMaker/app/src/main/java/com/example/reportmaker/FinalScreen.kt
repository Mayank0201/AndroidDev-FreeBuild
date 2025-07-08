package com.example.reportmaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "📄 Report Card", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = modifier.height(16.dp))

        Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
        Text(text = "ID: $id", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = modifier.height(8.dp))

        if (marks != null) {
            Text(text = "Marks", style = MaterialTheme.typography.titleMedium)
            Text("Maths: ${marks.maths}")
            Text("English: ${marks.english}")
            Text("ICT: ${marks.ict}")
            Text("Physics: ${marks.physics}")
            Text("Biology: ${marks.biology}")
            Text("Chemistry: ${marks.chemistry}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Total Marks: $total")
            Text("Result: $result")
        } else {
            Text("Marks not available")
        }

        Spacer(modifier = modifier.height(24.dp))

        Button(onClick = {
            viewModel.resetAll()
            onRestartClicked()
        }) {
            Text("Restart")
        }
    }

}

