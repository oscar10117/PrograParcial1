package com.example.parcial1.model

class Farmaceutico(
    val id: Int,
    val nombre: String,
    val cargo: String,
    val salario: Double
) {
    fun atenderVenta(id: Int, fecha: String, cliente: Cliente, items: List<ItemVenta>): Venta {
        val venta = Venta(id, fecha, cliente, this, items)
        cliente.historialCompras.add(venta)
        return venta
    }
}
