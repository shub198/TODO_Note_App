package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.time.LocalDateTime
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MyApp()

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModel: NoteViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "listScreen"
    ) {
        composable("listScreen") {
            ListWithFab(navController,viewModel)
        }
        composable("addItemScreen") {
            AddNewListItem(navController,viewModel)
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ListWithFab(navController: NavController,viewModel: NoteViewModel) {
    val items by viewModel.allNotes.observeAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("addItemScreen") },
                content = { Icon(Icons.Default.Add, contentDescription = "Add") }
            )
        },
        content = {
//            Column(modifier = Modifier.padding(16.dp)) {
//
//                    ItemLayout(note = Note("asdfg","sdfghj","asdfghjk"),10.dp,{/*"dsdfgh"*/})
//
//            }
        }
    )
//    Column(modifier = Modifier.padding(16.dp)) {
//
//        ItemLayout(note = Note("asdfg","sdfghj","asdfghjk"),10.dp,{/*"dsdfgh"*/})
//
//    }
    LazyColumn(content = {
        items(items!!){item ->
            ItemLayout(date = item.date, title =item.title)
        }
    })
}


@Composable
fun ItemLayout(date:String, title:String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color(0xFF123456))

    ) {
        Text(text = date,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier .padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(text = title,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier .padding(16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {/*"asdfg"*/}) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete icon", tint = Color.White )

        }

    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewListItem(navController: NavController,viewModel: NoteViewModel) {
    val title = remember { mutableStateOf("") }
    val content = remember { mutableStateOf("") }
    val items by viewModel.allNotes.observeAsState()
//    val itemName = remember { mutableStateOf("") }

    val currentDate = remember { mutableStateOf(LocalDateTime.now().toString()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = currentDate.value,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = title.value,
            onValueChange = { title.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            label = { Text("Title") },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray),
            placeholder = { Text("Enter title...") }
        )

        OutlinedTextField(
            value = content.value,
            onValueChange = { content.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            label = { Text("Content") },
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray),
            placeholder = { Text("Enter content...") }
        )

        Button(
            onClick = { viewModel.addNote(Note(currentDate.value.toString(), title.value.toString(),content.value.toString()))
                navController.popBackStack()},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add")
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun PreviewEntryList() {
//    AddNewListItem()

}
