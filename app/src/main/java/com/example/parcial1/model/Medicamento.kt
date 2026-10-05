package com.example.parcial1.model

open class Medicamento(
    val id: Int,
    val nombre: String,
    val laboratorio: String,
    var precio: Double,
    var stock: Int,
    val categoria: CategoriaMedicamento
) {
    fun estaDisponible(): Boolean = stock > 0

    fun actualizarStock(cantidad: Int) {
        stock += cantidad
    }

    open fun requiereReceta(): Boolean = false

    open fun descripcion(): String = "$nombre - $laboratorio"
}

class MedicamentoConReceta(
    id: Int,
    nombre: String,
    laboratorio: String,
    precio: Double,
    stock: Int,
    categoria: CategoriaMedicamento,
    val controlado: Boolean
) : Medicamento(id, nombre, laboratorio, precio, stock, categoria) {
    override fun requiereReceta(): Boolean = true
}

class MedicamentoLibre(
    id: Int,
    nombre: String,
    laboratorio: String,
    precio: Double,
    stock: Int,
    categoria: CategoriaMedicamento
) : Medicamento(id, nombre, laboratorio, precio, stock, categoria) {
    override fun requiereReceta(): Boolean = false
}
