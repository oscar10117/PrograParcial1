package com.example.parcial1.model

data class ItemVenta(
    val medicamento: Medicamento,
    val cantidad: Int
) {
    fun subtotal(): Double = medicamento.precio * cantidad
}
