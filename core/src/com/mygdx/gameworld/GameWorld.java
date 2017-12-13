/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.Azotobacter;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.PredatoryBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;
import com.mygdx.gameobjects.Bifidobacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import com.mygdx.gameobjects.Staphylococcus;
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
        for(int i = 0; i < maxCountOfBacteriums/5; i++){
            _bacteriums.add(new Bifidobacterium (this));
        }
        for(int i = 0; i < maxCountOfBacteriums/5; i++){
            _bacteriums.add(new Staphylococcus (this));
        }
        for(int i = 0; i < maxCountOfBacteriums/5; i++){
            _bacteriums.add(new Azotobacter (this));
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
        //Ничего не делаем
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
        //TODO если один них хищник меньшегоо радиуса
        if(bacter instanceof PredatoryBacterium && other instanceof SimpleBacterium) {
            ((PredatoryBacterium )bacter).eat(other);
            return other;
        }
        if(other instanceof PredatoryBacterium && bacter instanceof SimpleBacterium){
            ((PredatoryBacterium )other).eat(bacter);
            return bacter;
        }
        if (other instanceof SimpleBacterium && bacter instanceof SimpleBacterium)
            return null;
        
        if (bacter.getRadius() > other.getRadius()) {
            if ( bacter instanceof PredatoryBacterium){
                ((PredatoryBacterium )bacter).eat(other);
                return other;
            }
        } else {
            if ( bacter instanceof PredatoryBacterium){
                ((PredatoryBacterium )other).eat(bacter);
                return bacter;
            }
        }
        return null;
    }
    
    private void createNewBacteriums(){
        int maxNewValue = maxCountOfBacteriums - _bacteriums.size();
        Random random = new Random();
        if (maxNewValue>0) {
            int addCount = random.nextInt() % maxNewValue;
            for(int i = 0; i < addCount; i++){
                if (random.nextInt()%3 == 0){
                    _bacteriums.add(new Bifidobacterium (this));
                }
                if (random.nextInt()%7 == 0){
                    _bacteriums.add(new Azotobacter (this));
                }
                if (random.nextInt()%7 == 0){
                    _bacteriums.add(new Staphylococcus (this));
                }
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
            
            _bacteriums.add(new Bifidobacterium (this));
        }
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
}
