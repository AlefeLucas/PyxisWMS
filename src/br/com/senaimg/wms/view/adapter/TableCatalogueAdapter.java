/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.adapter;

import br.com.senaimg.wms.model.warehouse.agent.Catalogue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class TableCatalogueAdapter {

    private Catalogue catalogue;
    private String mnemonic;
    private String description;
    private String price;
    private String ranking;

    public Catalogue getCalogue() {
        return catalogue;
    }

    public void setCalogue(Catalogue catalogue) {
        this.catalogue = catalogue;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public TableCatalogueAdapter(Catalogue catalogue) {
        this.catalogue = catalogue;

        this.description = catalogue.getMetaItem().getDescription();
        this.mnemonic = catalogue.getMetaItem().getMnemonic();
        this.price = String.format("R$ %.2f", catalogue.getPrice());
        this.ranking = setRanking(catalogue);

    }

    private String setRanking(Catalogue catalogue) {
        List<Catalogue> list = Catalogue.list();
        ArrayList<Catalogue> cats = new ArrayList<>();

        for (Catalogue c : list) {
            if (c.getMetaItem().getId() == catalogue.getMetaItem().getId()) {
                cats.add(c);
            }
        }

        Collections.sort(cats, (o1, o2) -> {
            if (o1.getPrice() < o2.getPrice()) {
                return -1;
            } else {
                return 1;
            }
        });

        int index = -20;
        for(Catalogue c : cats){
            if(c.getId() == catalogue.getId()){
                index = cats.indexOf(c) + 1;
            }
        }
        return "" + index;
    }

}
