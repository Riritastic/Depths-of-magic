package com.game.depths.navigation;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.indexed.IndexedGraph;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.math.Vector2;

public class NavGraph implements IndexedGraph<NavNode> {
    private final Array<NavNode> nodes;
    private final ObjectMap<NavNode, Array<Connection<NavNode>>> connections;

    public NavGraph() {
        nodes = new Array<>();
        connections = new ObjectMap<>();
    }

    public void addNode(NavNode node) {
        nodes.add(node);
    }

    public void connectNodes(NavNode fromNode, NavNode toNode) {
        if (!connections.containsKey(fromNode)) {
            connections.put(fromNode, new Array<>());
        }
        connections.get(fromNode).add(new NavConnection(fromNode, toNode));
    }

    public NavNode getNodeAt(Vector2 position) {
        for (NavNode node : nodes) {
            if (node.getPosition().equals(position)) {
                return node;
            }
        }
        return null;
    }

    @Override
    public int getIndex(NavNode node) {
        return nodes.indexOf(node, true);
    }

    @Override
    public int getNodeCount() {
        return nodes.size;
    }

    @Override
    public Array<Connection<NavNode>> getConnections(NavNode fromNode) {
        return connections.get(fromNode);
    }
}

