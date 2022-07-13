package com.example.notes.notes_future.present.addEditNote.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notes.notes_future.present.addEditNote.AddEditNoteEvent
import com.example.notes.notes_future.present.addEditNote.AddEditNoteViewModel
import com.example.notes.notes_future.present.notes.compose.ButtonWithImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditPresent(
    navHostController: NavHostController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {

    val titleState = viewModel.title.value
    val contentState = viewModel.content.value

    Scaffold(
        floatingActionButton = {
            ButtonWithImage(
                image = Icons.Filled.Done,
                onClick = {
                    viewModel.onEvent(AddEditNoteEvent.SaveNote)
                    navHostController.popBackStack()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TextField(
                text = titleState.text,
                placeholder = titleState.placeholder,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isPlaceholder = titleState.isPlaceholder,
                singleLine = true,
                textStyle = MaterialTheme.typography.displayMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                text = contentState.text,
                placeholder = contentState.placeholder,
                onValueChange = {
                    viewModel.onEvent(AddEditNoteEvent.EnteredContent(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditNoteEvent.ChangeContentFocus(it))
                },
                isPlaceholder = contentState.isPlaceholder,
                textStyle = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}