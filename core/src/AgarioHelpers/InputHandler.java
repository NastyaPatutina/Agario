/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgarioHelpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.PlayerBacterium;
import com.mygdx.gameobjects.Whizzbang;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MenuScreen;

/**
 *
 * @author npatutina
 */
public class InputHandler implements InputProcessor {

    private PlayerBacterium _bacterium;

    public InputHandler(PlayerBacterium bacterium) {
        _bacterium = bacterium;

        if (_bacterium.getWorld().isReady()) {
            _bacterium.getWorld().start();
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        switch (button) {
            case 0:
                _bacterium.getWorld().addWhizzbang(Whizzbang.type.PULL, screenX * _bacterium.getWorld().getGameWidth() / Gdx.graphics.getWidth(), screenY * _bacterium.getWorld().getGameHeight() / Gdx.graphics.getHeight());
                break;
            case 1:
                _bacterium.getWorld().addWhizzbang(Whizzbang.type.TOXIC, screenX * _bacterium.getWorld().getGameWidth() / Gdx.graphics.getWidth(), screenY * _bacterium.getWorld().getGameHeight() / Gdx.graphics.getHeight());
                break;
            default:
                System.out.println("touchDown");

        }

        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (_bacterium.getWorld().isReady()) {
            _bacterium.getWorld().start();
        }
        _bacterium.keyDown(keycode);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        _bacterium.keyUp(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
