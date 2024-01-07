package com.pan.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pan.breakout.BreakoutGame;

import static com.pan.breakout.BreakoutGame.*;
import static com.pan.breakout.Constants.VIRTUAL_HEIGHT;
import static com.pan.breakout.Constants.VIRTUAL_WIDTH;

public class StartScreen implements Screen {

    int highlighted = 1;
    final BreakoutGame breakoutGame;

    private boolean keyIsPressed = false;
    private float pressDelay = 0.2f;
    private float timeSinceLastPress = 0;


    public StartScreen(BreakoutGame breakoutGame) {
        this.breakoutGame = breakoutGame;
        camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);




    }

    @Override
    public void render(float delta) {
        float dt = Gdx.graphics.getDeltaTime();
        breakoutGame.batch.begin();
        breakoutGame.batch.draw(breakoutGame.textures.get(1), 0, -1, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        breakoutGame.escribirTextoL("BREAKOUT", VIRTUAL_WIDTH / 4, VIRTUAL_HEIGHT / 2 + 100, 1.5f);
        renderHighlighted();
        breakoutGame.batch.end();
        seleccionMenu(dt);
        musica.play();
        eligeOpcion();


    }

    private void renderHighlighted() {
        if (highlighted == 1) {
            fontM.setColor(1, 1, 0.5f, 0.8f);
        } else {
            fontM.setColor(1, 1, 1, 1);
        }
        breakoutGame.escribirTextoM("START", VIRTUAL_WIDTH / 4, VIRTUAL_HEIGHT /2 + 20, 0.8f);
        if (highlighted == 2) {
            fontM.setColor(1, 1, 0.5f, 0.8f);
        } else {
            fontM.setColor(1, 1, 1, 1);
        }
        breakoutGame.escribirTextoM("HIGH SCORES", VIRTUAL_WIDTH / 4, VIRTUAL_HEIGHT / 2 - 10, 0.8f);
    }

    private void eligeOpcion() {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (highlighted == 1) {
                breakoutGame.setScreen(new GameScreen(breakoutGame));
            } else if (highlighted == 2) {
                breakoutGame.setScreen(new HighScoresScreen(breakoutGame));
            }
        }
    }

    private void seleccionMenu(float delta) {
        if (keyIsPressed) {
            timeSinceLastPress += delta;
            if (timeSinceLastPress >= pressDelay) {
                keyIsPressed = false;
            }
        }


        if (!keyIsPressed) {
            if (Gdx.input.isKeyPressed(19) || Gdx.input.isKeyPressed(20)) {
                highlighted = highlighted == 1 ? 2 : 1;
                sonidos.get("paddle_hit").play();
                keyIsPressed = true;
                timeSinceLastPress = 0;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }


    @Override
    public void show() {

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

    }
}
