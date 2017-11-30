/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author anast
 */


public class GameWorld {
    private GameState currentState;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private PlayerBacterium _playerBacterium;
    private ArrayList<SimpleBacterium> _bacteriums = new ArrayList();
    int maxCountOfBacteriums = 30;
    
    public GameWorld(int midPointY) {
        currentState = GameState.READY;
        _playerBacterium = new PlayerBacterium(33, 40, 6, this);
        for(int i = 0; i < maxCountOfBacteriums; i++){
            _bacteriums.add(new SimpleBacterium(this));
        }
    }

    public void update(float delta) {
       switch (currentState) {
        case READY:
            updateReady(delta);
            break;

        case RUNNING:
        default:
            updateRunning(delta);
            break;
        }
    }
    
    private void updateReady(float delta) {
        // Пока что ничего не делаем
    }
    private void updateRunning(float delta) {
        //Поедание
        if (!naturalSelection()){
            return;
        }
        //Добавляем новых бактерий
        Random random = new Random();
        if (random.nextBoolean()) {
            createNewBacteriums();
        }
        //Обновляем бактерии
        _playerBacterium.update(delta);
        for(SimpleBacterium bacter:_bacteriums ){
            bacter.update(delta);
        }
    }

    public ArrayList<SimpleBacterium> getSimpleBacteriums() {
        return _bacteriums;

    }

    public PlayerBacterium getPlayerBacterium() {
        return _playerBacterium;
    }
    
    public boolean containsBacterium(Vector2 position, int radius){
        if(_playerBacterium.intersect(position, radius))
            return true;
        for(SimpleBacterium bacter : _bacteriums) {
            if(bacter.intersect(position, radius))
                return true;
        }
        return false;
    }
    private PrimaryBacterium naturalSelectionBetween(PrimaryBacterium bacter, PrimaryBacterium other){
        if (bacter.getRadius() > other.getRadius()) {
            bacter.eat((int) other.getRadius());
            return other;
        } else {
            other.eat((int) bacter.getRadius());
            return bacter;
        }
    }
    private void createNewBacteriums(){
        int maxNewValue = maxCountOfBacteriums - _bacteriums.size();
        Random random = new Random();
        if (maxNewValue>0) {
            int addCount = random.nextInt() % maxNewValue;
            for(int i = 0; i < addCount; i++){
                _bacteriums.add(new SimpleBacterium(this));
            }
        }
    }
    
    private boolean naturalSelection(){
        ArrayList<PrimaryBacterium> eatenList = new ArrayList();
        for(SimpleBacterium bacter : _bacteriums){
            if (_playerBacterium!= null && bacter.intersect(_playerBacterium)) {
                eatenList.add(naturalSelectionBetween(bacter,_playerBacterium));
            }
            for(SimpleBacterium bacterOther : _bacteriums){
                if (bacter!= bacterOther && bacter.intersect(bacterOther)) {
                    eatenList.add(naturalSelectionBetween(bacter,bacterOther));
                }
            }
        }
        
        if (eatenList.contains(_playerBacterium)) {
            currentState = GameState.GAMEOVER;
            return false;
        }
        _bacteriums.removeAll(eatenList);
        
        return true;
    }
    
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        _bacteriums.clear();
        _playerBacterium.setCircle(33, 40, 6);
        for(int i = 0; i < maxCountOfBacteriums; i++){
            _bacteriums.add(new SimpleBacterium(this));
        }
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
