package com.game.depths.navigation;

import com.badlogic.gdx.math.Vector2;

public class NavNode {
    private final Vector2 position;

    public NavNode(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
}



