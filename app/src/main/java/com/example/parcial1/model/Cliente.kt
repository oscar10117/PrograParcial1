package com.example.parcial1.model

class Cliente(
    val id: Int,
    val nombre: String,
    val cedula: String,
    val telefono: String
) {
    val historialCompras: MutableList<Venta> = mutableListOf()
}
