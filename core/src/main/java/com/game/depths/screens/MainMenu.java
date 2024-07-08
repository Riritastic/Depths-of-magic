package com.game.depths.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.game.depths.Core;
import com.badlogic.gdx.utils.SerializationException;

public class MainMenu implements Screen {
    private final Core game;
    private final Stage stage;
    private Skin skin;
    private TextButton playButton;

    public MainMenu(final Core game) {
        this.game = game;
        stage = new Stage();

        try {
            skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        } catch (SerializationException e) {
            Gdx.app.error("Skin", "Error loading skin: " + e.getMessage());
            return;  // Salir del constructor si no se puede cargar la skin
        }

        Gdx.input.setInputProcessor(stage);

        TextButton.TextButtonStyle style = skin.get("default", TextButton.TextButtonStyle.class);
        playButton = new TextButton("Play", style);
        playButton.setPosition((float) Gdx.graphics.getWidth() / 2 - playButton.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - playButton.getHeight() / 2);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(playButton);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        if (skin != null) skin.dispose();
    }
}
