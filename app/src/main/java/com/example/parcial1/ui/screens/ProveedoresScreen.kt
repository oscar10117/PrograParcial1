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
import com.example.parcial1.model.MedicamentoLibre
import com.example.parcial1.model.Proveedor
import com.example.parcial1.ui.theme.Parcial1Theme

fun proveedoresDeEjemplo(): List<Proveedor> {
    val bayer = Proveedor(id = 1, nombre = "Bayer Laboratorios", telefono = "+506 2222-1010", email = "ventas@bayer.cr")
    bayer.agregarMedicamento(
        MedicamentoLibre(1, "Aspirina 500mg", "Bayer", 1.80, 200, CategoriaMedicamento.ANALGESICO)
    )

    val genfar = Proveedor(id = 2, nombre = "Genfar Distribuidora", telefono = "+506 2222-2020", email = "contacto@genfar.cr")
    genfar.agregarMedicamento(
        MedicamentoLibre(2, "Paracetamol 500mg", "Genfar", 2.20, 150, CategoriaMedicamento.ANALGESICO)
    )
    genfar.agregarMedicamento(
        MedicamentoLibre(3, "Ibuprofeno 400mg", "Genfar", 2.80, 90, CategoriaMedicamento.ANALGESICO)
    )

    val pfizer = Proveedor(id = 3, nombre = "Pfizer Andina", telefono = "+506 2222-3030", email = "pedidos@pfizer.cr")
    pfizer.agregarMedicamento(
        MedicamentoLibre(4, "Loratadina 10mg", "Pfizer", 3.75, 60, CategoriaMedicamento.ANTIALERGICO)
    )

    val natufarma = Proveedor(id = 4, nombre = "Natufarma Directo", telefono = "+506 2222-4040", email = "info@natufarma.cr")

    return listOf(bayer, genfar, pfizer, natufarma)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaProveedoresScreen(proveedores: List<Proveedor>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Proveedores") })
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(proveedores, key = { it.id }) { proveedor ->
                ProveedorCard(proveedor)
            }
        }
    }
}

@Composable
fun ProveedorCard(proveedor: Proveedor) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = proveedor.nombre, style = MaterialTheme.typography.titleMedium)
            Text(text = "Tel: ${proveedor.telefono}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Email: ${proveedor.email}", style = MaterialTheme.typography.bodyMedium)
            val suministrados = proveedor.medicamentosSuministrados
            Text(
                text = if (suministrados.isEmpty()) {
                    "Sin medicamentos registrados"
                } else {
                    "Suministra (${suministrados.size}): ${suministrados.joinToString { it.nombre }}"
                },
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaProveedoresPreview() {
    Parcial1Theme {
        ListaProveedoresScreen(proveedoresDeEjemplo())
    }
}
