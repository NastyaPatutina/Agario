/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author anast
 */
public class PrimaryBacterium {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation; // Для обработки поворота птицы
    private int radius;

    
    public PrimaryBacterium(float x, float y, int radius) {
        this.radius = radius;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 460);
    }
    
    public void update(float delta) {

        velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }

        position.add(velocity.cpy().scl(delta));

    }

    public void onClick() {
        velocity.y = -140;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRadius() {
        return radius;
    }


    public float getRotation() {
        return rotation;
    }

}
