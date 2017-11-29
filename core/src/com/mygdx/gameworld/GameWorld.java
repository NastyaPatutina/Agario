/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import java.util.ArrayList;

/**
 *
 * @author anast
 */
public class GameWorld {
    
    private PlayerBacterium _primaryBacterium;
    private ArrayList<SimpleBacterium> _bacteriums = new ArrayList();
    int maxCountOfBacteriums = 30;
    
    public GameWorld(int midPointY) {
        _primaryBacterium = new PlayerBacterium(33, midPointY - 5, 6, this);
        for(int i = 0; i < maxCountOfBacteriums; i++){
            _bacteriums.add(new SimpleBacterium(this));
        }
    }

    public void update(float delta) {
        _primaryBacterium.update(delta);
        for(SimpleBacterium bacter:_bacteriums ){
            bacter.update(delta);
        }
    }

    public ArrayList<SimpleBacterium> getSimpleBacteriums() {
        return _bacteriums;

    }

    public PlayerBacterium getPlayerBacterium() {
        return _primaryBacterium;
    }
    
    public boolean containsBacterium(Vector2 position, int radius){
        if(_primaryBacterium.intersect(position, radius))
            return true;
        for(SimpleBacterium bacter : _bacteriums) {
            if(bacter.intersect(position, radius))
                return true;
        }
        return false;
    }
}
