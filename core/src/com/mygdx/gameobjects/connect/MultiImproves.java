/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameobjects.connect;

import com.mygdx.gameobjects.PredatoryBacterium;

/**
 *
 * @author npatutina
 */
public class MultiImproves extends Changing {

    public MultiImproves() {
    }

    @Override
    public void changeRadius(PredatoryBacterium bacter, float delta) {
        bacter.addRadius(delta * 2);
    }
}
