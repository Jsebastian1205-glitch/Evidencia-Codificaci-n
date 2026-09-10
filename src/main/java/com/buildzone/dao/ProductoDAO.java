package com.buildzone.dao;

import com.buildzone.config.ConexionBD;
import com.buildzone.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private static final String INSERTAR =
            "INSERT INTO producto (id_marca, id_categoria, nombre, descripcion, imagen) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String CONSULTAR_TODOS =
            "SELECT id_producto, id_marca, id_categoria, nombre, descripcion, imagen " +
            "FROM producto ORDER BY id_producto";

    private static final String CONSULTAR_POR_ID =
            "SELECT id_producto, id_marca, id_categoria, nombre, descripcion, imagen " +
            "FROM producto WHERE id_producto = ?";

    private static final String ACTUALIZAR =
            "UPDATE producto SET id_marca = ?, id_categoria = ?, nombre = ?, " +
            "descripcion = ?, imagen = ? WHERE id_producto = ?";

    private static final String ELIMINAR =
            "DELETE FROM producto WHERE id_producto = ?";

    public boolean insertar(Producto producto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(
                     INSERTAR, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, producto.getIdMarca());
            statement.setInt(2, producto.getIdCategoria());
            statement.setString(3, producto.getNombre());
            statement.setString(4, producto.getDescripcion());
            statement.setString(5, producto.getImagen());

            int filas = statement.executeUpdate();

            if (filas == 0) {
                return false;
            }

            try (ResultSet claves = statement.getGeneratedKeys()) {
                if (claves.next()) {
                    producto.setIdProducto(claves.getInt(1));
                }
            }

            return true;

        } catch (SQLException exception) {
            System.err.println("Error al insertar producto: " + exception.getMessage());
            return false;
        }
    }

    public List<Producto> consultarTodos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(CONSULTAR_TODOS);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                productos.add(mapearProducto(resultado));
            }

        } catch (SQLException exception) {
            System.err.println("Error al consultar productos: " + exception.getMessage());
        }

        return productos;
    }

    public Producto consultarPorId(int idProducto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(CONSULTAR_POR_ID)) {

            statement.setInt(1, idProducto);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    return mapearProducto(resultado);
                }
            }

        } catch (SQLException exception) {
            System.err.println("Error al consultar producto: " + exception.getMessage());
        }

        return null;
    }

    public boolean actualizar(Producto producto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(ACTUALIZAR)) {

            statement.setInt(1, producto.getIdMarca());
            statement.setInt(2, producto.getIdCategoria());
            statement.setString(3, producto.getNombre());
            statement.setString(4, producto.getDescripcion());
            statement.setString(5, producto.getImagen());
            statement.setInt(6, producto.getIdProducto());

            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.err.println("Error al actualizar producto: " + exception.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idProducto) {
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(ELIMINAR)) {

            statement.setInt(1, idProducto);
            return statement.executeUpdate() > 0;

        } catch (SQLException exception) {
            System.err.println("Error al eliminar producto: " + exception.getMessage());
            return false;
        }
    }

    private Producto mapearProducto(ResultSet resultado) throws SQLException {
        return new Producto(
                resultado.getInt("id_producto"),
                resultado.getInt("id_marca"),
                resultado.getInt("id_categoria"),
                resultado.getString("nombre"),
                resultado.getString("descripcion"),
                resultado.getString("imagen")
        );
    }
}
