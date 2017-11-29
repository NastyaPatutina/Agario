/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgarioHelpers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author npatutina
 */

public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg;

    public static void load() {

        texture = new Texture(Gdx.files.internal("background.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 1000, 1000);
        bg.flip(false, true);
    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
    }

}