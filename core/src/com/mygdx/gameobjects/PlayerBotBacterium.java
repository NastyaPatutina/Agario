/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects;

import com.mygdx.gameobjects.connect.Changing;
import com.mygdx.gameworld.GameWorld;

/**
 *
 * @author npatutina
 */
public class PlayerBotBacterium extends PlayerBacterium {

    public PlayerBotBacterium(float x, float y, int radius, GameWorld world) {
        super(x, y, radius, world);
    }

    @Override
    public void update(float delta) {
        PrimaryBacterium food = _world.getNearestNotToxicBacterium(this);

        if (food != null) {
            if (food.getX() > position.x) {
                velocity.x = (int) Math.signum(_world.getVelocityMod(this)) * changeVelocity();
            } else if (food.getX() < position.x) {
                velocity.x = -changeVelocity() * (int) Math.signum(_world.getVelocityMod(this));
            } else {
                velocity.x = 0;
            }

            if (food.getY() > position.y) {
                velocity.y = changeVelocity() * (int) Math.signum(_world.getVelocityMod(this));
            } else if (food.getY() < position.y) {
                velocity.y = -changeVelocity() * (int) Math.signum(_world.getVelocityMod(this));
            } else {
                velocity.y = 0;
            }
        }

        if (velocity.x > maxVelocity) {
            velocity.x = maxVelocity;
        }
        if (velocity.y > maxVelocity) {
            velocity.y = maxVelocity;
        }
        if (velocity.x < -maxVelocity) {
            velocity.x = -maxVelocity;
        }
        if (velocity.y < -maxVelocity) {
            velocity.y = -maxVelocity;
        }


        position.x += velocity.cpy().x;
        position.y += velocity.cpy().y;

    }

}
