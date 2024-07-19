package com.example.swapp.navigation

sealed class SWScreens(val route: String){
    object FirstScreen: SWScreens("main_screen")
    object SecondScreen: SWScreens("second_screen")
    object ThirdScreen: SWScreens("character_screen")
    object Fourth: SWScreens("oneCharacter_screen")
    object Fifth: SWScreens("favourites_screen")
}
