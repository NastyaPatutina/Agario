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
import java.util.Date;
import java.util.Random;

/**
 *
 * @author npatutina
 */
public abstract class SimpleBacterium extends PrimaryBacterium {
    public SimpleBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }    
    
    public SimpleBacterium(GameWorld world, int maxRadius) {
        super();
        _world = world;
        do {
           position.x = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           position.y = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           radius = abs(random.nextInt() % maxRadius);
        } while (_world.containsBacterium(position, (int) (radius + 5)));
           diffVelocity = abs(velocityMax - radius);
    }
    
    private int velocityMax = 8;
    int iterationMax = 200;
    int deffIteration;
    final Random random = new Random();

    
    @Override
    public void update(float delta) {
        if (deffIteration < 0) {
            switch (abs(random.nextInt()) % velocityMax){
                case 0:
                    velocity.x = changeVelocity();
                    break;
                case 1:
                    velocity.x = - changeVelocity();
                    break;
                case 2:
                    velocity.y = changeVelocity();
                    break;
                case 3:
                    velocity.y = - changeVelocity();
                    break;
                case 4:
                    //velocity.x = 0;
                    break;
                case 5:
                    //velocity.y = 0;
                    break;
                default:
                    break;
            }
            deffIteration = 0;
        }
        deffIteration = iterationMax;
        if (velocity.x > velocityMax)
            velocity.x = velocityMax;
        if (velocity.y > velocityMax)
            velocity.y = velocityMax;
        if (velocity.x < -velocityMax)
            velocity.x  = -velocityMax;
        if (velocity.y < -velocityMax)
            velocity.y = -velocityMax;
        position.add(velocity.cpy().scl(delta));
         
         
    }

    @Override
    float changeVelocity() {
        return (float) exp((diffVelocity)*radius/5);
    }
}
