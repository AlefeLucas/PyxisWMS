/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import java.util.ArrayList;

/**
 *
 * @author Ã�lefeLucas
 */
public class TablePickSaleAdapter {

    private SaleHasMetaItem saleHasMetaItem;
    private String mnemonic;
    private String description;
    private String sell;
    private String stock;
    private String enough;
    private boolean isEnough;
    private ArrayList<Stock> stocks = new ArrayList<>();

    public TablePickSaleAdapter(SaleHasMetaItem saleHasMetaItem, ArrayList<ArrayList<Stock>> stockses) {
        setSaleHasMetaItem(saleHasMetaItem, stockses);
    }

    public SaleHasMetaItem getSaleHasMetaItem() {
        return saleHasMetaItem;
    }

    public void setSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem, ArrayList<ArrayList<Stock>> stockses) {
        this.saleHasMetaItem = saleHasMetaItem;        
        
        populateStocks(stockses, saleHasMetaItem);
        int stocksSize = stocks.size();
        stock = stocksSize + "";
        mnemonic = saleHasMetaItem.getMetaItem().getMnemonic();
        description = saleHasMetaItem.getMetaItem().getDescription();
        int sellSize = saleHasMetaItem.getQuantity();
        sell =  sellSize + "";
        isEnough = stocksSize >= sellSize;
        enough = (isEnough ? Lang.get("Yes") : Lang.get("No"));
        
        
        
    }

    private void populateStocks(ArrayList<ArrayList<Stock>> stockses, SaleHasMetaItem saleHasMetaItem1) {
        for (ArrayList<Stock> stocks : stockses) {
            if (stocks.get(0).getItem().getBatch().getMetaItem().getId() == saleHasMetaItem1.getMetaItem().getId()) {
                for(Stock stock : stocks){
                    this.stocks.add(stock);
                }
                break;
            }
        }
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getEnough() {
        return enough;
    }

    public void setEnough(String enough) {
        this.enough = enough;
    }

    public boolean isEnough() {
        return isEnough;
    }

    public void setIsEnough(boolean isEnough) {
        this.isEnough = isEnough;
    }

}
