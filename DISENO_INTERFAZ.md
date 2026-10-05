# Diseño de Interfaz — Caso de Estudio: Farmacia

La aplicación tiene **dos pantallas**, cada una lista uno de los objetos del modelo (`Medicamento` y `Proveedor`), y se navega entre ellas con una barra de navegación inferior.

## Navegación general

```
┌─────────────────────────────┐
│        TopAppBar             │  ← título de la pantalla activa
├─────────────────────────────┤
│                               │
│     Contenido (LazyColumn)   │  ← lista de tarjetas
│                               │
├─────────────────────────────┤
│  [ Medicamentos ] [Proveedores] │  ← NavigationBar (bottom nav)
└─────────────────────────────┘
```

- `NavHost` + `rememberNavController()` controlan qué pantalla se muestra.
- `NavigationBar` con dos `NavigationBarItem` (uno por pantalla) permite cambiar entre ellas sin perder el estado (`saveState` / `restoreState`).

## Pantalla 1 — Inventario de Medicamentos

Lista los objetos `Medicamento` (incluye `MedicamentoConReceta` y `MedicamentoLibre`).

```
┌─────────────────────────────┐
│ Inventario de Medicamentos   │
├─────────────────────────────┤
│ ┌───────────────────────┐   │
│ │ Amoxicilina 500mg - Bayer│ │
│ │ Categoría: ANTIBIOTICO  │ │
│ │ Precio: $8.50 · Stock:40│ │
│ │ Requiere receta médica  │ │
│ │ Disponible              │ │
│ └───────────────────────┘   │
│ ┌───────────────────────┐   │
│ │ Paracetamol 500mg - ...│ │
│ └───────────────────────┘   │
└─────────────────────────────┘
```

**Componentes de Jetpack Compose usados:**
- `Column` como contenedor de la pantalla
- `TopAppBar` (Material 3) para el título
- `LazyColumn` con `contentPadding` y `verticalArrangement = Arrangement.spacedBy(8.dp)` para la lista eficiente de medicamentos
- `items(medicamentos, key = { it.id })` para iterar con key estable
- `Card` (Material 3) por cada medicamento, con `CardDefaults.cardColors`
- `Text` con distintos `MaterialTheme.typography` (`titleMedium`, `bodyMedium`, `bodySmall`) para jerarquía visual
- `@Preview` para previsualizar sin emulador

## Pantalla 2 — Proveedores

Lista los objetos `Proveedor`, mostrando los medicamentos que cada uno suministra (relación `Proveedor` → `Medicamento`).

```
┌─────────────────────────────┐
│ Proveedores                   │
├─────────────────────────────┤
│ ┌───────────────────────┐   │
│ │ Bayer Laboratorios      │ │
│ │ Tel: +506 2222-1010     │ │
│ │ Email: ventas@bayer.cr  │ │
│ │ Suministra (1): Aspirina│ │
│ └───────────────────────┘   │
│ ┌───────────────────────┐   │
│ │ Genfar Distribuidora    │ │
│ └───────────────────────┘   │
└─────────────────────────────┘
```

**Componentes de Jetpack Compose usados:**
- `Column` + `TopAppBar` para el encabezado
- `LazyColumn` + `items(proveedores, key = { it.id })` para la lista
- `Card` (Material 3) por proveedor, con un color distinto (`secondaryContainer`) para diferenciarla visualmente de la pantalla de medicamentos
- `Text` mostrando datos del proveedor y, de forma condicional, la lista de medicamentos que suministra (`proveedor.medicamentosSuministrados.joinToString`)
- `@Preview` para previsualizar sin emulador

## Componentes compartidos (nivel app)

- `Scaffold` (en `MainActivity`) como contenedor raíz, con `bottomBar` para la navegación
- `NavigationBar` + `NavigationBarItem` + `Icon` (`Icons.AutoMirrored.Filled.List`, `Icons.Default.Person`) para alternar entre pantallas
- `NavHost` + `composable(ruta) { ... }` (de `androidx.navigation:navigation-compose`) para definir las rutas `"medicamentos"` y `"proveedores"`
