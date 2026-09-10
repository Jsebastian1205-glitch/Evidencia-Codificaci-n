package com.buildzone.model;

public class Producto {

    private int idProducto;
    private int idMarca;
    private int idCategoria;
    private String nombre;
    private String descripcion;
    private String imagen;

    public Producto() {
    }

    public Producto(int idProducto, int idMarca, int idCategoria,
                    String nombre, String descripcion, String imagen) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public Producto(int idMarca, int idCategoria,
                    String nombre, String descripcion, String imagen) {
        this(0, idMarca, idCategoria, nombre, descripcion, imagen);
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", idMarca=" + idMarca +
                ", idCategoria=" + idCategoria +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
