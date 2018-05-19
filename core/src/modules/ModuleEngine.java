/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import com.mygdx.gameworld.GameWorld;
import com.mygdx.gameworld.GameRenderer;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author npatutina
 */
public class ModuleEngine {

    public static void main(String args[], GameRenderer gr, GameWorld gw) {

        JFileChooser fileopen = new JFileChooser("C:\\Users\\npatutina\\Desktop\\Agario\\core\\src\\com\\mygdx\\gameworld");
        int ret = fileopen.showDialog(null, "Загрузить");
        String moduleName = null;
        String modulePath = null;
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            moduleName = file.getName().split(".java")[0];
            modulePath = (String) file.getPath();
        }

        ModuleLoader loader = new ModuleLoader(modulePath, ClassLoader.getSystemClassLoader());

        try {
            System.out.print("Executing loading module: ");
            System.out.println(moduleName);

            Class c = loader.loadClass("com.mygdx.gameworld." + moduleName);
            Module execute = (Module) c.newInstance();

            execute.load(gr, gw, execute);
            execute.unload();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
