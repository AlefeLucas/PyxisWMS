/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.OperationType;

/**
 *
 * @author Aluno
 */
public class TableOperationsAdapter {

    private String name;
    private String turnover;
    private OperationType operationType;

    public TableOperationsAdapter(OperationType operationType) {
        this.operationType = operationType;
        
        name = operationType.getName();
        turnover = (operationType.isTurnover() ? Lang.get("Yes") : Lang.get("No"));
    }

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTurnover() {
        return turnover;
    }

    public void setTurnover(String turnover) {
        this.turnover = turnover;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    
    
}
