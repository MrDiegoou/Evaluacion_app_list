package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
                    val tasks = remember { mutableStateListOf<Task>() }

                    Column {
                        TaskInput(onAddTask = { title, description ->
                            val task = Task(
                                id = tasks.size + 1,
                                title = title,
                                description = description,
                                isComplete = false
                            )
                            tasks.add(task)
                        })

                        TaskList(tasks = tasks)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInput(
    modifier: Modifier = Modifier,
    onAddTask: (String, String) -> Unit
) {
    val taskTitle = remember { mutableStateOf("") }
    val taskDescription = remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = taskTitle.value,
            onValueChange = { taskTitle.value = it },
            label = { Text("Task Title") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        TextField(
            value = taskDescription.value,
            onValueChange = { taskDescription.value = it },
            label = { Text("Task Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                onAddTask(taskTitle.value, taskDescription.value)
                taskTitle.value = ""
                taskDescription.value = ""
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text("Add Task")
        }
    }
}

@Composable
fun TaskList(tasks: List<Task>) {
    LazyColumn {
        items(items = tasks) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    val isSelected = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSelected.value) Color.LightGray else Color.White)
            .clickable {
                isSelected.value = !isSelected.value
            }
    ) {
        Checkbox(
            checked = isSelected.value,
            onCheckedChange = { isChecked ->
                isSelected.value = isChecked
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
                color = Colo    r.Gray
            )
        }

        IconButton(
            onClick = {
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = Color.White
            )
        }
    }
}
@Preview
@Composable
fun TaskListPreview() {
}

data class Task(val id: Int, val title: String, val description: String, val isComplete: Boolean)
