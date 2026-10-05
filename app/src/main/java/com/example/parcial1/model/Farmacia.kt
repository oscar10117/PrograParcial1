package com.example.parcial1.model

class Farmacia(
    val nombre: String,
    val direccion: String
) {
    val inventario: MutableList<Medicamento> = mutableListOf()
    val proveedores: MutableList<Proveedor> = mutableListOf()
    val empleados: MutableList<Farmaceutico> = mutableListOf()

    fun agregarMedicamento(m: Medicamento) {
        inventario.add(m)
    }

    fun buscarPorNombre(nombre: String): Medicamento? =
        inventario.find { it.nombre.equals(nombre, ignoreCase = true) }

    fun registrarVenta(venta: Venta) {
        venta.items.forEach { it.medicamento.actualizarStock(-it.cantidad) }
    }
}
