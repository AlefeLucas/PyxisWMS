/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.warehouse.process;

import br.com.senaimg.wms.language.Lang;

/**
 *
 * @author Aluno
 */
public enum LoanStatus {
    PICK(Lang.get("Pick")),
    LOANED(Lang.get("Loaned")),
    RETURNED(Lang.get("Returned")),
    STORE(Lang.get("Stored"));
    

    private final String status;

    private LoanStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
