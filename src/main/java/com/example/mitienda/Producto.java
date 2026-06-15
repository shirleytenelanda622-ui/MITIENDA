package com.example.mitienda;

public class Producto {
    private String codigo;
    private String nombre;
    private String categoria;
    private String estado;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, String estado, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.estado = estado;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }
}
