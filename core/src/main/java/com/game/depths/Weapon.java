package com.game.depths;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Weapon {
    private String nombre;
    private String tipo;
    private int daño;
    private int rango;
    private Rectangle hitbox;
    private Texture textura;

    public Weapon(String nombre, String tipo, int daño, int rango, float x, float y, float ancho, float alto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.rango = rango;
        this.hitbox = new Rectangle(x, y, ancho, alto);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(float x, float y, float width, float height) {
        this.hitbox.set(x, y, width, height);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDaño() {
        return daño;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", daño=" + daño +
                ", rango=" + rango +
                '}';
    }

}
