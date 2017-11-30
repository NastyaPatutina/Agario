/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgarioHelpers;

import com.badlogic.gdx.InputProcessor;
import com.mygdx.gameobjects.PlayerBacterium;

/**
 *
 * @author npatutina
 */
public class InputHandler implements InputProcessor {

    private PlayerBacterium _bacterium;

    public InputHandler(PlayerBacterium bacterium) {
       _bacterium = bacterium;
    }
    
   @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (_bacterium.getWorld().isReady()) {
            _bacterium.getWorld().start();
        }
        _bacterium.keyDown(keycode);
        
        if (_bacterium.getWorld().isGameOver()) {
            // Обнулим все перменные, перейдем в GameState.READ
            _bacterium.getWorld().restart();
            System.out.println(_bacterium.getWorld().isReady());
        }
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
