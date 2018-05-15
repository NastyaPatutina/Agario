/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.connect.Changing;
import com.mygdx.gameobjects.connect.Toxic;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.pow;

/**
 *
 * @author anast
 */
public abstract class PrimaryBacterium extends GameObject {

    float maxVelocity = 200;
    //private Vector2 acceleration;
    Changing _connection;
    Vector2 velocity;
    private float maxPossibleRadius;

    Color _color;

    public PrimaryBacterium(Changing connection, float x, float y, float radius, GameWorld world) {
        this.radius = radius;
        _connection = connection;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        _world = world;
        maxPossibleRadius = _world.screenWidth() / 40;
    }

    PrimaryBacterium(Changing connection) {
        this.radius = 0;
        _connection = connection;
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
    }

    public Color getColor() {
        return _color;
    }

    public float getMaxRadius() {
        return maxPossibleRadius;
    }

    public void setMaxRadius(float maxRedius) {
        maxPossibleRadius = maxRedius;
    }

    public void addRadius(float incriment) {
        if (radius + incriment < maxPossibleRadius) {
            radius += incriment;
        } else {
            radius = maxPossibleRadius;
        }
    }

    public void lowRadius(float derciment) {
        if (radius - derciment > 0) {
            radius -= derciment;
        } else {
            radius = 1;
        }
    }

    float changeVelocity() {
        return (float) (getMaxRadius() - radius + 1) / maxVelocity * _world.getVelocityMod(this);
    }

    void change(PredatoryBacterium other) {
        _connection.changeRadius(other, this.getRadius() / 3);
    }
    
    public boolean isToxic() {
        return _connection instanceof Toxic;
    }

}
