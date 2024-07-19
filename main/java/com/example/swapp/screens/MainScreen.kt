package com.example.swapp.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swapp.R
import com.example.swapp.dao.Character
import com.example.swapp.dao.CharacterViewModel
import com.example.swapp.model.loadData
import com.example.swapp.navigation.SWScreens
import com.example.swapp.ui.theme.sixtFour
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    characterViewModel: CharacterViewModel,
    listaCharacters: List<Character>
){
    Scaffold {
        BodyContent(navController, Modifier.padding(it),listaCharacters,characterViewModel)
    }
}

@Composable
fun BodyContent(navController: NavController, modifier: Modifier, listaCharacters: List<Character>, characterViewModel: CharacterViewModel){
    Column{
        Box {
            Image(
                painter = painterResource(id = R.drawable.primeraimagen),
                contentDescription = "Imagen de fondo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { /*navegar siguiente pantalla*/
                    navController.navigate(route = SWScreens.SecondScreen.route)
                    CoroutineScope(Dispatchers.IO).launch {
                        loadData(characterViewModel,listaCharacters)
                    }
                                 }
                    ) {
                    Text(text = "COMENZAR", fontFamily = sixtFour)
                }
            }
        }
    }
}

//@Composable
//fun BodyContent(navController: NavController){
 //   LazyColumn(){
  //      itemsIndexed(equipos){index, equipo ->
  //          EquipoItem(equipo = equipo, modifier = Modifier, navController)
 //       }
 //   }
//}

