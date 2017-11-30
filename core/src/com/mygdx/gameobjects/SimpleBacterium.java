/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import java.util.Random;

/**
 *
 * @author npatutina
 */
public class SimpleBacterium extends PrimaryBacterium {
    
    private final Random random = new Random();
    private int velocityMax = 8;
    public SimpleBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }
    public SimpleBacterium(GameWorld world) {
        super();
        _world = world;
        do {
           position.x = abs(random.nextInt() % 120);
           position.y = abs(random.nextInt() % 120);
           radius = abs(random.nextInt() % 7);
        } while (_world.containsBacterium(position, radius + 5));
           float r = abs(random.nextFloat());
           float g = abs(random.nextFloat());
           float b = abs(random.nextFloat());
           _color = new Color((int)(Math.random() * 0x1000000));
           diffVelocity = abs(velocityMax - radius);
    }

    @Override
    public void update(float delta) {
        switch (abs(random.nextInt()) % velocityMax){
            case 0:
                velocity.x = (float) exp((diffVelocity)*radius/5);
                break;
            case 1:
                velocity.x = - (float) exp((diffVelocity)*radius/5);
                break;
            case 2:
                velocity.y = (float) exp((diffVelocity)*radius/5);
                break;
            case 3:
                velocity.y = - (float) exp((diffVelocity)*radius/5);
                break;
        }
        if (velocity.x > velocityMax)
            velocity.x = velocityMax;
        if (velocity.y > velocityMax)
            velocity.y = velocityMax;
        if (velocity.x < -velocityMax)
            velocity.x  = -velocityMax;
        if (velocity.y < -velocityMax)
            velocity.y = -velocityMax;
        position.add(velocity.cpy().scl(delta));
   /*     if (position.x < 0 - radius){
            position.x = 135 + radius;
        }
         if (position.y < 0- radius){
            position.y = 135 + radius;
         }
         if (position.x > 135 +radius){
            position.x = 0 - radius;
         }
         if (position.y > 135 + radius){
            position.y = 0 - radius;
         }*/
         
         
    }
}
