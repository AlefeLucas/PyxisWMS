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
public enum UserStatus {
    
    /**
     * On, user can log in and do stuff
     */
    ON(Lang.get("On")),

    /**
     * Requested, user can't log in, waiting for approval
     */
    REQUESTED(Lang.get("Requested")),

    /**
     * Deactivated, user can't log in
     */
    OFF(Lang.get("Off"));

    private final String status;

    private UserStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
