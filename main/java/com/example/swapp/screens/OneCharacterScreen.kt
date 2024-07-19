package com.example.swapp.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.swapp.dao.Character
import com.example.swapp.favorites.CharacterFav
import com.example.swapp.favorites.CharacterFavViewModel
import com.example.swapp.ui.theme.sixtFour

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OneCharacterScreen(
    navController: NavController,
    character: Character,
    characterFavViewModel: CharacterFavViewModel
){
    Scaffold(
        topBar = {
            Icon(imageVector= Icons.Default.ArrowBack, contentDescription="Arrow Back",
                modifier= Modifier.clickable {
                    navController.popBackStack()

                }
            )
        }

    ) {
        BodyContentCharacter(navController = navController, character = character, modifier =Modifier, characterFavViewModel )

    }
}

@Composable
fun BodyContentCharacter(
    navController: NavController,
    character: Character,
    modifier: Modifier,
    characterFavViewModel: CharacterFavViewModel
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement= Arrangement.Center,
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black.copy(0.5f))
    ){

        Column(
            modifier= modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            val painter = rememberAsyncImagePainter(character.image)
            Image(
                painter = painter,
                contentDescription = "Imagen del personaje",
                modifier= modifier
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .height(400.dp)
            )
        }
        Spacer(modifier = modifier.height(10.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier= modifier
                .width(300.dp)
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
        ) {
            Text(text = character.name, modifier= Modifier.padding(top=10.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
                , fontFamily = sixtFour)
            Text(text = character.homeworld, modifier= Modifier.padding(top=10.dp),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyLarge
                , fontFamily = sixtFour
            )
            Button(onClick = {
                val fav= CharacterFav(character.id)
                characterFavViewModel.insert(fav)
            }) {
                Text(text = "Favorito")
            }

        }
    }
}
