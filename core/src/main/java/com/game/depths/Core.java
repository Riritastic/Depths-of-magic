package com.game.depths;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.depths.entities.Player;
import com.game.depths.screens.GameScreen;
import com.game.depths.screens.MainMenu;
import elemental2.core.JSONType;

import static elemental2.core.Global.JSON;


public class Core extends Game {

    public Player heroe;
    public Texture heroeIm;
    public SpriteBatch batch;
    private Texture image;
    public Skin skin; // Añade una variable para almacenar el Skin

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        heroeIm = new Texture("Blue_idle_001.png");
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 140, 210);
        batch.draw(heroeIm,heroe.hitbox.x,heroe.hitbox.y);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        if (skin != null) {
            skin.dispose();
        }


    }
}
