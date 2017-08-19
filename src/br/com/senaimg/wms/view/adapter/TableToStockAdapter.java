/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.place.Hall;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;
import br.com.senaimg.wms.model.warehouse.stock.place.Rack;
import br.com.senaimg.wms.model.warehouse.stock.place.RackFloor;
import java.util.List;

/**
 *
 * @author ÁlefeLucas
 */
public class TableToStockAdapter {

    private List<Stock> stocks;
    private String item;
    private String quantity;
    private String location;

    public TableToStockAdapter(List<Stock> stocks) {
        setStocks(stocks);
    }

    public void setStocks(List<Stock> stocks) {
        if (stocks != null && !stocks.isEmpty()) {
            this.stocks = stocks;
            Pallet pallet = stocks.get(0).getPallet();
            RackFloor floor = pallet.getFloor();
            Rack rack = floor.getRack();
            Hall hall = rack.getHall();

            this.location = hall.getCode() + "/" + rack.getCode() + "/" + floor.getFloorNumber() + "/Pallet " + pallet.getId();
            

            if (stocks != null && !stocks.isEmpty()) {
                this.item = stocks.get(0).getItem().getBatch().getMetaItem().getMnemonic();
                this.quantity = stocks.size() + "";
            } else {
                this.quantity = "0";
            }
        }
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

}
