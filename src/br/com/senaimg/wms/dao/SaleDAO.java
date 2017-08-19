/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.Sale;
import br.com.senaimg.wms.model.warehouse.process.SaleHasMetaItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class SaleDAO {
 
    /**
     *
     * @param sale
     */
    public static void insertSale(Sale sale) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(sale);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the sale table in database through Hibernate.
     *
     * @param sale
     */
    public static void updateSale(Sale sale) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(sale);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the sale table in database through Hibernate.
     *
     * @param sale
     */
    public static void deleteSale(Sale sale) {

        List<SaleHasMetaItem> saleHasItems = sale.getSaleHasMetaItems();
        for(SaleHasMetaItem phi : saleHasItems){
            phi.delete();
        }
        sale.setSaleHasMetaItems(null);
        
        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(sale);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the sale table in
     * database through Hibernate.
     *
     * @param sale
     */
    public static void mergeSale(Sale sale) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(sale);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the sale table in database through
     * Hibernate.
     *
     * @param sales
     */
    public static void insertSales(List<Sale> sales) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            sales.forEach(sale -> session.save(sale));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the sale table in database through
     * Hibernate.
     *
     * @param sales
     */
    public static void updateSales(List<Sale> sales) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            sales.forEach(sale -> session.update(sale));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the sale table in database through
     * Hibernate.
     *
     * @param sales
     */
    public static void deleteSales(List<Sale> sales) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            sales.forEach(sale -> session.delete(sale));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the sale table in
     * database through Hibernate.
     *
     * @param sales
     */
    public static void mergeSales(List<Sale> sales) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            sales.forEach(sale -> session.merge(sale));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the sale table in database through
     * Hibernate.
     *
     * @return List &lt Sale>
     */
    public static List<Sale> selectSales() {
        List<Sale> sales = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Sale.class);          
            sales = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return sales;
    }
    
    
}
