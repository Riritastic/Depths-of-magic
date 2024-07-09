package com.game.depths.navigation;

import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.DefaultConnection;
import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.ai.pfa.indexed.IndexedAStarPathFinder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class Pathfinding {
    private final NavGraph graph;
    private final IndexedAStarPathFinder<NavNode> pathFinder;

    public Pathfinding(NavGraph graph) {
        this.graph = graph;
        this.pathFinder = new IndexedAStarPathFinder<>(graph);
    }

    public void searchNodePath(NavNode startNode, NavNode endNode, GraphPath<NavNode> outPath) {
        pathFinder.searchNodePath(startNode, endNode, new Heuristic(), outPath);
    }

    private static class Heuristic implements com.badlogic.gdx.ai.pfa.Heuristic<NavNode> {
        @Override
        public float estimate(NavNode node, NavNode endNode) {
            return node.getPosition().dst(endNode.getPosition());
        }
    }
}
