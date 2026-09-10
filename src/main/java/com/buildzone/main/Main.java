package com.buildzone.main;

import com.buildzone.dao.ProductoDAO;
import com.buildzone.model.Producto;

public class Main {

    public static void main(String[] args) {
        ProductoDAO productoDAO = new ProductoDAO();

        System.out.println("=== BUILDZONE - PRUEBA CRUD JDBC ===");

        Producto producto = new Producto(
                1,
                1,
                "NVIDIA GeForce RTX 4070",
                "Tarjeta gráfica para computadores de alto rendimiento.",
                "rtx4070.jpg"
        );

        // INSERTAR
        if (productoDAO.insertar(producto)) {
            System.out.println("Producto insertado correctamente.");
            System.out.println(producto);
        }

        // CONSULTAR
        System.out.println("\nProductos registrados:");
        productoDAO.consultarTodos().forEach(System.out::println);

        // CONSULTAR POR ID
        Producto productoConsultado =
                productoDAO.consultarPorId(producto.getIdProducto());

        if (productoConsultado != null) {
            System.out.println("\nProducto consultado por ID:");
            System.out.println(productoConsultado);
        }

        // ACTUALIZAR
        producto.setNombre("NVIDIA GeForce RTX 4070 SUPER");
        producto.setDescripcion("Tarjeta gráfica actualizada para alto rendimiento.");

        if (productoDAO.actualizar(producto)) {
            System.out.println("\nProducto actualizado correctamente.");
        }

        // ELIMINAR
        if (productoDAO.eliminar(producto.getIdProducto())) {
            System.out.println("Producto eliminado correctamente.");
        }

        System.out.println("\n=== FIN DE LA PRUEBA ===");
    }
}
