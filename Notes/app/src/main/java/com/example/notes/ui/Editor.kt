package com.example.notes.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notes.data.Notes

@Composable
fun Editor(
    modifier: Modifier=Modifier,
    note: Notes,
    onEditClicked: (String,String) -> Unit
) {
    var text by remember { mutableStateOf(note.content) }
    var title by remember {mutableStateOf(note.title)}

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Edit Title") }
        )

        Spacer(modifier = modifier.height(8.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = modifier.fillMaxWidth(),
            label = { Text("Edit Content") }
        )

        Button(
            onClick = { onEditClicked(title,text) },
            modifier = modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}