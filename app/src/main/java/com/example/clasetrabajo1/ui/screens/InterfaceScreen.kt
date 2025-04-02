package com.example.clasetrabajo1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.clasetrabajo1.R

@Composable
fun InterfaceScreen(navController: NavHostController) {
    //Proporciona una API sencilla que puedes usar para lo siguiente:
    // ensamblar rápidamente la estructura de tu app según los lineamientos de Material Design.
    Scaffold(
        topBar = { SearchBar() },
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Text(
                text = "Relacionado con los artículos que viste",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(16.dp)
            )
            BookGrid()
            //Para añadir una línea entre apartados
            Divider(Modifier.padding(vertical = 16.dp),
                thickness = 6.dp
            )
            Text(
                text = "Hasta 30% para divertirte jugando",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                modifier = Modifier.padding(20.dp)
            )
            GameSection()
        }
    }
}

@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("   Buscar en Amazon.com.mx") }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 10.dp)
            .height(40.dp),
        color = Color.White,
        shape = RoundedCornerShape(16.dp),
        shadowElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .border(10.dp, color = Color(0xFFB3E5FC), RoundedCornerShape(5.dp))
                .padding(1.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar",
                    tint = Color.Black,
                    modifier = Modifier.padding(2.dp)
                )

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 16.sp),
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    painter = painterResource(id = R.drawable.iconcamera),
                    contentDescription = "Busqueda por foto",
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}

@Composable
fun BookGrid() {
    val books = listOf(
        R.drawable.libro1, R.drawable.libro2, R.drawable.libro3,
        R.drawable.libro4, R.drawable.libro5, R.drawable.libro6,
        R.drawable.libro7, R.drawable.libro8, R.drawable.libro9
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.padding(9.dp)
    ) {
        items(books.size) { index ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .height(99.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1)),
                shape = RectangleShape
            ) {
                Image(
                    painter = painterResource(id = books[index]),
                    contentDescription = "Libro",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}

@Composable
fun GameSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.oferta),
            contentDescription = "Oferta",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BottomNavBar() {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.AccountCircle, contentDescription = "Cuenta") },
            label = { Text("Cuenta") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito") },
            label = { Text("Carrito") },
            selected = false,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Menu, contentDescription = "Menú") },
            label = { Text("Menú") },
            selected = false,
            onClick = {}
        )
    }
}
