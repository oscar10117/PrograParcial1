# Modelo de Clases — Caso de Estudio: Farmacia

```mermaid
classDiagram
    class Medicamento {
        +Int id
        +String nombre
        +String laboratorio
        +Double precio
        +Int stock
        +CategoriaMedicamento categoria
        +estaDisponible() Boolean
        +actualizarStock(cantidad: Int) void
        +requiereReceta() Boolean
        +descripcion() String
    }

    class MedicamentoConReceta {
        +Boolean controlado
        +requiereReceta() Boolean
    }

    class MedicamentoLibre {
        +requiereReceta() Boolean
    }

    class CategoriaMedicamento {
        <<enumeration>>
        ANALGESICO
        ANTIBIOTICO
        ANTIALERGICO
        VITAMINA
        ANTIGRIPAL
        DERMATOLOGICO
    }

    class Proveedor {
        +Int id
        +String nombre
        +String telefono
        +String email
        +List~Medicamento~ medicamentosSuministrados
        +agregarMedicamento(m: Medicamento) void
    }

    class Cliente {
        +Int id
        +String nombre
        +String cedula
        +String telefono
        +List~Venta~ historialCompras
    }

    class Farmaceutico {
        +Int id
        +String nombre
        +String cargo
        +Double salario
        +atenderVenta(cliente: Cliente, items: List~ItemVenta~) Venta
    }

    class ItemVenta {
        +Medicamento medicamento
        +Int cantidad
        +subtotal() Double
    }

    class Venta {
        +Int id
        +String fecha
        +Cliente cliente
        +Farmaceutico farmaceutico
        +List~ItemVenta~ items
        +calcularTotal() Double
    }

    class Farmacia {
        +String nombre
        +String direccion
        +List~Medicamento~ inventario
        +List~Proveedor~ proveedores
        +List~Farmaceutico~ empleados
        +agregarMedicamento(m: Medicamento) void
        +buscarPorNombre(nombre: String) Medicamento
        +registrarVenta(venta: Venta) void
    }

    Medicamento <|-- MedicamentoConReceta
    Medicamento <|-- MedicamentoLibre
    Medicamento --> CategoriaMedicamento

    Farmacia "1" o-- "*" Medicamento : inventario
    Farmacia "1" o-- "*" Proveedor : proveedores
    Farmacia "1" o-- "*" Farmaceutico : empleados

    Proveedor "1" o-- "*" Medicamento : suministra

    Venta "1" *-- "*" ItemVenta : contiene
    ItemVenta "*" --> "1" Medicamento

    Venta "*" --> "1" Cliente
    Venta "*" --> "1" Farmaceutico
    Cliente "1" o-- "*" Venta : historialCompras
```

## Descripción de las clases

- **Medicamento**: clase base con los datos comunes de todo producto farmacéutico (nombre, laboratorio, precio, stock, categoría). Es abierta (`open`) para permitir especializarse.
- **MedicamentoConReceta** / **MedicamentoLibre**: subclases que redefinen `requiereReceta()` según si el medicamento necesita prescripción médica o se vende libremente.
- **CategoriaMedicamento**: enumeración con las categorías de medicamentos que maneja la farmacia.
- **Proveedor**: empresa que suministra medicamentos a la farmacia.
- **Cliente**: persona que compra medicamentos; mantiene su historial de compras.
- **Farmaceutico**: empleado que atiende las ventas.
- **ItemVenta**: línea de una venta (un medicamento + cantidad comprada).
- **Venta**: transacción compuesta por uno o más `ItemVenta`, asociada a un cliente y al farmacéutico que la atendió.
- **Farmacia**: clase principal que agrupa el inventario, los proveedores y los empleados, y coordina las operaciones (agregar medicamentos, registrar ventas, buscar productos).

Este modelo se implementa exactamente en Kotlin dentro de `app/src/main/java/com/example/parcial1/model/`.
