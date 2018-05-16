/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import static com.mygdx.gameobjects.Whizzbang.type.TOXIC;
import com.mygdx.gameworld.GameWorld;
import java.util.ArrayList;

/**
 *
 * @author npatutina
 */
public class Whizzbang extends GameObject {

    private final type type;

    public Whizzbang(float x, float y, float radius, GameWorld world, type typeWhizzbang) {
        super(x, y, radius, world);
        this.type = typeWhizzbang;
    }

    public enum type {
        PULL,
        TOXIC
    }
    int lostTime = 500;

    @Override
    public void update(float delta) {
        lostTime--;
        if (type == TOXIC) {
            ArrayList<PrimaryBacterium> arrayBacterium = this.getWorld().getBacteriumInDistance((int) this.position.x, (int) this.position.y, 20);
            for (PrimaryBacterium bacter : arrayBacterium) {
                bacter.lowRadius((float) (bacter.getRadius() * 0.005));
            }
        }
    }

    public type getType() {
        return type;
    }

    public int getTime() {
        return lostTime / 100;
    }

    public boolean isOver() {
        return lostTime <= 0;
    }

}
