/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema.enums;

/**
 *
 * @author Aluno
 */
public enum CssTheme {
    
    DARK("Dark"),
    LIGHT("Light");

    private final String theme;

    private CssTheme(String theme) {
        this.theme = theme;
    }

    @Override
    public String toString() {
        return this.theme;
    }
}
