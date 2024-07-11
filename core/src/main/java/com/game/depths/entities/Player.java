package com.game.depths.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.depths.Weapon;


public class Player {
    private int nivel_actual;
    private Weapon arma_actual;
    private int vida;
    private int vida_maxima;
    private int kills;
    public Rectangle hitbox;
    private Vector2 position;


    public Player(int nivel_actual, int vida, int vida_maxima,Vector2 startPosition) {
        this.nivel_actual = nivel_actual;
        this.vida = vida;
        this.vida_maxima = vida_maxima;
        this.kills = 0;
        this.position = startPosition;
        this.hitbox = new Rectangle();
        hitbox.x = (int) position.x;
        hitbox.y = (int) position.y;
        hitbox.width = 78;
        hitbox.height = 91;
    }
    public void ataque(Enemy enemy) {
        arma_actual.setPosition(position);
        arma_actual.atacar(enemy);
        if (enemy.getHp() <= 0) {
            kills++;
        }
    }

    public void asignarArma(Weapon weapon) {
        this.arma_actual = weapon;
    }

    public int getNivel_actual() {
        return nivel_actual;
    }

    public void setNivel_actual(int nivel_actual) {
        this.nivel_actual = nivel_actual;
    }

    public Weapon getArma_actual() {
        return arma_actual;
    }

    public void setArma_actual(Weapon arma_actual) {
        this.arma_actual = arma_actual;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida_maxima() {
        return vida_maxima;
    }

    public void setVida_maxima(int vida_maxima) {
        this.vida_maxima = vida_maxima;
    }

    public void takeDamage(int damage) {
        this.vida -= damage;
    }

    public boolean isAlive() {
        return vida > 0;
    }

    public int getKills() {
        return kills;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void incrementarKills() {
        this.kills++;
    }


}

