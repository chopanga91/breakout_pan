package com.pan.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.pan.breakout.BreakoutGame;
import com.pan.breakout.LevelMaker;
import com.pan.breakout.models.Bloques;
import com.pan.breakout.models.Colisionable;
import com.pan.breakout.models.Paleta;
import com.pan.breakout.models.Pelota;

import static com.pan.breakout.BreakoutGame.*;
import static com.pan.breakout.Constants.VIRTUAL_HEIGHT;
import static com.pan.breakout.Constants.VIRTUAL_WIDTH;

public class GameScreen implements Screen {

    private static final int TAMAÑO_PALETA = 1;

    final BreakoutGame breakoutGame;
    public static Array<TextureRegion> textureRegions;
    public static Array<TextureRegion> paletas;
    public static Array<TextureRegion> pelotas;
    public static Array<Bloques> bloques;

    boolean colisionDetectada;

    Paleta paleta;
    Pelota pelota;

    public GameScreen(BreakoutGame breakoutGame) {
        this.breakoutGame = breakoutGame;
        camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        obtenerBloquesDesdeSpriteSheet();
        obtenerPaletasDesdeSpriteSheet();
        obtenerPelotasDesdeSpriteSheet();
        this.paleta = new Paleta(TAMAÑO_PALETA);
        this.pelota = new Pelota(MathUtils.random(0, 6));
        this.pelota.setDx(MathUtils.random(-200, 200));
        this.pelota.setDy(MathUtils.random(-70, -40));
        this.pelota.setX(VIRTUAL_WIDTH / 2 - 4);
        this.pelota.setY(VIRTUAL_HEIGHT / 2 + 50);
        this.bloques = LevelMaker.createMap(); // METODO MAGICO LE DA POSICION A CADA BLOQUE
    }

    private void obtenerBloquesDesdeSpriteSheet() {
        textureRegions = new Array<>();
        int corteX = 0, corteY = 0;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 6; i++) {
                textureRegions.add(new TextureRegion(breakoutGame.textures.get(3), corteX, corteY, 32, 16));
                corteX += 32;
            }
            corteX = 0;
            corteY += 16;
        }
        for (int i = 0; i < 3; i++) {
            textureRegions.add(new TextureRegion(breakoutGame.textures.get(3), corteX, corteY, 32, 16));
            corteX += 32;
        }
    }

    private void obtenerPaletasDesdeSpriteSheet() {
        paletas = new Array<>();
        int corteX = 0, corteY = 64, width = 32;
        for (int i = 0; i < 6; i++) {
            paletas.add(new TextureRegion(breakoutGame.textures.get(3), corteX, corteY, width, 16));
            corteX += width;
            width *= 2;
        }
    }

    private void obtenerPelotasDesdeSpriteSheet() {
        pelotas = new Array<>();
        int corteX = 96, corteY = 48, width = 32;
        for (int i = 0; i < 4; i++) {
            pelotas.add(new TextureRegion(breakoutGame.textures.get(3), corteX, corteY, 8, 8));
            corteX += 8;
        }
        corteX = 96;
        corteY = 56;
        for (int i = 0; i < 3; i++) {
            pelotas.add(new TextureRegion(breakoutGame.textures.get(3), corteX, corteY, 8, 8));
            corteX += 8;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        float dt = Gdx.graphics.getDeltaTime();
        breakoutGame.batch.begin();
        breakoutGame.batch.draw(breakoutGame.textures.get(1), 0, -1, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        posicionarBloques();
        dibujarPaleta();
        dibujarPelota();
        breakoutGame.batch.end();
        paleta.mover(dt);
        pelota.mover(dt);
        detectarColision(dt, paleta);
        for (Bloques bloque : bloques) {
            if (bloque.isInPlay()) {
                detectarColision(dt, bloque);
            }
        }
        camera.update();

    }

    private void dibujarPaleta() {
        breakoutGame.batch.draw(paleta.getTextureRegion(), paleta.getX(), paleta.getY(), paleta.width, paleta.height);
    }

    private void dibujarPelota() {
        breakoutGame.batch.draw(pelota.getTextureRegion(), pelota.getX(), pelota.getY(), pelota.getWidth(), pelota.getHeight());
    }

    private void posicionarBloques() {
        for (Bloques bloque : bloques) {
            if (bloque.isInPlay()) {
                breakoutGame.batch.draw(textureRegions.get(bloque.render()), bloque.getX(), bloque.getY(), bloque.getWidth(), bloque.getHeight());
            }
        }
    }

    float time;

    private void detectarColision(float dt, Colisionable target) {
        if (pelota.collides(target) && time > 2f) {
            target.hit();
            // Invertir solo la dirección vertical de la pelota
            pelota.setDy(-pelota.getDy());
            time = 0;
        } else {
            time += dt;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        textureRegions.clear();
        paletas.clear();
        pelotas.clear();
        bloques.clear();
    }
}
