package com.example.parcial1.model

class Proveedor(
    val id: Int,
    val nombre: String,
    val telefono: String,
    val email: String
) {
    val medicamentosSuministrados: MutableList<Medicamento> = mutableListOf()

    fun agregarMedicamento(m: Medicamento) {
        medicamentosSuministrados.add(m)
    }
}
