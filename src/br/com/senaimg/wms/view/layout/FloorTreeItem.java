/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Aluno
 */
public class FloorTreeItem<T> extends TreeItem<T>  {

    public FloorTreeItem() {
    }

    public FloorTreeItem(T value) {
        super(value);
    }

    public FloorTreeItem(T value, Node graphic) {
        super(value, graphic);
    }
    
}
