/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.Purchase;
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
public abstract class PurchaseDAO {
 
    /**
     *
     * @param purchase
     */
    public static void insertPurchase(Purchase purchase) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(purchase);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the purchase table in database through Hibernate.
     *
     * @param purchase
     */
    public static void updatePurchase(Purchase purchase) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(purchase);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the purchase table in database through Hibernate.
     *
     * @param purchase
     */
    public static void deletePurchase(Purchase purchase) {

        List<PurchaseHasMetaItem> purchaseHasItems = purchase.getPurchaseHasMetaItems();
        for(PurchaseHasMetaItem phi : purchaseHasItems){
            phi.delete();
        }
        purchase.setPurchaseHasMetaItems(null);
        
        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(purchase);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the purchase table in
     * database through Hibernate.
     *
     * @param purchase
     */
    public static void mergePurchase(Purchase purchase) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(purchase);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the purchase table in database through
     * Hibernate.
     *
     * @param purchases
     */
    public static void insertPurchases(List<Purchase> purchases) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchases.forEach(purchase -> session.save(purchase));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the purchase table in database through
     * Hibernate.
     *
     * @param purchases
     */
    public static void updatePurchases(List<Purchase> purchases) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchases.forEach(purchase -> session.update(purchase));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the purchase table in database through
     * Hibernate.
     *
     * @param purchases
     */
    public static void deletePurchases(List<Purchase> purchases) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchases.forEach(purchase -> session.delete(purchase));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the purchase table in
     * database through Hibernate.
     *
     * @param purchases
     */
    public static void mergePurchases(List<Purchase> purchases) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            purchases.forEach(purchase -> session.merge(purchase));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the purchase table in database through
     * Hibernate.
     *
     * @return List &lt Purchase>
     */
    public static List<Purchase> selectPurchases() {
        List<Purchase> purchases = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Purchase.class);          
            purchases = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return purchases;
    }
    
    
}
