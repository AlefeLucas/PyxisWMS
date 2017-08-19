package br.com.senaimg.wms.view.adapter.factory;

import java.util.ArrayList;
import java.util.List;

import br.com.senaimg.wms.model.warehouse.agent.Customer;
import br.com.senaimg.wms.view.adapter.TableCustomersAdapter;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class CustomerFactory {

	public static List<TableCustomersAdapter> loadActive(){
		List<Customer> Customers = Customer.list();
		ArrayList<TableCustomersAdapter> list = new ArrayList<>();
		
		for(Customer s : Customers){
			if(!s.isDisabled()){
				list.add(new TableCustomersAdapter(s));
			}
		}
		
		return list;
	}
	
	
	public static JRBeanCollectionDataSource getActiveDataSource() {
		return new JRBeanCollectionDataSource(loadActive(), true);
	}
}
