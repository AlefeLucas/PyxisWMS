/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter.factory;

import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import br.com.senaimg.wms.view.adapter.TableStockAdapter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class StockFactory {
	
	public static JRBeanCollectionDataSource getAllDataSource() {
		return new JRBeanCollectionDataSource(loadAll(), true);
	}

	public static List<TableStockAdapter> loadAll() {
		ArrayList<TableStockAdapter> tableStocks = new ArrayList<>();

		List<Stock> allStocks = Stock.list();

		List<MetaItem> metaItems = MetaItem.list();
		ArrayList<ArrayList<Stock>> listOfListOfStock = new ArrayList<>(metaItems.size());
		for (MetaItem metaItem : metaItems) {
			listOfListOfStock.add(new ArrayList<>());
		}

		for (int x = 0; x < metaItems.size(); x++) {
			for (Stock stock : allStocks) {
				if (stock.getItem().getBatch().getMetaItem().getId() == metaItems.get(x).getId()) {
					listOfListOfStock.get(x).add(stock);
				}
			}
		}

		for (ArrayList<Stock> stocks : listOfListOfStock) {
			if (!stocks.isEmpty()) {
				tableStocks.add(new TableStockAdapter(stocks));
			}
		}

		return tableStocks;
	}
	
	public static JRBeanCollectionDataSource getMinimumDataSource() {
		return new JRBeanCollectionDataSource(loadMinimum(), true);
	}
	
	public static List<TableStockAdapter> loadMinimum() {
		List<TableStockAdapter> list = loadAll();
		ArrayList<TableStockAdapter> listOut = new ArrayList<>();
		
		for(TableStockAdapter tsa : list){
			int quantity = tsa.getQuantity();
			List<Stock> s = tsa.getStocks();
			int min = s.get(0).getItem().getBatch().getMetaItem().getMinQuantity();
			if(quantity <= min){
				listOut.add(tsa);
			}
		}
		return listOut;
	}

}
