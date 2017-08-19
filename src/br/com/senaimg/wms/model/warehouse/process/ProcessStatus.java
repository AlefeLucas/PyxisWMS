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
public enum ProcessStatus {
   
    ONGOING(Lang.get("Ongoing")),

   
    DONE(Lang.get("Done")),

    
    UNBEGUN(Lang.get("Unbegun"));

    private final String status;

    private ProcessStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
