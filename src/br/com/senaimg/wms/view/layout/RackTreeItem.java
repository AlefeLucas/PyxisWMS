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
public class RackTreeItem<T> extends TreeItem<T>  {

    public RackTreeItem() {
    }

    public RackTreeItem(T value) {
        super(value);
    }

    public RackTreeItem(T value, Node graphic) {
        super(value, graphic);
    }

    
    
}
