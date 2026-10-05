package com.example.parcial1.model

class Venta(
    val id: Int,
    val fecha: String,
    val cliente: Cliente,
    val farmaceutico: Farmaceutico,
    val items: List<ItemVenta>
) {
    fun calcularTotal(): Double = items.sumOf { it.subtotal() }
}
