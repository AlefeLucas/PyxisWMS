/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.ItemType;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class ItemTypeDAO {
 
    /**
     *
     * @param itemType
     */
    public static void insertItemType(ItemType itemType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(itemType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the itemType table in database through Hibernate.
     *
     * @param itemType
     */
    public static void updateItemType(ItemType itemType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(itemType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the itemType table in database through Hibernate.
     *
     * @param itemType
     */
    public static void deleteItemType(ItemType itemType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(itemType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the itemType table in
     * database through Hibernate.
     *
     * @param itemType
     */
    public static void mergeItemType(ItemType itemType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(itemType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the itemType table in database through
     * Hibernate.
     *
     * @param itemTypes
     */
    public static void insertItemTypes(List<ItemType> itemTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            itemTypes.forEach(itemType -> session.save(itemType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the itemType table in database through
     * Hibernate.
     *
     * @param itemTypes
     */
    public static void updateItemTypes(List<ItemType> itemTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            itemTypes.forEach(itemType -> session.update(itemType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the itemType table in database through
     * Hibernate.
     *
     * @param itemTypes
     */
    public static void deleteItemTypes(List<ItemType> itemTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            itemTypes.forEach(itemType -> session.delete(itemType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the itemType table in
     * database through Hibernate.
     *
     * @param itemTypes
     */
    public static void mergeItemTypes(List<ItemType> itemTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            itemTypes.forEach(itemType -> session.merge(itemType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the itemType table in database through
     * Hibernate.
     *
     * @return List &lt ItemType>
     */
    public static List<ItemType> selectItemTypes() {
        List<ItemType> itemTypes = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(ItemType.class);          
            itemTypes = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return itemTypes;
    }
    
    
}
