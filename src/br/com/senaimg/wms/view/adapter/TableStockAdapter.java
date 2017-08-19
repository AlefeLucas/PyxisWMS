/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.Stock;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemDimension;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;
import java.util.List;

/**
 *
 * @author Ã�lefeLucas
 */
public class TableStockAdapter {

	private List<Stock> stocks;
	private String mnemonic;
	private String group;
	private String brand;
	private String description;
	private int quantity;

	private String minimum;
	private String maximum;
	private String totalCosts;
	private String sellingPrice;
	private String profitMargin;
	private String avgCost;
	private String totalSellingPrice;
	private String totalProfit;
	private String avgIndividualProfit;
	private String ean13;
	private String operation;
	private String type;
	private String caution;
	private String height;
	private String width;
	private String length;
	private String weight;

	public TableStockAdapter(List<Stock> stocks) {
		setStocks(stocks);
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		if (stocks != null && !stocks.isEmpty()) {
			this.stocks = stocks;

			try {
				verifyConsistency(stocks);
			} catch (IllegalStateException ex) {
				ex.printStackTrace();
				System.exit(1);
			}

			MetaItem mt = stocks.get(0).getItem().getBatch().getMetaItem();

			setMetaItem(mt);

			int quantityInStock = stocks.size();

			double totalCosts = 0;
			for (Stock stock : stocks) {
				totalCosts += stock.getItem().getBuyingPrice();
			}
			double avgCost = totalCosts / quantityInStock;

			double totalCostOut = mt.getSellingPrice() * quantityInStock;

			double profitMargin = (totalCostOut * 100 / totalCosts) - 100;

			double totalProfit = totalCostOut - totalCosts;

			double avgProfit = totalProfit / quantityInStock;

			this.quantity = quantityInStock;
			this.totalCosts = String.format(Lang.get("R$") + " %.2f", totalCosts);
			this.profitMargin = String.format("%.2f", profitMargin) + "%";
			this.avgCost = String.format(Lang.get("R$") + " %.2f", avgCost);
			this.totalSellingPrice = String.format(Lang.get("R$") + " %.2f", totalCostOut);
			this.totalProfit = String.format(Lang.get("R$") + " %.2f", totalProfit);
			this.avgIndividualProfit = String.format(Lang.get("R$") + " %.2f", avgProfit);

		}
	}

	private void setMetaItem(MetaItem mt) {
		this.group = mt.getGroup().toString();
		this.mnemonic = mt.getMnemonic();
		this.brand = mt.getBrand().getName();
		this.description = mt.getDescription();
		this.minimum = mt.getMinQuantity() + "";
		this.maximum = mt.getMaxQuantity() + "";
		this.sellingPrice = String.format(Lang.get("R$") + " %.2f", mt.getSellingPrice());
		this.ean13 = mt.getEan13();
		this.operation = mt.getOperation().getName();
		if (mt.getItemType() == null) {
			this.type = Lang.get("Unspecified");
		} else {
			this.type = mt.getItemType().getName();
		}
		this.caution = mt.getCaution();

		ItemDimension dimension = mt.getDimension();
		setDimensions(dimension);

	}

	private void setDimensions(ItemDimension dimension) {
		this.height = String.format("%.2f " + "mm", dimension.getHeight());
		this.width = String.format("%.2f " + "mm", dimension.getWidth());
		this.length = String.format("%.2f " + "mm", dimension.getLength());
		this.weight = String.format("%.2f " + "g", dimension.getWeight());
	}

	private void verifyConsistency(List<Stock> stocks1) throws IllegalStateException {
		MetaItem mt = stocks1.get(0).getItem().getBatch().getMetaItem();
		for (Stock stock : stocks1) {
			if (stock.getItem().getBatch().getMetaItem().getId() != mt.getId()) {
				throw new IllegalStateException("PASSOU LISTA ERRADA");
			}
		}
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;

	}

	public String getMinimum() {
		return minimum;
	}

	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}

	public String getMaximum() {
		return maximum;
	}

	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}

	public String getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(String totalCosts) {
		this.totalCosts = totalCosts;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getProfitMargin() {
		return profitMargin;
	}

	public void setProfitMargin(String profitMargin) {
		this.profitMargin = profitMargin;
	}

	public String getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(String avgCost) {
		this.avgCost = avgCost;
	}

	public String getTotalSellingPrice() {
		return totalSellingPrice;
	}

	public void setTotalSellingPrice(String totalSellingPrice) {
		this.totalSellingPrice = totalSellingPrice;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getAvgIndividualProfit() {
		return avgIndividualProfit;
	}

	public void setAvgIndividualProfit(String avgIndividualProfit) {
		this.avgIndividualProfit = avgIndividualProfit;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCaution() {
		return caution;
	}

	public void setCaution(String caution) {
		this.caution = caution;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
