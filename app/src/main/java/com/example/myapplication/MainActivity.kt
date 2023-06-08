package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskListPreview()
                }
            }
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskCompleteToggle: (Int) -> Unit,
    onTaskDelete: (Int) -> Unit
) {
    LazyColumn {
        items(items = tasks) { tarea ->
            TaskItem(
                task = tarea,
                onTaskCompleteToggle = onTaskCompleteToggle,
                onTaskDelete = onTaskDelete
            )
        }
    }
}

data class Task(val id: Int, val title: String, val description: String, val isComplete: Boolean)

@Composable
fun TaskItem(
    task: Task,
    onTaskCompleteToggle: (Int) -> Unit,
    onTaskDelete: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Checkbox(
            checked = task.isComplete,
            onCheckedChange = { isChecked ->
                onTaskCompleteToggle(task.id)
            },
            modifier = Modifier.padding(8.dp)
        )

        Column {
            Text(
                text = task.title,
                color = Color.Black
            )
            Text(
                text = task.description,
                color = Color.Gray
            )
        }

        IconButton(
            onClick = { onTaskDelete(task.id) },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = Color.White //
            )
        }
    }
}


@Preview
@Composable
fun TaskListPreview() {
    val tasks = listOf(
        Task(1, "Task 1", "Tarea 1", false),
        Task(2, "Task 2", "Tarea 2", true),
        Task(3, "Task 3", "Tarea 3", false)
    )
    TaskList(
        tasks = tasks,
        onTaskCompleteToggle = {},
        onTaskDelete = {}
    )
}