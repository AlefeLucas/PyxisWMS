/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.PurchaseHasItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class PurchaseHasItemDAO {
 
    /**
     *
     * @param purchaseHasItem
     */
    public static void insertPurchaseHasItem(PurchaseHasItem purchaseHasItem) {

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
    public static void updatePurchaseHasItem(PurchaseHasItem purchaseHasItem) {

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
    public static void deletePurchaseHasItem(PurchaseHasItem purchaseHasItem) {

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
    public static void mergePurchaseHasItem(PurchaseHasItem purchaseHasItem) {

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
    public static void insertPurchaseHasItems(List<PurchaseHasItem> purchaseHasItems) {

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
    public static void updatePurchaseHasItems(List<PurchaseHasItem> purchaseHasItems) {

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
    public static void deletePurchaseHasItems(List<PurchaseHasItem> purchaseHasItems) {

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
    public static void mergePurchaseHasItems(List<PurchaseHasItem> purchaseHasItems) {

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
    public static List<PurchaseHasItem> selectPurchaseHasItems() {
        List<PurchaseHasItem> purchaseHasItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(PurchaseHasItem.class);          
            purchaseHasItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return purchaseHasItems;
    }
    
    
}
