/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
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
public abstract class SaleHasMetaItemDAO {
 
    /**
     *
     * @param saleHasMetaItem
     */
    public static void insertSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(saleHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the saleHasMetaItem table in database through Hibernate.
     *
     * @param saleHasMetaItem
     */
    public static void updateSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(saleHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the saleHasMetaItem table in database through Hibernate.
     *
     * @param saleHasMetaItem
     */
    public static void deleteSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(saleHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the saleHasMetaItem table in
     * database through Hibernate.
     *
     * @param saleHasMetaItem
     */
    public static void mergeSaleHasMetaItem(SaleHasMetaItem saleHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(saleHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the saleHasMetaItem table in database through
     * Hibernate.
     *
     * @param saleHasMetaItems
     */
    public static void insertSaleHasMetaItems(List<SaleHasMetaItem> saleHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasMetaItems.forEach(saleHasMetaItem -> session.save(saleHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the saleHasMetaItem table in database through
     * Hibernate.
     *
     * @param saleHasMetaItems
     */
    public static void updateSaleHasMetaItems(List<SaleHasMetaItem> saleHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasMetaItems.forEach(saleHasMetaItem -> session.update(saleHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the saleHasMetaItem table in database through
     * Hibernate.
     *
     * @param saleHasMetaItems
     */
    public static void deleteSaleHasMetaItems(List<SaleHasMetaItem> saleHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasMetaItems.forEach(saleHasMetaItem -> session.delete(saleHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the saleHasMetaItem table in
     * database through Hibernate.
     *
     * @param saleHasMetaItems
     */
    public static void mergeSaleHasMetaItems(List<SaleHasMetaItem> saleHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            saleHasMetaItems.forEach(saleHasMetaItem -> session.merge(saleHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the saleHasMetaItem table in database through
     * Hibernate.
     *
     * @return List &lt SaleHasMetaItem>
     */
    public static List<SaleHasMetaItem> selectSaleHasMetaItems() {
        List<SaleHasMetaItem> saleHasMetaItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(SaleHasMetaItem.class);          
            saleHasMetaItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return saleHasMetaItems;
    }
    
    
}
