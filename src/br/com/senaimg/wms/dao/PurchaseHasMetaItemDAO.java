/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasMetaItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author ÁlefeLucas
 */
public abstract class PurchaseHasMetaItemDAO {
 
    /**
     *
     * @param purchaseHasItem
     */
    public static void insertPurchaseHasItem(PurchaseHasMetaItem purchaseHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(purchaseHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the purchaseHasItem table in database through Hibernate.
     *
     * @param purchaseHasItem
     */
    public static void updatePurchaseHasItem(PurchaseHasMetaItem purchaseHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(purchaseHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the purchaseHasItem table in database through Hibernate.
     *
     * @param purchaseHasItem
     */
    public static void deletePurchaseHasItem(PurchaseHasMetaItem purchaseHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(purchaseHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the purchaseHasItem table in
     * database through Hibernate.
     *
     * @param purchaseHasItem
     */
    public static void mergePurchaseHasItem(PurchaseHasMetaItem purchaseHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(purchaseHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the purchaseHasItem table in database through
     * Hibernate.
     *
     * @param purchaseHasItems
     */
    public static void insertPurchaseHasItems(List<PurchaseHasMetaItem> purchaseHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchaseHasItems.forEach(purchaseHasItem -> session.save(purchaseHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the purchaseHasItem table in database through
     * Hibernate.
     *
     * @param purchaseHasItems
     */
    public static void updatePurchaseHasItems(List<PurchaseHasMetaItem> purchaseHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchaseHasItems.forEach(purchaseHasItem -> session.update(purchaseHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the purchaseHasItem table in database through
     * Hibernate.
     *
     * @param purchaseHasItems
     */
    public static void deletePurchaseHasItems(List<PurchaseHasMetaItem> purchaseHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchaseHasItems.forEach(purchaseHasItem -> session.delete(purchaseHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the purchaseHasItem table in
     * database through Hibernate.
     *
     * @param purchaseHasItems
     */
    public static void mergePurchaseHasItems(List<PurchaseHasMetaItem> purchaseHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchaseHasItems.forEach(purchaseHasItem -> session.merge(purchaseHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the purchaseHasItem table in database through
     * Hibernate.
     *
     * @return List &lt PurchaseHasItem>
     */
    public static List<PurchaseHasMetaItem> selectPurchaseHasItems() {
        List<PurchaseHasMetaItem> purchaseHasItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(PurchaseHasMetaItem.class);          
            purchaseHasItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return purchaseHasItems;
    }
    
    
}
