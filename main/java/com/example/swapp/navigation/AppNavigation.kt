package com.example.swapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swapp.dao.CharacterViewModel
import com.example.swapp.favorites.CharacterFavViewModel
import com.example.swapp.screens.CharacterScreen
import com.example.swapp.screens.FavScreen
import com.example.swapp.screens.MainScreen
import com.example.swapp.screens.OneCharacterScreen
import com.example.swapp.screens.SecondScreen


@Composable
fun AppNavigation(characterViewModel: CharacterViewModel, characterFavViewModel: CharacterFavViewModel) {
    val navController= rememberNavController()
    val listaCharacters= characterViewModel.allValues.collectAsState(initial = emptyList()).value
    val selectedCharacter= characterViewModel.selectedCharacter.observeAsState(initial = characterViewModel.basicData()).value
    val listaFavCharacters= characterFavViewModel.allValues.collectAsState(initial = emptyList()).value

    NavHost(navController = navController, startDestination = SWScreens.FirstScreen.route){
        composable(route= SWScreens.FirstScreen.route){ // PRIMERA PANTALLA
            MainScreen(navController, characterViewModel, listaCharacters)
        } 
        composable(route= SWScreens.SecondScreen.route) {
            SecondScreen(navController = navController)
        }

        composable(route= SWScreens.ThirdScreen.route) {
            CharacterScreen(navController = navController, listaCharacters, characterViewModel)
        }
        composable(route= SWScreens.Fourth.route) {
            OneCharacterScreen(navController = navController, character =selectedCharacter , characterFavViewModel)
        }
        composable(route= SWScreens.Fifth.route) {
            FavScreen(navController = navController,listaCharacters,listaFavCharacters,characterViewModel)
        }
    }
}