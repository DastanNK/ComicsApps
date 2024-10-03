package com.example.comicsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.comicsapp.screens.LibraryScreen
import com.example.comicsapp.screens.SavedScreen
import com.example.comicsapp.ui.theme.ComicsAppTheme
import com.example.comicsapp.viewmodel.LibraryViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<LibraryViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    MainController(navController, viewModel)
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Library : Screen("library")
    object EachItem : Screen("eachitem/{characterId}") {
        fun createRoute(characterId: Int?): String = "eachitem/${characterId}"
    }
    object Saved : Screen("saved")
}

@Composable
fun MainController(navController: NavHostController, viewModel: LibraryViewModel) {

    NavHost(navController, Screen.Library.route) {
        composable(Screen.Library.route) {
            LibraryScreen(navController, viewModel = viewModel )
        }
        composable(Screen.Saved.route) {
            SavedScreen(navController)
        }
        composable(Screen.EachItem.route) { navBackStackEntry ->

        }
    }

}
