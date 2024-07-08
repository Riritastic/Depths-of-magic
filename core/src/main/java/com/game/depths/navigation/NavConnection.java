package com.game.depths.navigation;

import com.badlogic.gdx.ai.pfa.DefaultConnection;

public class NavConnection extends DefaultConnection<NavNode> {

    public NavConnection(NavNode fromNode, NavNode toNode) {
        super(fromNode, toNode);
    }
}
