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
import com.mygdx.gameobjects.Salmonella;
import com.mygdx.gameobjects.SimpleBacterium;
import com.mygdx.gameobjects.Staphylococcus;
import com.mygdx.gameobjects.Teratobacter;
import static java.lang.Math.pow;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author anast
 */


public class GameWorld {
    private GameState currentState;
    float _screenWidth;
    float _screenHeight;
    int maxRadius = 7;
    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private ArrayList<PrimaryBacterium> _bacteriums = new ArrayList();
    int maxCountOfBacteriums;
    
    public GameWorld(float screenWidth, float screenHeight) {
        currentState = GameState.READY;
        _screenWidth = screenWidth;
        _screenHeight = screenHeight;
        maxCountOfBacteriums = (int) ((screenHeight * screenWidth)/pow(maxRadius * 10, 2));
        fillworld();
        
    }
    
    void fillworld(){
        _bacteriums.add(new PlayerBacterium(33, 40, maxRadius, this));
        for(int i = 0; i < maxCountOfBacteriums/6; i++){
            _bacteriums.add(new Bifidobacterium (this, maxRadius));
        }
        for(int i = 0; i < maxCountOfBacteriums/6; i++){
            _bacteriums.add(new Staphylococcus (this, maxRadius));
        }
        for(int i = 0; i < maxCountOfBacteriums/6; i++){
            _bacteriums.add(new Azotobacter (this, maxRadius));
        }
        for(int i = 0; i < maxCountOfBacteriums/6; i++){
            _bacteriums.add(new Teratobacter (this, maxRadius));
        }
        for(int i = 0; i < maxCountOfBacteriums/6; i++){
            _bacteriums.add(new Salmonella (this, maxRadius));
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
        for(PrimaryBacterium bacter:_bacteriums ){
            bacter.update(delta);
        }
    }

    public ArrayList<PrimaryBacterium> getBacteriums() {
        return _bacteriums;

    }

    public PlayerBacterium getPlayerBacterium() {
        for(PrimaryBacterium bacter : _bacteriums) {
            if(bacter instanceof PlayerBacterium)
                return (PlayerBacterium) bacter;
        }
        return null;
    }
    
    public boolean containsBacterium(Vector2 position, int radius){
        for(PrimaryBacterium bacter : _bacteriums) {
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
                    _bacteriums.add(new Bifidobacterium (this, maxRadius));
                }
                if (random.nextInt()%7 == 0){
                    _bacteriums.add(new Azotobacter (this, maxRadius));
                }
                if (random.nextInt()%7 == 0){
                    _bacteriums.add(new Staphylococcus (this, maxRadius));
                }
                if (random.nextInt()%2 == 0){
                    _bacteriums.add(new Salmonella (this, maxRadius));
                }
                if (random.nextInt()%2 == 0){
                    _bacteriums.add(new Teratobacter (this, maxRadius));
                }
            }
        }
    }
    
    private boolean naturalSelection(){
        ArrayList<PrimaryBacterium> eatenList = new ArrayList();
        for(PrimaryBacterium bacter : _bacteriums){
            for(PrimaryBacterium bacterOther : _bacteriums){
                if (bacter!= bacterOther && bacter.intersect(bacterOther)) {
                    eatenList.add(naturalSelectionBetween(bacter,bacterOther));
                }
            }
        }
        
        if (eatenList.contains(getPlayerBacterium())) {
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
        fillworld();
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }
    
    public PredatoryBacterium getNearestPredatoryBacterium(PrimaryBacterium currentBacter) {
       float minDistance = 10000;
       PredatoryBacterium minBacter = null;

       for(PrimaryBacterium bacter : _bacteriums) {
            if(bacter instanceof PredatoryBacterium && (currentBacter.distance(bacter.getX(), bacter.getY()) < minDistance)){
                minBacter = (PredatoryBacterium) bacter;
                minDistance = currentBacter.distance(bacter.getX(), bacter.getY());
            }
        }
       return minBacter;
    }
    
    public SimpleBacterium getNearestSimpleBacterium(PrimaryBacterium currentBacter) {
       float minDistance = 10000;
       SimpleBacterium minBacter = null;

       for(PrimaryBacterium bacter : _bacteriums) {
            if(bacter instanceof SimpleBacterium && (currentBacter.distance(bacter.getX(), bacter.getY()) < minDistance)){
                minBacter = (SimpleBacterium) bacter;
                minDistance = currentBacter.distance(bacter.getX(), bacter.getY());
            }
        }
       return minBacter;
    }
    
}
