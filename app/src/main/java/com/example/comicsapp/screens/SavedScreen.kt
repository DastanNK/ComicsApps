package com.example.comicsapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.comicsapp.BottomNavBar
import com.example.comicsapp.BottomNavBarItems

@Composable
fun SavedScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.weight(1f)) {  }
        BottomNavBar(BottomNavBarItems.SavedItem, navController)
    }
}