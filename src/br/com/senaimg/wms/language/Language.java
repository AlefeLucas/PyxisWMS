/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.language;

/**
 *
 * @author Aluno
 */
public enum Language {

    PTBR("pt_BR"),
    ENUS("en_US");

    private final String language;

    private Language(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return this.language;
    }
}
