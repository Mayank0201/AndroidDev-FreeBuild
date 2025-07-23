package com.example.notes.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.NotesViewModel
import com.example.notes.data.Notes
import com.example.notes.data.SearchMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = viewModel(factory = NotesViewModel.factory)
) {
    var showDialog by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = modifier.fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )

        {
            Spacer(modifier=modifier.height(12.dp))
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        viewModel.searchQuery = it
                        viewModel.searchNotes()
                    },
                    placeholder = {
                        Text(
                            when (viewModel.searchMode) {
                                SearchMode.ID -> "Search by ID"
                                SearchMode.TITLE -> "Search by Title"
                            }
                        )
                    },
                    modifier = modifier.weight(1f)
                )

                Spacer(modifier = modifier.width(8.dp))

                DropdownMenuBox(
                    selectedMode = viewModel.searchMode,
                    onModeSelected = {
                        viewModel.searchMode = it
                        viewModel.searchNotes()
                    }
                )
            }

            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(viewModel.notes) { note ->
                    if (viewModel.editingNoteId == note.id) {
                        Editor(
                            note = note,
                            onEditClicked = { title, content ->
                                viewModel.updateNoteContent(note.id, title, content)
                            }
                        )
                    } else {
                        AppCard(
                            item = note,
                            onEdit = { viewModel.setEditing(note) },
                            onDelete = {
                                viewModel.deleteNote(note)
                                searchText = ""
                            }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Note",
                tint = Color.White
            )
        }

        if (showDialog) {
            AlertDialog(
                modifier = modifier.padding(6.dp),
                onDismissRequest = { showDialog = false },
                confirmButton = {},
                title = { Text("Add Note") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = viewModel.titleName,
                            onValueChange = viewModel::updateTitleName,
                            placeholder = { Text(text = "Enter your title:") },
                            modifier = modifier.fillMaxWidth()
                        )

                        Spacer(modifier = modifier.height(8.dp))

                        OutlinedTextField(
                            value = viewModel.itemName,
                            onValueChange = viewModel::updateItemName,
                            placeholder = { Text(text = "Enter your content:") },
                            modifier = modifier.fillMaxWidth()
                        )

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    viewModel.addNote()
                                    showDialog = false
                                    searchText = ""
                                }
                            ) {
                                Text("Add")
                            }
                            Button(onClick = { showDialog = false }) {
                                Text("Exit")
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun DropdownMenuBox(
    modifier: Modifier=Modifier,
    selectedMode: SearchMode,
    onModeSelected: (SearchMode) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = modifier.wrapContentSize(Alignment.TopStart)) {
        Button(onClick = { expanded = true }) {
            Text(selectedMode.name)
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text("ID") },
                onClick = {
                    onModeSelected(SearchMode.ID)
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("TITLE") },
                onClick = {
                    onModeSelected(SearchMode.TITLE)
                    expanded = false
                }
            )

        }
    }
}

@Composable
fun AppCard(
    modifier: Modifier=Modifier,
    item: Notes,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(2.dp, Color.Black, MaterialTheme.shapes.medium)
            .clickable { isExpanded = !isExpanded },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = modifier.padding(12.dp)) {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier.weight(1f)
                )

                Row {
                    IconButton(onClick = onEdit) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                    }

                    IconButton(onClick = onDelete) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }

            if (isExpanded) {
                Text(
                    text = item.content,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth()
                )
            }

            Text(
                text = "Edited on: ${formatDate(item.timestamp)} (ID: ${item.id})",
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.align(Alignment.End)
                    .padding(top = if (isExpanded) 8.dp else 4.dp),
                color = Color.Gray
            )
        }
    }
}