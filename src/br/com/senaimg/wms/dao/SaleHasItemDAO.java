/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.SaleHasItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ÁlefeLucas
 */
public abstract class SaleHasItemDAO {
 
    /**
     *
     * @param saleHasItem
     */
    public static void insertSaleHasItem(SaleHasItem saleHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(saleHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the saleHasItem table in database through Hibernate.
     *
     * @param saleHasItem
     */
    public static void updateSaleHasItem(SaleHasItem saleHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(saleHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the saleHasItem table in database through Hibernate.
     *
     * @param saleHasItem
     */
    public static void deleteSaleHasItem(SaleHasItem saleHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(saleHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the saleHasItem table in
     * database through Hibernate.
     *
     * @param saleHasItem
     */
    public static void mergeSaleHasItem(SaleHasItem saleHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(saleHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the saleHasItem table in database through
     * Hibernate.
     *
     * @param saleHasItems
     */
    public static void insertSaleHasItems(List<SaleHasItem> saleHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasItems.forEach(saleHasItem -> session.save(saleHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the saleHasItem table in database through
     * Hibernate.
     *
     * @param saleHasItems
     */
    public static void updateSaleHasItems(List<SaleHasItem> saleHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasItems.forEach(saleHasItem -> session.update(saleHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the saleHasItem table in database through
     * Hibernate.
     *
     * @param saleHasItems
     */
    public static void deleteSaleHasItems(List<SaleHasItem> saleHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasItems.forEach(saleHasItem -> session.delete(saleHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the saleHasItem table in
     * database through Hibernate.
     *
     * @param saleHasItems
     */
    public static void mergeSaleHasItems(List<SaleHasItem> saleHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasItems.forEach(saleHasItem -> session.merge(saleHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the saleHasItem table in database through
     * Hibernate.
     *
     * @return List &lt SaleHasItem>
     */
    public static List<SaleHasItem> selectSaleHasItems() {
        List<SaleHasItem> saleHasItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(SaleHasItem.class);          
            saleHasItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return saleHasItems;
    }
    
    
}
