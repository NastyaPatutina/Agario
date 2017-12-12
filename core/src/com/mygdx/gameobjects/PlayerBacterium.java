/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import static java.lang.Math.exp;

/**
 *
 * @author npatutina
 */
public class PlayerBacterium extends PredatoryBacterium{
    
    
    private String nameUser = "User";
    
    public PlayerBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
        _color = Color.GOLD;
        diffVelocity = 3;
    }
    
    @Override
    public void update(float delta) {
        //velocity.add(acceleration.cpy().scl(delta));

        if (velocity.y > 200) {
            velocity.y = 200;
        }
        if (velocity.x > 200) {
            velocity.x = 200;
        }
        if (abs(velocity.x) < 1) {
            velocity.x*=3;
        }
        if (abs(velocity.y) < 1) {
            velocity.y*=3;
        }
        
        position.add(velocity.cpy().scl(delta));
        System.out.println(velocity);
        if (position.x < 0){
            position.x = 135;
        }
         if (position.y < 0){
            position.y = 135;
         }
         if (position.x > 135){
            position.x = 0;
         }
         if (position.y > 135){
            position.y = 0;
         }
    }

    private void right() {
        velocity.x = changeVelocity();
    }
    private void left() {
        velocity.x = - changeVelocity();        
    }
    private void up() {
        velocity.y = - changeVelocity();
    }
    private void down() {
        velocity.y = changeVelocity();
    }
    
    private void rightLow() {
        velocity.x=0;
    }
    private void leftLow() {
        velocity.x=0;
        
    }
    private void upLow() {
        velocity.y=0;
    }
    private void downLow() {
        velocity.y=0;
    }
    
    public void keyDown(int keycode){         
            switch (keycode){
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
    public void keyUp(int keycode){
        switch (keycode){
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

    @Override
    float changeVelocity() {
        return (float)(2 +  exp((diffVelocity)*radius/10));
    }
}
