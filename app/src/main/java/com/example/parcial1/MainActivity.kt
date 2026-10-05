package com.example.parcial1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.parcial1.ui.screens.ListaMedicamentosScreen
import com.example.parcial1.ui.screens.ListaProveedoresScreen
import com.example.parcial1.ui.screens.medicamentosDeEjemplo
import com.example.parcial1.ui.screens.proveedoresDeEjemplo
import com.example.parcial1.ui.theme.Parcial1Theme

private sealed class Pantalla(val ruta: String, val etiqueta: String) {
    data object Medicamentos : Pantalla("medicamentos", "Medicamentos")
    data object Proveedores : Pantalla("proveedores", "Proveedores")
}

private val pantallas = listOf(Pantalla.Medicamentos, Pantalla.Proveedores)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial1Theme {
                FarmaciaApp()
            }
        }
    }
}

@Composable
fun FarmaciaApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                val backStackEntry by navController.currentBackStackEntryAsState()
                val destinoActual = backStackEntry?.destination
                pantallas.forEach { pantalla ->
                    NavigationBarItem(
                        selected = destinoActual?.hierarchy?.any { it.route == pantalla.ruta } == true,
                        onClick = {
                            navController.navigate(pantalla.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (pantalla == Pantalla.Medicamentos) Icons.AutoMirrored.Filled.List else Icons.Default.Person,
                                contentDescription = pantalla.etiqueta
                            )
                        },
                        label = { Text(pantalla.etiqueta) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pantalla.Medicamentos.ruta,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Pantalla.Medicamentos.ruta) {
                ListaMedicamentosScreen(medicamentos = medicamentosDeEjemplo())
            }
            composable(Pantalla.Proveedores.ruta) {
                ListaProveedoresScreen(proveedores = proveedoresDeEjemplo())
            }
        }
    }
}
