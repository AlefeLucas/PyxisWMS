/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Alefe Lucas
 */
public class LoginBackgroundTransition {

    private StackPane backgroundPane1;
    private StackPane backgroundPane2;

    private FadeTransition bg1Fade;
    private FadeTransition bg2Fade;

    private Integer transitionIndex = 0;

    private static Thread transitionThread;

    private boolean changeBg;
    private static LoginBackgroundTransition trans;

    /**
     * Transits between the two Panes
     *
     * @param backgroundPane1
     * @param backgroundPane2
     */
    public LoginBackgroundTransition(StackPane backgroundPane1, StackPane backgroundPane2) {
        this.backgroundPane1 = backgroundPane1;
        this.backgroundPane2 = backgroundPane2;
        if (trans != null) {
            if (trans.transitionThread != null) {
                trans.transitionThread.stop();
            }
        }
        trans = this;
    }

    private static final String DIRECTORY = SystemImageUtil.IMAGE_RES;
    private static final List<String> WALLPAPERS = SystemImageUtil.getWallpapers();

    /**
     * To start transition
     */
    public void start() {

        setWallpaper(backgroundPane1, transitionIndex);

        setWallpaper(backgroundPane2, transitionIndex);

        setTransitions();

        startTransitions();

    }

    /**
     * Sets the wallpaper to the background of a given pane
     */
    private void setWallpaper(StackPane background, Integer index) {
        background.setStyle("-fx-background-image: url(" + DIRECTORY + WALLPAPERS.get(index) + ");");
        System.out.println("Wallpaper: " + WALLPAPERS.get(index));
    }

    /**
     * Sets the transitions of the two panes.
     */
    private void setTransitions() {
        bg1Fade = new FadeTransition(Duration.seconds(1), backgroundPane1);
        bg1Fade.setFromValue(0);
        bg1Fade.setToValue(1);

        bg2Fade = new FadeTransition(Duration.seconds(1), backgroundPane2);
        bg2Fade.setFromValue(0);
        bg2Fade.setToValue(1);

    }

    /**
     * Starts the transition Thread. Changes the wallpaper each 10000
     * milliseconds.
     *
     */
    private void startTransitions() {
        System.out.println("\n\n\nSTARTED\n\n\n");
        if (transitionThread != null) {
            transitionThread.stop();
        }
        transitionThread = new Thread(new Runnable() {
            public void run() {

                while (true) {
                    if (transitionIndex >= WALLPAPERS.size()) {
                        transitionIndex = 0;
                    }

                    if (changeBg) {
                        transitBackground(1, backgroundPane1, transitionIndex);
                    } else {
                        transitBackground(-1, backgroundPane2, transitionIndex);
                    }

                    try {
                        Thread.sleep(6000);

                    } catch (Exception e) {
                    }
                    changeBg = !changeBg;
                    transitionIndex++;

                }
            }
        });

        transitionThread.start();
    }

    /**
     * Fades one pane in while fades another pane out
     *
     */
    private void transitBackground(Integer rate, StackPane background, Integer index) {
        setWallpaper(background, index);

        bg1Fade.setRate(rate);
        bg1Fade.play();
        bg2Fade.setRate(rate * -1);
        bg2Fade.play();
    }

    /**
     * Stops transition;
     */
    public void stop() {
        System.out.println("STOPPPPP");

        trans.transitionThread.stop();
    }

}
