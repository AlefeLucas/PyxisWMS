/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema.enums;

import br.com.senaimg.wms.language.Lang;

/**
 *
 * @author Alefe Lucas
 */
public enum Gender {

    /**
     * XY, Male
     */
    MALE(Lang.get("Male")),

    /**
     * XX, Female
     */
    FEMALE(Lang.get("Female")),

    /**
     * ??, Other
     */
    OTHER(Lang.get("Other"));

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return this.gender;
    }

}
