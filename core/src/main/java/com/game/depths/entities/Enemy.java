package com.game.depths.entities;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.depths.DataBaseConnection;
import com.game.depths.navigation.NavGraph;
import com.game.depths.navigation.NavNode;
import com.game.depths.navigation.Pathfinding;
import com.badlogic.gdx.ai.pfa.DefaultGraphPath;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Enemy {
    private String nombre;
    private Rectangle hitbox;
    private static Texture textura;
    private Vector2 position;
    private final NavGraph graph;
    private final Pathfinding pathfinding;
    private final GraphPath<NavNode> path;
    private int currentNodeIndex;
    private int damage;
    private int hp;

    public Enemy(Vector2 startPosition, NavGraph graph, String nombre)  {
        this.nombre = nombre;
        this.position = startPosition;
        this.graph = graph;
        this.pathfinding = new Pathfinding(graph);
        this.path = new DefaultGraphPath<>();
        this.currentNodeIndex = 0;
        this.hitbox = new Rectangle();
        this.hitbox.x = position.x;
        this.hitbox.y = position.y;
        this.hitbox.width = 16;
        this.hitbox.height = 16;
        try {
            textura = new Texture(""+nombre+".png");
        }catch (Exception e){
            textura = new Texture("Slime");
            System.out.println("No existe asset para "+nombre+", se uso Slime en su lugar");
        }
        ResultSet resultSet = DataBaseConnection.query("SELECT * FROM monster WHERE nombre = ?",nombre);
        try {
            while (resultSet.next()) {
                    this.damage = resultSet.getInt("daño");
                    this.hp = resultSet.getInt("vida");
                }
            resultSet.close();
            }
         catch (SQLException e) {
            System.out.println("Query no exitosa");
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }



    private void findPathToPlayer(Player player) {
        NavNode startNode = graph.getNodeAt(position);
        NavNode endNode = graph.getNodeAt(player.getPosition());
        pathfinding.searchNodePath(startNode, endNode, path);
        currentNodeIndex = 0;
    }
    public Texture getTextura(){
        return Enemy.textura;
    }

    private void moveAlongPath() {
        if (currentNodeIndex < path.getCount()) {
            NavNode nextNode = path.get(currentNodeIndex);
            position.x = nextNode.getPosition().x;
            position.y = nextNode.getPosition().y;
            currentNodeIndex++;
        } else {
            path.clear();
        }
    }
}
