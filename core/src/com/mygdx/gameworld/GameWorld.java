/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.gameworld;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.BotBacterium;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.PlayerBotBacterium;
import com.mygdx.gameobjects.PlayerRealBacterium;
import com.mygdx.gameobjects.PredatoryBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import com.mygdx.gameobjects.Whizzbang;
import com.mygdx.gameobjects.connect.Improves;
import com.mygdx.gameobjects.connect.MultiImproves;
import com.mygdx.gameobjects.connect.Toxic;
import com.mygdx.gameworld.areas.Area;
import com.mygdx.gameworld.areas.DinamicArea;
import com.mygdx.gameworld.areas.FastArea;
import com.mygdx.gameworld.areas.LowArea;
import com.mygdx.gameworld.areas.MirrorArea;
import com.mygdx.gameworld.areas.StaticArea;
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
    float _gameHeight;

    public enum GameState {
        READY, RUNNING, GAMEOVER
    }
    private ArrayList<PrimaryBacterium> _bacteriums = new ArrayList();
    private ArrayList<Area> _areas = new ArrayList();
    private ArrayList<Whizzbang> _whizzbangs = new ArrayList();
    int maxCountOfBacteriums;

    public GameWorld(float screenWidth, float screenHeight, boolean isBot) {
        currentState = GameState.READY;
        _screenWidth = screenWidth;
        _screenHeight = screenHeight;
        maxCountOfBacteriums = (int) ((screenHeight * screenWidth) / pow(screenHeight / 5, 2));
        fillworld(isBot);

    }

    public void setGameHeight(float gameHieght) {
        _gameHeight = gameHieght;
        generateAreas();
    }

    public float getGameHeight() {
        return _gameHeight;
    }

    public float getGameWidth() {
        return _screenHeight / (_screenWidth / _gameHeight);
    }

    public float screenWidth() {
        return _screenWidth;
    }

    public void addWhizzbang(Whizzbang.type type, float x, float y) {
        _whizzbangs.add(new Whizzbang(x, y, 3, this, type));
    }

    public float screenHeight() {
        return _screenHeight;
    }

    public float getVelocityMod(PrimaryBacterium bacter) {
        for (Area area : _areas) {
            if (area instanceof DinamicArea && area.getRectangle().contains(bacter.getX(), bacter.getY())) {
                return ((DinamicArea) area).getModificationVelocity();
            }
        }
        return 1;
    }

    private void fillworld(boolean isBot) {
        if (isBot) {
            _bacteriums.add(new PlayerBotBacterium(33, 40, maxRadius, this));
        } else {
            _bacteriums.add(new PlayerRealBacterium(33, 40, maxRadius, this));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new SimpleBacterium(new Improves(), this, maxRadius));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new SimpleBacterium(new Toxic(), this, maxRadius));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new SimpleBacterium(new MultiImproves(), this, maxRadius));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new BotBacterium(new Improves(), this, maxRadius));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new BotBacterium(new Toxic(), this, maxRadius));
        }
        for (int i = 0; i < maxCountOfBacteriums / 7; i++) {
            _bacteriums.add(new BotBacterium(new MultiImproves(), this, maxRadius));
        }
    }

    private void generateAreas() {
        _areas.add(new StaticArea(new Rectangle(0, 0, getGameWidth() / 2, getGameHeight() / 2)));
        _areas.add(new LowArea(new Rectangle(getGameWidth() / 2, getGameHeight() / 2, getGameWidth() / 2, getGameHeight() / 2)));
        _areas.add(new FastArea(new Rectangle(0, getGameHeight() / 2, getGameWidth() / 2, getGameHeight() / 2)));
        _areas.add(new MirrorArea(new Rectangle(getGameWidth() / 2, 0, getGameWidth() / 2, getGameHeight() / 2)));
    }

    public void update(float delta) {
        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case GAMEOVER:
                _bacteriums.clear();
                _areas.clear();
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
        if (!naturalSelection()) {
            return;
        }
        //Добавляем новых бактерий
        Random random = new Random();
        if (random.nextBoolean()) {
            createNewBacteriums();
        }
        //Обновляем бактерии
        for (PrimaryBacterium bacter : _bacteriums) {
            bacter.update(delta);
        }
        //Обновляем 
        ArrayList<Whizzbang> wizznNotForRemove = new ArrayList();
        for (Whizzbang whizzbang : _whizzbangs) {
            whizzbang.update(delta);
            if (!whizzbang.isOver()) {
                wizznNotForRemove.add(whizzbang);
            }
        }
        _whizzbangs = wizznNotForRemove;
    }

    public ArrayList<PrimaryBacterium> getBacteriums() {
        return _bacteriums;

    }

    public ArrayList<Whizzbang> getWhizzbangs() {
        return _whizzbangs;

    }

    public ArrayList<Area> getAreas() {
        return _areas;

    }

    public PlayerBacterium getPlayerBacterium() {
        for (PrimaryBacterium bacter : _bacteriums) {
            if (bacter instanceof PlayerBacterium) {
                return (PlayerBacterium) bacter;
            }
        }
        return null;
    }

    public boolean containsBacterium(Vector2 position, int radius) {
        for (PrimaryBacterium bacter : _bacteriums) {
            if (bacter.intersect(position, radius)) {
                return true;
            }
        }
        return false;
    }

    private PrimaryBacterium naturalSelectionBetween(PrimaryBacterium bacter, PrimaryBacterium other) {
        //TODO если один них хищник меньшегоо радиуса
        if (bacter instanceof PredatoryBacterium && other instanceof SimpleBacterium) {
            if (bacter.getRadius() > other.getRadius()) {
                ((PredatoryBacterium) bacter).eat(other);
                return other;
            } else {
                return null;
            }
        }

        if (other instanceof PredatoryBacterium && bacter instanceof SimpleBacterium) {
            if (bacter.getRadius() <= other.getRadius()) {
                ((PredatoryBacterium) other).eat(bacter);
                return bacter;
            } else {
                return null;
            }
        }

        if (other instanceof SimpleBacterium && bacter instanceof SimpleBacterium) {
            return null;
        }
        if (other instanceof PredatoryBacterium && bacter instanceof PredatoryBacterium) {
            if (bacter.getRadius() > other.getRadius()) {
                ((PredatoryBacterium) bacter).eat(other);
                return other;

            } else {
                ((PredatoryBacterium) other).eat(bacter);
                return bacter;
            }
        }
        return null;
    }

    private void createNewBacteriums() {
        int maxNewValue = maxCountOfBacteriums - _bacteriums.size();
        Random random = new Random();
        if (maxNewValue > 0) {
            int addCount = random.nextInt() % maxNewValue;
            for (int i = 0; i < addCount; i++) {
                if (random.nextInt() % 3 == 0) {
                    _bacteriums.add(new SimpleBacterium(new Improves(), this, maxRadius));
                }
                if (random.nextInt() % 7 == 0) {
                    _bacteriums.add(new SimpleBacterium(new MultiImproves(), this, maxRadius));
                }
                if (random.nextInt() % 7 == 0) {
                    _bacteriums.add(new SimpleBacterium(new Toxic(), this, maxRadius));
                }
                if (random.nextInt() % 2 == 0) {
                    _bacteriums.add(new BotBacterium(new Improves(), this, maxRadius));
                }
                if (random.nextInt() % 2 == 0) {
                    _bacteriums.add(new BotBacterium(new Toxic(), this, maxRadius));
                }
                if (random.nextInt() % 2 == 0) {
                    _bacteriums.add(new BotBacterium(new MultiImproves(), this, maxRadius));
                }
            }
        }
    }

    private boolean naturalSelection() {
        ArrayList<PrimaryBacterium> eatenList = new ArrayList();
        for (PrimaryBacterium bacter : _bacteriums) {
            for (PrimaryBacterium bacterOther : _bacteriums) {
                if (bacter != bacterOther && bacter.intersect(bacterOther) && !eatenList.contains(bacter) && !eatenList.contains(bacterOther)) {
                    eatenList.add(naturalSelectionBetween(bacter, bacterOther));
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

    public void restart(boolean isBot) {
        currentState = GameState.READY;
        _bacteriums.clear();
        _areas.clear();
        //fillworld(isBot);
        //generateAreas();
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public PredatoryBacterium getNearestPredatoryBacterium(PrimaryBacterium currentBacter) {
        float minDistance = 10000;
        PredatoryBacterium minBacter = null;

        for (PrimaryBacterium bacter : _bacteriums) {
            if (currentBacter != bacter && bacter instanceof PredatoryBacterium && (currentBacter.distance(bacter.getX(), bacter.getY()) < minDistance)) {
                minBacter = (PredatoryBacterium) bacter;
                minDistance = currentBacter.distance(bacter.getX(), bacter.getY());
            }
        }
        return minBacter;
    }

    public SimpleBacterium getNearestSimpleBacterium(PrimaryBacterium currentBacter) {
        float minDistance = 10000;
        SimpleBacterium minBacter = null;

        for (PrimaryBacterium bacter : _bacteriums) {
            if (currentBacter != bacter && bacter instanceof SimpleBacterium && bacter.getRadius() <= currentBacter.getRadius() && (currentBacter.distance(bacter.getX(), bacter.getY()) < minDistance)) {
                minBacter = (SimpleBacterium) bacter;
                minDistance = currentBacter.distance(bacter.getX(), bacter.getY());
            }
        }
        return minBacter;
    }

    public Whizzbang getNearestPullWhizzbang(PrimaryBacterium currentBacter) {
        Whizzbang nearest = null;
        float minDistance = 10000;

        for (Whizzbang whizzbang : _whizzbangs) {
            if (whizzbang.getType() == Whizzbang.type.PULL && (currentBacter.distance(whizzbang.getX(), whizzbang.getY()) < minDistance)) {
                nearest = whizzbang;
                minDistance = currentBacter.distance(whizzbang.getX(), whizzbang.getY());
            }
        }
        return nearest;
    }

    public PrimaryBacterium getNearestNotToxicBacterium(PrimaryBacterium currentBacter) {
        float minDistance = 10000;
        PrimaryBacterium minBacter = null;

        for (PrimaryBacterium bacter : _bacteriums) {
            if (currentBacter != bacter && bacter.getRadius() <= currentBacter.getRadius() && !bacter.isToxic() && (currentBacter.distance(bacter.getX(), bacter.getY()) < minDistance)) {
                minBacter = bacter;
                minDistance = currentBacter.distance(bacter.getX(), bacter.getY());
            }
        }
        return minBacter;
    }

    public ArrayList<PrimaryBacterium> getBacteriumInDistance(int x, int y, int distance) {
        ArrayList<PrimaryBacterium> minBacters = new ArrayList();

        for (PrimaryBacterium bacter : _bacteriums) {
            if (bacter.distance(x, y) < distance) {
                minBacters.add(bacter);
            }
        }
        return minBacters;
    }
}
