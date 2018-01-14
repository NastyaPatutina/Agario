/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.gameobjects.connect.Changing;
import com.mygdx.gameobjects.connect.Improves;
import com.mygdx.gameobjects.connect.MultiImproves;
import com.mygdx.gameobjects.connect.Toxic;
import com.mygdx.gameworld.GameWorld;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author npatutina
 */
public class SimpleBacterium extends PrimaryBacterium {
    public SimpleBacterium(Changing connection, float x, float y, int radius, GameWorld world) {
        super(connection, x, y, radius, world);
    }    
    
    public SimpleBacterium(Changing connection, GameWorld world, int maxRadius) {
        super(connection);
        _world = world;
        
        if (connection instanceof Improves){
            _color = Color.WHITE;
        } else if (connection instanceof MultiImproves) {
            _color = Color.LIME;            
        }else if (connection instanceof Toxic) {
            _color = Color.RED;            
        }
        do {
           position.x = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           position.y = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           radius = abs(random.nextInt() % maxRadius) + 1;
        } while (_world.containsBacterium(position, (int) (radius + 5)));
    }
    
    private int velocityMax = 8;
    int iterationMax = 200;
    int deffIteration;
    final Random random = new Random();

    
    @Override
    public void update(float delta) {
        PredatoryBacterium dangerBacter  = _world.getNearestPredatoryBacterium(this);
        
        if (dangerBacter != null) {
            if (dangerBacter.getX() > position.x) {
                velocity.x = - changeVelocity();
            } else if (dangerBacter.getX() < position.x) {
                velocity.x = changeVelocity();
            } else {
                velocity.x = 0;
            }

            if (dangerBacter.getY() > position.y) {
                velocity.y = - changeVelocity();
            } else if (dangerBacter.getX() < position.y) {
                velocity.y = changeVelocity();
            } else {
                velocity.y = 0;
            }
        }
        
        if (velocity.x > velocityMax)
            velocity.x = velocityMax;
        if (velocity.y > velocityMax)
            velocity.y = velocityMax;
        if (velocity.x < -velocityMax)
            velocity.x  = -velocityMax;
        if (velocity.y < -velocityMax)
            velocity.y = -velocityMax;
        
        position.x += velocity.cpy().x;
        position.y += velocity.cpy().y;
         
         
    }

}
