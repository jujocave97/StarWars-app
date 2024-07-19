package com.example.swapp.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.swapp.favorites.CharacterFav
import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberAsyncImagePainter
import com.example.swapp.dao.Character
import com.example.swapp.dao.CharacterViewModel
import com.example.swapp.navigation.SWScreens


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavScreen(navController: NavController, listaCharacters: List<Character>, listaFavCharacter:List<CharacterFav>, characterViewModel: CharacterViewModel){
    Scaffold {
        BodyContentScreen(navController, listaCharacters, characterViewModel, listaFavCharacter)
    }
}



@ExperimentalFoundationApi
@ExperimentalCoilApi
@Composable
fun BodyContentScreen(
    navController: NavController,
    listaCharacters: List<Character>,
    characterViewModel: CharacterViewModel,
    listaFavCharacter: List<CharacterFav>
){
    val favIds = listaFavCharacter.map {  it.characterId }
    val filteredList = listaCharacters.filter { it.id in favIds}

    LazyColumn(
        contentPadding = PaddingValues(1.dp),
        modifier = Modifier.fillMaxWidth()
    ){  // todos los personajes
        items(filteredList){
            CharacterFavFun(modifier = Modifier, character =it , characterViewModel, navController)
        }
    }

}

@Composable
fun CharacterFavFun(
    modifier: Modifier,
    character: Character,
    characterViewModel: CharacterViewModel,
    navController: NavController
){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                //navegacion al item
                characterViewModel.setSelectedCharacter(character)
                navController.navigate(route = SWScreens.Fourth.route)
            }

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier= modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp))
                .padding(8.dp)
        ) {
            val painter =rememberAsyncImagePainter(character.image)
            Image(painter =painter, contentDescription ="", //imagen personaje
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(height = 300.dp, width = 400.dp)
            )
        }
    }
}
