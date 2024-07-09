package com.game.depths;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.depths.entities.Enemy;
import com.game.depths.entities.Player;
import com.game.depths.navigation.NavGraph;
import com.game.depths.navigation.NavNode;
import com.game.depths.screens.GameScreen;
import com.game.depths.screens.MainMenu;
import elemental2.core.JSONType;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static elemental2.core.Global.JSON;


public class Core extends Game {
    public OrthographicCamera camera;

    public Player heroe;
    public Texture heroeIm;
    public SpriteBatch batch;
    private Texture image;
    public Skin skin; // Añade una variable para almacenar el Skin
    public final ArrayList<Enemy> enemigos = new ArrayList<>();
    public List<Texture> enemigosIm ;
    public NavGraph graph;
    public BitmapFont font;
    @Override
    public void create() {
        camera = new OrthographicCamera();
        graph = new NavGraph();
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        initializeGraph();
        font = new BitmapFont();
        heroe = new Player(1, 200, 200, new Vector2(10,10));
        enemigos.add(new Enemy(new Vector2(100, 100), graph,"Goblin"));
        enemigos.add(new Enemy(new Vector2(200, 200), graph,"Slime"));
        enemigosIm = enemigos.stream().map(Enemy::getTextura).collect(Collectors.toList());
        try {
            Weapon arma = DataBaseConnection.getRandomWeapon();
            Weapon arma1 = new Weapon(arma.getNombre(), arma.getTipo(), arma.getDaño(), arma.getRango(),heroe.getPosition().x,heroe.getPosition().y,5,5);
            heroe.asignarArma(arma);
            System.out.println("Arma asignada al héroe: " + heroe.getArma_actual().toString());
        } catch (SQLException e) {
            System.out.println("Asignación de arma fallo");
        }
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        heroeIm = new Texture("Blue_idle_001.png");
        this.setScreen(new MainMenu(this));
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
    public void render() {
        super.render();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "Kills totales: " + heroe.getKills(), 0, 480);
        batch.draw(image, 140, 210);
        batch.draw(heroeIm,heroe.hitbox.x,heroe.hitbox.y);
        IntStream.range(0, enemigosIm.size()).forEachOrdered(i -> batch.draw(enemigosIm.get(i), (100 * i) + 1, (100 * i) + 1));
        batch.end();
        float speed = 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            heroe.hitbox.y += speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            heroe.hitbox.y -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            heroe.hitbox.x -= speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            heroe.hitbox.x += speed;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){

        }
        if (heroe.hitbox.x < 0)
            heroe.hitbox.x = 0;
        if (heroe.hitbox.x > 800 - 64)
            heroe.hitbox.x = 800 - 64;


    }
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        heroeIm.dispose();
        if (skin != null) {
            skin.dispose();
        }


    }
}
