package com.game.depths.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.game.depths.Core;
import com.game.depths.entities.Player;
import com.game.depths.entities.Enemy;
import com.game.depths.navigation.NavGraph;
import com.game.depths.navigation.NavNode;

public class GameScreen implements Screen {
    private final OrthographicCamera camera;
    private final SpriteBatch batch;
    private final Array<Enemy> enemies;
    private final Player player;
    private final NavGraph graph;


    public GameScreen(Core game) {
        camera = new OrthographicCamera();
        batch = game.batch;
        graph = new NavGraph();
        enemies = new Array<>();

        // Añadir nodos y conexiones al grafo aquí
        initializeGraph();

        // Inicializar jugador
        player = new Player(1, 200, 200, new Vector2(10,10)); // Posición inicial, salud

        // Inicializar enemigos
        enemies.add(new Enemy(new Vector2(100, 100), graph,"Goblin")); // Posición inicial,  daño
        enemies.add(new Enemy(new Vector2(200, 200), graph,"Slime"));
    }

    private void initializeGraph() {
        NavNode node1 = new NavNode(new Vector2(100, 100));
        NavNode node2 = new NavNode(new Vector2(200, 100));
        NavNode node3 = new NavNode(new Vector2(300, 100));
        NavNode node4 = new NavNode(new Vector2(100, 200));
        NavNode node5 = new NavNode(new Vector2(200, 200));
        NavNode node6 = new NavNode(new Vector2(300, 200));

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);
        graph.addNode(node6);

        graph.connectNodes(node1, node2);
        graph.connectNodes(node2, node3);
        graph.connectNodes(node1, node4);
        graph.connectNodes(node2, node5);
        graph.connectNodes(node3, node6);
        graph.connectNodes(node4, node5);
        graph.connectNodes(node5, node6);
    }

    @Override
    public void render(float delta) {
        /*update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Dibujar jugador y enemigos
        // batch.draw(texture, player.getPosition().x, player.getPosition().y); // Dibujar jugador
        for (Enemy enemy : enemies) {
            // batch.draw(texture, enemy.getPosition().x, enemy.getPosition().y); // Dibujar enemigo
        }
        batch.end();*/
        /*update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Dibujar jugador
        batch.draw(playerTexture, player.getPosition().x, player.getPosition().y);
        // Dibujar enemigos
        for (Enemy enemy : enemies) {
            batch.draw(enemyTexture, enemy.getPosition().x, enemy.getPosition().y);
        }
        batch.end();*/
        if (!player.isAlive()) {
            // Manejar el fin del juego aquí
            return;
        }


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        // Dibujar jugador
        // batch.draw(texture, player.getPosition().x, player.getPosition().y); // Dibujar jugador
        for (Enemy enemy : enemies) {
            // batch.draw(texture, enemy.getPosition().x, enemy.getPosition().y); // Dibujar enemigo
        }
        batch.end();
    }



    private void updatePlayerPosition() {
        // Lógica para actualizar la posición del jugador
        float speed = 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.setPosition(player.getPosition().add(0, speed));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.setPosition(player.getPosition().add(0, -speed));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.setPosition(player.getPosition().add(-speed, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.setPosition(player.getPosition().add(speed, 0));
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
