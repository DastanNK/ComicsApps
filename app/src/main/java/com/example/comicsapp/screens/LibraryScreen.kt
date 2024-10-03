package com.example.comicsapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.comicsapp.Api.NetworkResult
import com.example.comicsapp.BottomNavBar
import com.example.comicsapp.BottomNavBarItems
import com.example.comicsapp.CharactersApiResponse
import com.example.comicsapp.Screen
import com.example.comicsapp.viewmodel.LibraryViewModel

@Composable
fun LibraryScreen(navController: NavController, viewModel: LibraryViewModel) {
    val result by viewModel.result.collectAsState()
    val text = viewModel.queryText.collectAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = text.value,
                onValueChange = viewModel::onQueryChanged,
                label = { Text(text = "Character search") },
                placeholder = { Text(text = "Character") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                when (result) {
                    is NetworkResult.Initial -> {
                        Text(text = "Search for a character")
                    }


                    is NetworkResult.Success -> {
                        Text(text = "Error: ${result.data?.code}")
                        //ShowCharactersList(result)
                    }

                    is NetworkResult.Loading -> {
                        //Text(text = "Error: ${result.data?.code}")
                        CircularProgressIndicator()
                    }

                    is NetworkResult.Error -> {
                        Text(text = "Error: ${result.data?.code}")
                    }
                }
            }
        }
        BottomNavBar(BottomNavBarItems.LibraryItem, navController)

    }
}

@Composable
fun ShowCharactersList(
    result: NetworkResult<CharactersApiResponse>
) {

    result.data?.data?.results?.let { characters ->
        Text(characters.get(0).toString())
        /*LazyColumn(
            modifier = Modifier.background(Color.LightGray),
            verticalArrangement = Arrangement.Top
        ) {*/
            /*result.data.attributionText?.let {
                item {

                    //AttributionText(text = it)
                }
            }*/

            /*items(items = characters) { character ->
                val imageUrl = character.thumbnail?.path + "." + character.thumbnail?.extension
                val title = character.name
                val description = character.description
                val context = LocalContext.current
                val id = character.id

                Column(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color.White)
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            //if (character.id != null)
                            //navController.navigate(Screen.CharacterDetail.createRoute(id))
                            /*else
                                Toast
                                    .makeText(context, "Character id is null", Toast.LENGTH_SHORT)
                                    .show()*/
                        }
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        /*CharacterImage(
                            url = imageUrl,
                            modifier = Modifier
                                .padding(4.dp)
                                .width(100.dp)
                        )*/

                        //Column(modifier = Modifier.padding(4.dp)) {
                            Text(text = title ?: "", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        //}
                    }

                    Text(text = description ?: "", maxLines = 4, fontSize = 14.sp)
                }
            }
        }*/
    }
}