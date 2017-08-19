/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.MetaItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class MetaItemDAO {

    /**
     *
     * @param metaItem
     */
    public static void insertMetaItem(MetaItem metaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(metaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the metaItem table in database through Hibernate.
     *
     * @param metaItem
     */
    public static void updateMetaItem(MetaItem metaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(metaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the metaItem table in database through Hibernate.
     *
     * @param metaItem
     */
    public static void deleteMetaItem(MetaItem metaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(metaItem);

            transaction.commit();
            session.close();
        } catch (ConstraintViolationException ex) {
            throw ex;

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the metaItem table in
     * database through Hibernate.
     *
     * @param metaItem
     */
    public static void mergeMetaItem(MetaItem metaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(metaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the metaItem table in database through
     * Hibernate.
     *
     * @param metaItems
     */
    public static void insertMetaItems(List<MetaItem> metaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            metaItems.forEach(metaItem -> session.save(metaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the metaItem table in database through
     * Hibernate.
     *
     * @param metaItems
     */
    public static void updateMetaItems(List<MetaItem> metaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            metaItems.forEach(metaItem -> session.update(metaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the metaItem table in database through
     * Hibernate.
     *
     * @param metaItems
     */
    public static void deleteMetaItems(List<MetaItem> metaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            metaItems.forEach(metaItem -> session.delete(metaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the metaItem table in
     * database through Hibernate.
     *
     * @param metaItems
     */
    public static void mergeMetaItems(List<MetaItem> metaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            metaItems.forEach(metaItem -> session.merge(metaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the metaItem table in database through
     * Hibernate.
     *
     * @return List &lt MetaItem>
     */
    public static List<MetaItem> selectMetaItems() {
        List<MetaItem> metaItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(MetaItem.class);
            metaItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return metaItems;
    }

    public static List<MetaItem> selectMetaItemsTurnover() {
        List<MetaItem> metaItems = selectMetaItems();
        ArrayList<MetaItem> turnovers = new ArrayList<>();
        System.out.println("SELECT TURNOVER");
        int x = 0;
        for (MetaItem metaItem : metaItems) {
            System.out.println("SELECT TURNOVER " + x++);
            if (metaItem.getOperation().isTurnover()) {
                turnovers.add(metaItem);
                System.out.println("ADDED");
            }
        }
        System.out.println("SELECT TURNOVER");
        return turnovers;

    }

}
