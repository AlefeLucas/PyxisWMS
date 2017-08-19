/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.agent.enums;

import br.com.senaimg.wms.language.Lang;

/**
 *
 * @author ÁlefeLucas
 */
public enum CustomerType {

    INDIVIDUAL(Lang.get("Individual")),

 
    COMPANY(Lang.get("Company"));

    
    private final String type;

    private CustomerType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }

}
