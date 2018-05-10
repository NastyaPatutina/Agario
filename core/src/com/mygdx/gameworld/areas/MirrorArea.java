/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld.areas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author npatutina
 */
public class MirrorArea extends DinamicArea {

    public MirrorArea(Rectangle rect) {
        super(rect);
        _color = Color.PURPLE;
    }

    @Override
    public float getModificationVelocity() {
        return -1;
    }

}
