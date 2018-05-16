/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.connect.Improves;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import static java.lang.Math.exp;

/**
 *
 * @author npatutina
 */
public class PlayerRealBacterium extends PlayerBacterium {

    private String nameUser = "User";

    public PlayerRealBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }

    @Override
    public void update(float delta) {

        if (velocity.cpy().y > maxVelocity) {
            velocity.cpy().y = maxVelocity;
        }
        if (velocity.cpy().x > maxVelocity) {
            velocity.cpy().x = maxVelocity;
        }

        position.x += velocity.cpy().x;
        position.y += velocity.cpy().y;

        if (position.x < 0) {
            position.x = _world.getGameWidth();
        }
        if (position.y < 0) {
            position.y = _world.getGameHeight();
        }
        if (position.x > _world.getGameWidth()) {
            position.x = 0;
        }
        if (position.y > _world.getGameHeight()) {
            position.y = 0;
        }
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                left();
                break;
            case Input.Keys.RIGHT:
                right();
                break;
            case Input.Keys.UP:
                up();
                break;
            case Input.Keys.DOWN:
                down();
                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                leftLow();
                break;
            case Input.Keys.RIGHT:
                rightLow();
                break;
            case Input.Keys.UP:
                upLow();
                break;
            case Input.Keys.DOWN:
                downLow();
                break;
        }
    }

    private void right() {
        System.out.println("right");
        velocity.x = changeVelocity();
    }

    private void left() {
        System.out.println("left");
        velocity.x = -changeVelocity();
    }

    private void up() {
        System.out.println("up");
        velocity.y = -changeVelocity();
    }

    private void down() {
        System.out.println("down");
        velocity.y = changeVelocity();
    }

    private void rightLow() {
        velocity.x = 0;
    }

    private void leftLow() {
        velocity.x = 0;

    }

    private void upLow() {
        velocity.y = 0;
    }

    private void downLow() {
        velocity.y = 0;
    }
}
