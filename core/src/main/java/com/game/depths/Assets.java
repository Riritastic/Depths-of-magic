package com.game.depths;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Assets {
    private static final AssetManager asm = new AssetManager();
    public static String assetsPackPath = "assets.pack";
    public static TextureAtlas atlas;

    public static void load(){
        asm.load(assetsPackPath,TextureAtlas.class);
        asm.finishLoading();

        atlas = asm.get(assetsPackPath);
    }

    public static void dispose(){
        asm.dispose();
    }
}
