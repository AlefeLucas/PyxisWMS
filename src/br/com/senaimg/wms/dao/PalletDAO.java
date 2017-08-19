/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.place.Pallet;

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
public abstract class PalletDAO {

    /**
     *
     * @param pallet
     */
    public static void insertPallet(Pallet pallet) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(pallet);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the pallet table in database through Hibernate.
     *
     * @param pallet
     */
    public static void updatePallet(Pallet pallet) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(pallet);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the pallet table in database through Hibernate.
     *
     * @param pallet
     */
    public static void deletePallet(Pallet pallet) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(pallet);

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
     * Updates or inserts if not exists a record in the pallet table in database
     * through Hibernate.
     *
     * @param pallet
     */
    public static void mergePallet(Pallet pallet) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(pallet);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the pallet table in database through
     * Hibernate.
     *
     * @param pallets
     */
    public static void insertPallets(List<Pallet> pallets) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            pallets.forEach(pallet -> session.save(pallet));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the pallet table in database through
     * Hibernate.
     *
     * @param pallets
     */
    public static void updatePallets(List<Pallet> pallets) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            pallets.forEach(pallet -> session.update(pallet));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the pallet table in database through
     * Hibernate.
     *
     * @param pallets
     */
    public static void deletePallets(List<Pallet> pallets) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            pallets.forEach(pallet -> session.delete(pallet));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the pallet table in
     * database through Hibernate.
     *
     * @param pallets
     */
    public static void mergePallets(List<Pallet> pallets) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            pallets.forEach(pallet -> session.merge(pallet));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the pallet table in database through
     * Hibernate.
     *
     * @return List &lt Pallet>
     */
    public static List<Pallet> selectPallets() {
        List<Pallet> pallets = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Pallet.class);
            pallets = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return pallets;
    }

}
