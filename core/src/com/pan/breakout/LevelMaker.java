package com.pan.breakout;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.pan.breakout.models.Bloques;

import java.util.ArrayList;

import static com.pan.breakout.Constants.VIRTUAL_HEIGHT;

public class LevelMaker {
    public static Array<Bloques> createMap(){
        Array<Bloques> bloques = new Array<>();
        int numeroColumnas = MathUtils.random(7, 13);
        int numeroFilas = MathUtils.random(1, 5);
        for (int i = 0; i < numeroFilas; i++) {
            for (int j = 0; j < numeroColumnas; j++) {
                bloques.add(new Bloques(j * 32 + 8 + (13 - numeroColumnas) * 16, VIRTUAL_HEIGHT -( (2+ i) * 16) ));
            }
        }
        return bloques;

    }
}
