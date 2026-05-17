package com.example.lab09

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter


@Composable
fun ScreenCharacters(
    navController: NavHostController,
    servicio: CharacterApiService
) {

    var listaCharacters by remember {
        mutableStateOf<List<CharacterModel>>(emptyList())
    }

    LaunchedEffect(Unit) {

        listaCharacters =
            servicio.getCharacters().results
    }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101820))

    ) {

        items(listaCharacters) { item ->

            Card(

                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
                    .clickable {

                        navController.navigate(
                            "characterDetail/${item.id}"
                        )
                    },

                shape = RoundedCornerShape(20.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )

            ) {

                Column {

                    Image(

                        painter = rememberAsyncImagePainter(
                            item.image
                        ),

                        contentDescription = item.name,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),

                        contentScale = ContentScale.Crop
                    )

                    Column(

                        modifier = Modifier
                            .padding(16.dp)

                    ) {

                        Text(

                            text = item.name,

                            style = MaterialTheme.typography.headlineSmall,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Especie: ${item.species}"
                        )

                        Text(
                            text = "Estado: ${item.status}"
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ScreenCharacter(

    navController: NavHostController,

    servicio: CharacterApiService,

    id: Int

) {

    var character by remember {

        mutableStateOf<CharacterModel?>(null)
    }

    LaunchedEffect(Unit) {

        character =
            servicio.getCharacterById(id)
    }

    character?.let { item ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF101820))
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(

                painter = rememberAsyncImagePainter(
                    item.image
                ),

                contentDescription = item.name,

                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(20.dp)),

                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(

                text = item.name,

                style = MaterialTheme.typography.headlineMedium,

                color = Color.White,

                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Estado: ${item.status}",
                color = Color.White
            )

            Text(
                text = "Especie: ${item.species}",
                color = Color.White
            )

            Text(
                text = "Género: ${item.gender}",
                color = Color.White
            )

            Text(
                text = "Origen: ${item.origin.name}",
                color = Color.White
            )
        }
    }
}