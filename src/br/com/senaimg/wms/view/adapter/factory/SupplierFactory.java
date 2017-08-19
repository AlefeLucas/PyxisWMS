package br.com.senaimg.wms.view.adapter.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.senaimg.wms.model.warehouse.agent.Supplier;
import br.com.senaimg.wms.view.adapter.TableSuppliersAdapter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class SupplierFactory {

	public static List<TableSuppliersAdapter> loadActive(){
		List<Supplier> suppliers = Supplier.list();
		ArrayList<TableSuppliersAdapter> list = new ArrayList<>();
		
		for(Supplier s : suppliers){
			if(!s.isDisabled()){
				list.add(new TableSuppliersAdapter(s));
			}
		}
		
		return list;
	}
	
	
	public static JRBeanCollectionDataSource getActiveDataSource() {
		return new JRBeanCollectionDataSource(loadActive(), true);
	}

	
}
