/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Alefe Lucas
 */
public abstract class SystemImageUtil {

    /**
     * Image directory
     */
    public static final String IMAGE_RES = "br/com/senaimg/wms/view/images/";

    /**
     * An "Image not found" image 512x512
     */
    public static final String DEFAULT = "default.png";

    /**
     * An default user image
     */
    public static final String DEFAULT_USER = "defaultuser512.png";

    /**
     * An default user exists image
     */
    public static final String DEFAULT_USER_ON = "defaultuseron512.png";

    /**
     * A forklift wallpaper
     */
    public static final String FORKLIFT = "forklift.png";

    /**
     * SENAI logo whose height is 128
     */
    public static final String SENAI_LOGO_128 = "senailogo128.png";

    /**
     * SENAI logo whose height is 256
     */
    public static final String SENAI_LOGO_256 = "senailogo256.png";

    /**
     * A visionary wallpaper 1920x1200
     */
    public static final String VISIONARY = "visionary.png";
    
  
    public static final String TIPS = "tips.png";

   
    public static final String TIPS_BLACK = "tipsBlack.png";

    /**
     * A warehouse wallpaper 1600x1000
     */
    public static final String WAREHOUSE = "warehouse.png";
public static final String WALLPAPER11 = "warehouse11.jpg";
    public static final String WALLPAPER12 = "warehouse12.jpg";
    public static final String WALLPAPER1 = "warehouse1.jpg";
    public static final String WALLPAPER2 = "warehouse2.jpg";
    public static final String WALLPAPER3 = "warehouse3.jpg";
    public static final String WALLPAPER4 = "warehouse4.jpg";
    public static final String WALLPAPER5 = "warehouse5.jpg";
    public static final String WALLPAPER6 = "warehouse6.jpg";
    public static final String WALLPAPER7 = "warehouse7.jpg";
    public static final String WALLPAPER8 = "warehouse8.jpg";
    public static final String WALLPAPER9 = "warehouse9.jpg";
    public static final String WALLPAPER10 = "warehouse10.jpg";
    

    /**
     * A warehouse wallpaper 1920x1080
     */
    public static final String WAREHOUSE_WIDE = "warehouseWide.png";

    /**
     * The WMS icon 16px
     */
    public static final String WMSICON_16 = "wmsicon16.png";

    /**
     * The WMS icon 32px
     */
    public static final String WMSICON_32 = "wmsicon32.png";

    /**
     * The WMS icon 64px
     */
    public static final String WMSICON_64 = "wmsicon64.png";

    /**
     * The WMS icon 128px
     */
    public static final String WMSICON_128 = "wmsicon128.png";

    /**
     * The WMS icon 256px
     */
    public static final String WMSICON_256 = "wmsicon256.png";

    /**
     * The WMS icon 512px
     */
    public static final String WMSICON_512 = "wmsicon512.png";

    /**
     * The Pyxis logo - 128px
     */
    public static final String PYXIS_128 = "pyxis128.png";

    /**
     * The Pyxis logo - 256px
     */
    public static final String PYXIS_256 = "pyxis256.png";

    /**
     * The Pyxis logo - 512px
     */
    public static final String PYXIS_512 = "pyxis512.png";

    /**
     * The Pyxis logo - for white background - 128px
     */
    public static final String PYXIS_128_BLACK = "pyxis128black.png";

    /**
     * The Pyxis logo - for white background - 256px
     */
    public static final String PYXIS_256_BLACK = "pyxis256black.png";

    /**
     * The Pyxis logo - for white background - 512px
     */
    public static final String PYXIS_512_BLACK = "pyxis512black.png";

    /**
     * A forklift wallpaper
     */
    public static final String FUNC_SAMPLE = "funcSample.png";

    /**
     * Get an image located in br/com/senaimg/wms/view/images/ given its name.
     * If image not found, a default image is returned.
     *
     * @param name Name of the image you want.
     * @return Image - The JavaFx Image object.
     */
    public static Image getImage(String name) {
        String path = IMAGE_RES + name;
        Image imageFx;
        try {
            imageFx = new Image(path);
        } catch (NullPointerException | IllegalArgumentException ex) {
            System.out.println("IMAGE NOT FOUND: " + IMAGE_RES + name);
            path = IMAGE_RES + DEFAULT;
            imageFx = new Image(path);
            name = DEFAULT;
        }
        return imageFx;
    }

    /**
     * Gets a shuffled list of wallpapers
     *
     * @return Wallpaper list
     */
    public static List<String> getWallpapers() {
        List<String> wallpapers = new ArrayList<>();
        wallpapers.add(FORKLIFT);
        wallpapers.add(WAREHOUSE_WIDE);
        wallpapers.add(WAREHOUSE);
        wallpapers.add(VISIONARY);
        wallpapers.add(WALLPAPER1);
        wallpapers.add(WALLPAPER2);
        wallpapers.add(WALLPAPER3);
        wallpapers.add(WALLPAPER4);
        wallpapers.add(WALLPAPER5);
        wallpapers.add(WALLPAPER6);
        wallpapers.add(WALLPAPER7);
        wallpapers.add(WALLPAPER8);
        wallpapers.add(WALLPAPER9);
        wallpapers.add(WALLPAPER11);
        wallpapers.add(WALLPAPER12);
        wallpapers.add(WALLPAPER10);

        Collections.shuffle(wallpapers);
        return wallpapers;
    }

}
