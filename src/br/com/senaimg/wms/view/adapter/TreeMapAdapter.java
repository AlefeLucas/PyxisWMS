/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.stock.place.Hall;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import br.com.senaimg.wms.model.warehouse.stock.place.Rack;
import br.com.senaimg.wms.model.warehouse.stock.place.RackFloor;

/**
 *
 * @author ÁlefeLucas
 */
public class TreeMapAdapter {

    private Hall hall;
    private Rack rack;
    private RackFloor floor;
    private Pallet pallet;

    public TreeMapAdapter() {
    }
    
    

    public TreeMapAdapter(Hall hall) {
        if(hall == null){
            throw new RuntimeException();            
        }
        this.hall = hall;
        this.rack = null;
        this.floor = null;
        this.pallet = null;
    }

    public TreeMapAdapter(Rack rack) {
        if(rack == null){
            throw new RuntimeException();            
        }
        this.rack = rack;
        this.hall = null;

        this.floor = null;
        this.pallet = null;
    }

    public TreeMapAdapter(RackFloor floor) {
        if(floor == null){
            throw new RuntimeException();            
        }
        this.floor = floor;
        this.hall = null;
        this.rack = null;

        this.pallet = null;
    }

    public TreeMapAdapter(Pallet pallet) {
        if(pallet == null){
            throw new RuntimeException();            
        }
        this.pallet = pallet;
        this.hall = null;
        this.rack = null;
        this.floor = null;

    }

    @Override
    public String toString() {
        if(hall != null){
            return hall.getCode();
        } else if (rack != null){
            return rack.getCode();
        } else if (floor != null){
            return floor.getFloorNumber() + "";
        } else if (pallet != null){
            return pallet.getId() + "";
        }        else {
            return "root";
        }
                
    }

    public Hall getHall() {
        return hall;
    }

    public Rack getRack() {
        return rack;
    }

    public RackFloor getFloor() {
        return floor;
    }

    public Pallet getPallet() {
        return pallet;
    }

    

}
