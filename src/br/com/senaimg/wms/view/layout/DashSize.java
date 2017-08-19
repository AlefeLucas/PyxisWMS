/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import javafx.geometry.Dimension2D;

/**
 *
 * @author Alefe Lucas
 */
public enum DashSize {

    /**
     *
     */
    SMALL(55, 55),

    /**
     *
     */
    MEDIUM(120, 120),

    /**
     *
     */
    LARGE(250, 120),

    /**
     *
     */
    BIG(250, 250);

    private final Double xSpan;
    private final Double ySpan;

    private DashSize(double xSpan, double ySpan) {
        this.xSpan = xSpan;
        this.ySpan = ySpan;
    }

    /**
     *
     * @return
     */
    public Dimension2D getSpans() {
        return new Dimension2D(xSpan, ySpan);
    }
}
