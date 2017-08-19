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
public enum InProcess {
    PURCHASE(Lang.get("Purchase")),
    RECEIPT(Lang.get("Receipt")),
    CHECK(Lang.get("Check")),
    STORE(Lang.get("Store"));

    private final String process;

    private InProcess(String process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return this.process;
    }
}
