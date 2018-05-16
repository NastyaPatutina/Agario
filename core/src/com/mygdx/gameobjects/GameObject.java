/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.pow;

/**
 *
 * @author npatutina
 */
public abstract class GameObject {

    public GameObject(float x, float y, float radius, GameWorld world) {
        this.radius = radius;
        position = new Vector2(x, y);
        _world = world;
    }
    
    public GameObject() {
        this.radius = 0;
        position = new Vector2(0, 0);
        _world = null;
    }

    Vector2 position;
    GameWorld _world;
    float radius;

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRadius() {
        return radius;
    }

    public GameWorld getWorld() {
        return _world;
    }

    public float distance(float x, float y) {
        return (float) pow(pow(position.x - x, 2) + pow(position.y - y, 2), 0.5);
    }

    public boolean intersect(GameObject other) {
        if (distance(other.getX(), other.getY()) < pow(pow((double) (radius + other.getRadius()), 2), 0.5)) {
            return true;
        }
        return false;
    }

    public boolean intersect(Vector2 positionOther, int radiusOther) {
        if ((Math.pow((double) (position.x - positionOther.x), 2) + Math.pow((double) (position.y - positionOther.y), 2)) < Math.pow((double) (radius + radiusOther), 2)) {
            return true;
        }
        return false;
    }

    public void setCircle(int x, int y, int radius) {
        this.radius = radius;
        position = new Vector2(x, y);
    }

    public abstract void update(float delta);

}
