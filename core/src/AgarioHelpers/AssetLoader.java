/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AgarioHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 *
 * @author npatutina
 */
public class AssetLoader {

    public static Texture texture;
    public static TextureRegion bg;
    public static BitmapFont font;
    public static Skin skin;

    public static void load() {

        font = new BitmapFont(Gdx.files.internal("raw/font-export.fnt"));
        font.getData().setScale(.25f, -.25f);
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        texture = new Texture(Gdx.files.internal("background.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bg = new TextureRegion(texture, 0, 0, 1500, 1500);
        bg.flip(false, true);
    }

    public static void dispose() {
        // Мы должны избавляться от текстур, когда заканчивает работать с объектом в котором есть текстуры
        texture.dispose();
    }

}
