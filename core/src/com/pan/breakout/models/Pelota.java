package com.pan.breakout.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.pan.breakout.BreakoutGame.sonidos;
import static com.pan.breakout.Constants.VIRTUAL_HEIGHT;
import static com.pan.breakout.Constants.VIRTUAL_WIDTH;
import static com.pan.breakout.screens.GameScreen.pelotas;

public class Pelota {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int width;
    private int height;
    private TextureRegion textureRegion;

    public Pelota(int color){
        this.textureRegion = pelotas.get(color);
        this.width = 8;
        this.height = 8;
        this.dx = 0;
        this.dy = 0;
    }

    public void mover(float dt) {
        this.x += this.dx * dt;
        this.y += this.dy * dt;
        if (this.x > VIRTUAL_WIDTH - this.width) {
            sonidos.get("wall_hit").play();
            this.x = VIRTUAL_WIDTH - this.width;
            this.dx = -this.dx;
        }
        if (this.x < 0) {
            sonidos.get("wall_hit").play();
            this.x = 0;
            this.dx = -this.dx;
        }
        if (this.y > VIRTUAL_HEIGHT - this.height) {
            sonidos.get("wall_hit").play();
            this.y = VIRTUAL_HEIGHT - this.height;
            this.dy = -this.dy;
        }
    }

    public void reset() {
        this.x = VIRTUAL_WIDTH / 2 - 4;
        this.y = VIRTUAL_HEIGHT / 2 - 4;
        this.dx = 0;
        this.dy = 0;
    }

    public boolean collides(Colisionable target) {
        if (x > target.getX() + target.getWidth() || target.getX() > x + width) {
            return false;
        }
        if (y > target.getY() + target.getHeight() || target.getY() >y + height) {
            return false;
        }
        // If none of the above conditions are met, there is a collision
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return this.dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
