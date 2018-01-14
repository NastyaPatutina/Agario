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
import java.util.Random;

/**
 *
 * @author npatutina
 */
public class BotBacterium extends PredatoryBacterium {
    
    public BotBacterium(Changing connection, float x, float y, int radius, GameWorld world) {
        super(connection, x, y, radius, world);
    }
      
    public BotBacterium(Changing connection, GameWorld world, int maxRadius) {
        super(connection);
        _world = world;
        if (connection instanceof Improves){
            _color = Color.BLUE;
        } else if (connection instanceof MultiImproves) {
            _color = Color.CYAN;            
        }else if (connection instanceof Toxic) {
            _color = Color.PURPLE;            
        }
        
        do {
           position.x = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           position.y = abs(random.nextInt() % (_world.screenWidth()/maxRadius));
           radius = abs(random.nextInt() % maxRadius) + 1;
        } while (_world.containsBacterium(position, (int) (radius + maxRadius/2)));
        
        setMaxRadius(_world.screenWidth() / 50);
    }
    
    int diffIteration;
    final Random random = new Random();
    
    @Override
    public void update(float delta) {
        
        SimpleBacterium food  = _world.getNearestSimpleBacterium(this);
        
        if (food != null) {
            if (food.getX() > position.x) {
                velocity.x = changeVelocity();
            } else if (food.getX() < position.x) {
                velocity.x = -changeVelocity();
            } else {
                velocity.x = 0;
            }

            if (food.getY() > position.y) {
                velocity.y = changeVelocity();
            } else if (food.getX() < position.y) {
                velocity.y = -changeVelocity();
            } else {
                velocity.y = 0;
            }
        }
        
        if (velocity.x > maxVelocity)
            velocity.x = maxVelocity;
        if (velocity.y > maxVelocity)
            velocity.y = maxVelocity;
        if (velocity.x < -maxVelocity)
            velocity.x  = -maxVelocity;
        if (velocity.y < -maxVelocity)
            velocity.y = -maxVelocity;
        
        position.x += velocity.cpy().x;
        position.y += velocity.cpy().y;
         
         
    }    
}
