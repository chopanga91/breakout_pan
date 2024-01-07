package com.pan.breakout.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.pan.breakout.BreakoutGame.sonidos;
import static com.pan.breakout.Constants.VIRTUAL_HEIGHT;
import static com.pan.breakout.Constants.VIRTUAL_WIDTH;
import static com.pan.breakout.screens.GameScreen.paletas;


public class Paleta implements Colisionable {
    public int x;
    public int y;
    public TextureRegion textureRegion;
    public int width;
    public int height;
    public float dx;
    public int tipo;

    public Paleta(int tipo){
        this.tipo = tipo;
        this.textureRegion = paletas.get(tipo);
        if (tipo == 0) {this.width = 32;} else if (tipo == 1){this.width = 64;} else {this.width = 128;};
        this.y = 32;
        this.x = VIRTUAL_WIDTH / 2 - 32;
        this.height = 16;
        this.dx = 0;
    }
    public void mover(float dt){
        this.dx = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.dx = -5;
            } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.dx = 5;
        }
       this.x += this.dx;
        if (this.x > VIRTUAL_WIDTH - this.width) {
            this.x = VIRTUAL_WIDTH - this.width;
        } else if (this.x < 0) {
            this.x = 0;
        }
    }
    public void hit(){
        sonidos.get("paddle_hit").play();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public TextureRegion getTextureRegion() {
        return textureRegion;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public float getDx() {
        return dx;
    }
}
