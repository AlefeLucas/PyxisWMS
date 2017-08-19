/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.Functionality;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alefe Lucas
 */
public abstract class FunctionalityDAO {

    /**
     * Inserts a new record into the functionality table in database through Hibernate.
     * @param functionality
     */
    public static void insertFunctionality(Functionality functionality) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(functionality);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the functionality table in database through Hibernate.
     * @param functionality
     */
    public static void updateFunctionality(Functionality functionality) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(functionality);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the functionality table in database through Hibernate.
     * @param functionality
     */
    public static void deleteFunctionality(Functionality functionality) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(functionality);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the functionality table in database through Hibernate.
     * @param functionality
     */
    public static void mergeFunctionality(Functionality functionality) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(functionality);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the functionality table in database through Hibernate.
     * @param functionalities
     */
    public static void insertFunctionalities(List<Functionality> functionalities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalities.forEach(functionality -> session.save(functionality));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the functionality table in database through Hibernate.
     * @param functionalities
     */
    public static void updateFunctionalities(List<Functionality> functionalities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalities.forEach(functionality -> session.update(functionality));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     *Deletes several records in the functionality table in database through Hibernate.
     * @param functionalities
     */
    public static void deleteFunctionalities(List<Functionality> functionalities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalities.forEach(functionality -> session.delete(functionality));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the functionality table in database through Hibernate.
     * @param functionalities
     */
    public static void mergeFunctionalities(List<Functionality> functionalities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalities.forEach(functionality -> session.merge(functionality));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the functionality table in database through Hibernate.
     * @return List &lt Functionality>
     */
    public static List<Functionality> selectFunctionalities() {
        List<Functionality> functionalities = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Functionality.class);
criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            functionalities = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return functionalities;
    }   
}
