package com.mygdx.gameworld;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gameobjects.BotBacterium;
import com.mygdx.gameobjects.PrimaryBacterium;
import com.mygdx.gameobjects.SimpleBacterium;
import com.mygdx.gameobjects.connect.Improves;
import com.mygdx.gameobjects.connect.Toxic;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class ModuleGameTest {

    @Test
    public void initTest() {
        GameWorld gw = new GameWorld();
        Assert.assertEquals(new Vector2(100 / 3, 100 / 3), gw.getPlayerBacterium().getPosition());
        Assert.assertEquals(7, (int) gw.getPlayerBacterium().getRadius());
    }

    @Test
    public void OneSimpleBacterInTheWorld() {
        GameWorld gw = new GameWorld();
        ArrayList<PrimaryBacterium> bacteriums = new ArrayList();
        bacteriums.add(new SimpleBacterium(new Improves(), 0, 0, 5, gw));
        gw.addBacteriums(bacteriums);

        ModuleGame bot = new ModuleGame();
        bot.load(null, gw, null);
        bot.run();
        Assert.assertEquals(new Vector2((float) 33.0175, (float) 33.0175), gw.getPlayerBacterium().getPosition());
    }

    @Test
    public void OneToxicBacterInTheWorld() {
        GameWorld gw = new GameWorld();
        ArrayList<PrimaryBacterium> bacteriums = new ArrayList();
        bacteriums.add(new SimpleBacterium(new Toxic(), 0, 0, 5, gw));
        gw.addBacteriums(bacteriums);

        ModuleGame bot = new ModuleGame();
        bot.load(null, gw, null);
        bot.run();
        Assert.assertEquals(new Vector2(100 / 3, 100 / 3), gw.getPlayerBacterium().getPosition());

    }

    @Test
    public void OneBigBacterInTheWorld() {
        GameWorld gw = new GameWorld();
        ArrayList<PrimaryBacterium> bacteriums = new ArrayList();
        bacteriums.add(new SimpleBacterium(new Toxic(), 0, 0, 10, gw));
        gw.addBacteriums(bacteriums);

        ModuleGame bot = new ModuleGame();
        bot.load(null, gw, null);
        bot.run();
        Assert.assertEquals(new Vector2(100 / 3, 100 / 3), gw.getPlayerBacterium().getPosition());

    }

    @Test
    public void SomeBactersInWorld() {
        GameWorld gw = new GameWorld();
        ArrayList<PrimaryBacterium> bacteriums = new ArrayList();
        bacteriums.add(new SimpleBacterium(new Improves(), 0, 0, 3, gw));
        bacteriums.add(new SimpleBacterium(new Improves(), 50, 100 / 3, 5, gw));
        bacteriums.add(new BotBacterium(new Toxic(), 10, 10, 5, gw));
        bacteriums.add(new SimpleBacterium(new Toxic(), 90, 10, 5, gw));
        bacteriums.add(new BotBacterium(new Improves(), 80, 80, 10, gw));
        gw.addBacteriums(bacteriums);

        ModuleGame bot = new ModuleGame();
        bot.load(null, gw, null);
        bot.run();
        Assert.assertEquals(new Vector2((float) 32.9825, 100 / 3), gw.getPlayerBacterium().getPosition());
    }

    @Test
    public void OnlyToxicBactersInWorld() {
        GameWorld gw = new GameWorld();
        ArrayList<PrimaryBacterium> bacteriums = new ArrayList();
        bacteriums.add(new SimpleBacterium(new Toxic(), 0, 0, 3, gw));
        bacteriums.add(new SimpleBacterium(new Toxic(), 50, 100 / 3, 5, gw));
        bacteriums.add(new BotBacterium(new Toxic(), 10, 10, 5, gw));
        bacteriums.add(new SimpleBacterium(new Toxic(), 90, 10, 5, gw));
        bacteriums.add(new BotBacterium(new Toxic(), 80, 80, 10, gw));
        gw.addBacteriums(bacteriums);

        ModuleGame bot = new ModuleGame();
        bot.load(null, gw, null);
        bot.run();
        Assert.assertEquals(new Vector2(100 / 3, 100 / 3), gw.getPlayerBacterium().getPosition());
    }
}
