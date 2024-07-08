package com.game.depths.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.depths.Arma;

public class Player {
    private int nivel;
    public Rectangle hitbox;
    private Vector2 position;
    private int health;
    private Arma armaEquipada;

    public Player(Vector2 startPosition) {
        this.position = startPosition;
        this.hitbox = new Rectangle();
        hitbox.x = position.x;
        hitbox.y = position.y;
        hitbox.width = 78;
        hitbox.height = 91;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isAlive() {
        return health > 0;
    }


}

