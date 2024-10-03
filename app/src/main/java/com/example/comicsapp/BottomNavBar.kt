package com.example.comicsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

sealed class BottomNavBarItems(val route: String, val icon: Int) {
    object LibraryItem : BottomNavBarItems("library", R.drawable.baseline_add_home_24)
    object SavedItem : BottomNavBarItems("saved", R.drawable.baseline_edit_24)
}

@Composable
fun BottomNavBar(selectedItem: BottomNavBarItems, navController: NavController) {
    val bottomNavBarItems = listOf(
        BottomNavBarItems.LibraryItem,
        BottomNavBarItems.SavedItem
    )
    Row(
        modifier = Modifier.fillMaxWidth().height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (item in bottomNavBarItems) {
            Image(
                rememberImagePainter(item.icon), contentDescription = null, colorFilter = ColorFilter.tint(
                    if (item == selectedItem) {
                        Color.DarkGray
                    } else {
                        Color.LightGray
                    }
                ), modifier = Modifier.clickable {
                    navController.navigate(item.route){
                        launchSingleTop = true
                    }
                }
            )

        }
    }
}