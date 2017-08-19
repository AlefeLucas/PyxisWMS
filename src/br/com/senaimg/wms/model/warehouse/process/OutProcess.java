/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.language.Lang;

/**
 *
 * @author ÁlefeLucas
 */
public enum OutProcess {
    
    SALE(Lang.get("Sale")),    
    PICK(Lang.get("Pick")),    
    CHECK(Lang.get("Check")),
    DISPATCH(Lang.get("Dispatch"));

    private final String process;

    private OutProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return this.process;
    }
}
