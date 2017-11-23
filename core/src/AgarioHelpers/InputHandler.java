/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgarioHelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.PrimaryBacterium;

/**
 *
 * @author npatutina
 */
public class InputHandler implements InputProcessor {

    private PrimaryBacterium _bacterium;

    public InputHandler(PrimaryBacterium bacterium) {
       _bacterium = bacterium;
    }
    
   @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        _bacterium.onClick();
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
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