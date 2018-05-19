/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;
import java.util.Random;
import modules.Module;

/**
 *
 * @author npatutina
 */
public class ModuleGame implements Module {
   GameWorld gw;
    Random rnd;
    
    @Override
    public void load(GameRenderer gr, GameWorld gw, Module batch) {
        this.gw = gw;
        if(gr != null){
            gr.setModule(batch);
        }
        this.rnd = new Random(System.currentTimeMillis());
    }

    @Override
    public int run() {
        PlayerBacterium player = gw.getPlayerBacterium();
        PrimaryBacterium food = gw.getNearestNotToxicBacterium(player);
        player.goTo(food);
        return 0;
    }


    @Override
    public void unload() {
        System.out.println("unload");
   }
}
