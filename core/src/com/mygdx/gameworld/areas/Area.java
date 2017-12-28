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
abstract public class Area {
    private Rectangle _rect;
    Color _color;
    
    public Area (Rectangle rect) {
        _rect = rect;    
    }
    
    public Rectangle getRectangle(){
        return _rect;
    }
    public void setRectangle(Rectangle rect){
        _rect = rect;
    }
    
    public Color getColor(){
        return _color;
    }
    
}
