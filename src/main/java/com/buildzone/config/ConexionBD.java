package com.buildzone.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConexionBD {

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {
        Properties propiedades = cargarPropiedades();

        return DriverManager.getConnection(
                propiedades.getProperty("db.url"),
                propiedades.getProperty("db.user"),
                propiedades.getProperty("db.password")
        );
    }

    private static Properties cargarPropiedades() {
        Properties propiedades = new Properties();

        try (InputStream entrada = ConexionBD.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (entrada == null) {
                throw new IllegalStateException(
                        "No se encontró el archivo database.properties."
                );
            }

            propiedades.load(entrada);
            return propiedades;

        } catch (IOException exception) {
            throw new IllegalStateException(
                    "No fue posible cargar la configuración de la base de datos.",
                    exception
            );
        }
    }
}
