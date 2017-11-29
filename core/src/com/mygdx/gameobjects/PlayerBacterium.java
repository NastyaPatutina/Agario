/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public class PlayerBacterium extends PrimaryBacterium{
    
    
    private String nameUser = "User";
    
    public PlayerBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
        _color = Color.GOLD;
        diffVelocity = 20;
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
        
        position.add(velocity.cpy().scl(delta));
    }

    public void right() {
        velocity.x+=diffVelocity;
    }
    public void left() {
        velocity.x-=diffVelocity;
        
    }
    public void up() {
        velocity.y-=diffVelocity;
    }
    public void down() {
        velocity.y+=diffVelocity;
    }
    
    public void rightLow() {
        velocity.x=0;
    }
    public void leftLow() {
        velocity.x=0;
        
    }
    public void upLow() {
        velocity.y=0;
    }
    public void downLow() {
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
}
