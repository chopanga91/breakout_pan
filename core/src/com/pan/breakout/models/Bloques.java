package com.pan.breakout.models;

import static com.pan.breakout.BreakoutGame.sonidos;
import static com.pan.breakout.screens.GameScreen.textureRegions;

public class Bloques implements Colisionable {

    private int x;
    private int y;
    private int width;
    private int height;
    private int color;
    private int tier;

    private boolean inPlay = true;

    public Bloques(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 32;
        this.height = 16;
        this.color = 1;
        this.tier = 0;

    }
    public int render(){
        return  (1 + (color - 1) * 4) + tier; //Me da ladrillos organizados por colores
    }


    public void hit(){
        sonidos.get("brick-hit-2").play();
        inPlay = false;
    }




    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getColor(){
        return color;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInPlay() {
        return inPlay;
    }
}
