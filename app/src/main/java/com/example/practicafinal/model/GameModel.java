package com.example.practicafinal.model;

import java.io.Serializable;

public class GameModel implements Serializable {
    private String name, company, consola;
    private int imagen, precio, buy;

    public GameModel(String name, String company, String consola, int imagen, int precio, int buy) {
        this.name = name;
        this.company = company;
        this.consola = consola;
        this.imagen = imagen;
        this.precio = precio;
        this.buy = buy;
    }

    // TODO: Borrar los constructores que no utilicemos.
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getConsola() { return consola; }
    public void setConsola(String consola) { this.consola = consola; }

    public int getImagen() { return imagen; }
    public void setImagen(int imagen) { this.imagen = imagen; }

    public int getPrecio() { return precio; }
    public void setPrecio(int precio) { this.precio = precio; }

    public boolean getBuy() { return (buy == 0)? false : true; }
    public void setBuy(int buy) { this.buy = buy; }

    @Override
    public String toString() {
        return "GameModel{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", consola='" + consola + '\'' +
                ", imagen=" + imagen +
                ", precio=" + precio +
                ", buy=" + buy +
                '}';
    }
}
