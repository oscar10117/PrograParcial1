package com.example.parcial1.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parcial1.model.CategoriaMedicamento
import com.example.parcial1.model.Medicamento
import com.example.parcial1.model.MedicamentoConReceta
import com.example.parcial1.model.MedicamentoLibre
import com.example.parcial1.ui.theme.Parcial1Theme

fun medicamentosDeEjemplo(): List<Medicamento> = listOf(
    MedicamentoConReceta(
        id = 1,
        nombre = "Amoxicilina 500mg",
        laboratorio = "Bayer",
        precio = 8.50,
        stock = 40,
        categoria = CategoriaMedicamento.ANTIBIOTICO,
        controlado = false
    ),
    MedicamentoLibre(
        id = 2,
        nombre = "Paracetamol 500mg",
        laboratorio = "Genfar",
        precio = 2.20,
        stock = 150,
        categoria = CategoriaMedicamento.ANALGESICO
    ),
    MedicamentoLibre(
        id = 3,
        nombre = "Loratadina 10mg",
        laboratorio = "Pfizer",
        precio = 3.75,
        stock = 60,
        categoria = CategoriaMedicamento.ANTIALERGICO
    ),
    MedicamentoLibre(
        id = 4,
        nombre = "Vitamina C 1000mg",
        laboratorio = "Natufarma",
        precio = 5.00,
        stock = 0,
        categoria = CategoriaMedicamento.VITAMINA
    ),
    MedicamentoConReceta(
        id = 5,
        nombre = "Tramadol 50mg",
        laboratorio = "Roche",
        precio = 12.90,
        stock = 15,
        categoria = CategoriaMedicamento.ANALGESICO,
        controlado = true
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaMedicamentosScreen(medicamentos: List<Medicamento>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Inventario de Medicamentos") })
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(medicamentos, key = { it.id }) { medicamento ->
                MedicamentoCard(medicamento)
            }
        }
    }
}

@Composable
fun MedicamentoCard(medicamento: Medicamento) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = medicamento.descripcion(), style = MaterialTheme.typography.titleMedium)
            Text(
                text = "Categoría: ${medicamento.categoria}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Precio: $${"%.2f".format(medicamento.precio)}  ·  Stock: ${medicamento.stock}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = if (medicamento.requiereReceta()) "Requiere receta médica" else "Venta libre",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = if (medicamento.estaDisponible()) "Disponible" else "Sin stock",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaMedicamentosPreview() {
    Parcial1Theme {
        ListaMedicamentosScreen(medicamentosDeEjemplo())
    }
}
