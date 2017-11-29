/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import java.util.Random;

/**
 *
 * @author npatutina
 */
public class SimpleBacterium extends PrimaryBacterium {
    
    private final Random random = new Random();
    public SimpleBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }
    public SimpleBacterium(GameWorld world) {
        super();
        _world = world;
        do {
           position.x = abs(random.nextInt() % 120);
           position.y = abs(random.nextInt() % 120);
           radius = abs(random.nextInt() % 8);
        } while (_world.containsBacterium(position, radius + 5));
           float r = abs(random.nextFloat());
           float g = abs(random.nextFloat());
           float b = abs(random.nextFloat());
           _color = new Color((int)(Math.random() * 0x1000000));
           diffVelocity = abs(7 - radius);
    }

    @Override
    public void update(float delta) {
        switch (abs(random.nextInt()) % 4){
            case 0:
                velocity.x+=diffVelocity;
                break;
            case 1:
                velocity.x-=diffVelocity;
                break;
            case 2:
                velocity.y+=diffVelocity;
                break;
            case 3:
                velocity.y-=diffVelocity;
                break;
        }
        if (velocity.x > 20)
            velocity.x -= 10;
        if (velocity.y > 20)
            velocity.y -= 10;
        position.add(velocity.cpy().scl(delta));
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
}
