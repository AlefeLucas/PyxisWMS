/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;

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
public abstract class SupplierDAO {
 
    /**
     *
     * @param supplier
     */
    public static void insertSupplier(Supplier supplier) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(supplier);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the supplier table in database through Hibernate.
     *
     * @param supplier
     */
    public static void updateSupplier(Supplier supplier) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(supplier);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the supplier table in database through Hibernate.
     *
     * @param supplier
     */
    public static void deleteSupplier(Supplier supplier) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(supplier);

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
     * Updates or inserts if not exists a record in the supplier table in
     * database through Hibernate.
     *
     * @param supplier
     */
    public static void mergeSupplier(Supplier supplier) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(supplier);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the supplier table in database through
     * Hibernate.
     *
     * @param suppliers
     */
    public static void insertSuppliers(List<Supplier> suppliers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            suppliers.forEach(supplier -> session.save(supplier));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the supplier table in database through
     * Hibernate.
     *
     * @param suppliers
     */
    public static void updateSuppliers(List<Supplier> suppliers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            suppliers.forEach(supplier -> session.update(supplier));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the supplier table in database through
     * Hibernate.
     *
     * @param suppliers
     */
    public static void deleteSuppliers(List<Supplier> suppliers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            suppliers.forEach(supplier -> session.delete(supplier));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the supplier table in
     * database through Hibernate.
     *
     * @param suppliers
     */
    public static void mergeSuppliers(List<Supplier> suppliers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            suppliers.forEach(supplier -> session.merge(supplier));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the supplier table in database through
     * Hibernate.
     *
     * @return List &lt Supplier>
     */
    public static List<Supplier> selectSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Supplier.class);          
            suppliers = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return suppliers;
    }

    public static void refresh(Supplier aThis) {
        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.refresh(aThis);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }
    
    
}
