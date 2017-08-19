/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.place.Hall;

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
public abstract class HallDAO {

    /**
     *
     * @param hall
     */
    public static void insertHall(Hall hall) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(hall);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the hall table in database through Hibernate.
     *
     * @param hall
     */
    public static void updateHall(Hall hall) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(hall);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the hall table in database through Hibernate.
     *
     * @param hall
     */
    public static void deleteHall(Hall hall) throws ConstraintViolationException {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(hall);

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
     * Updates or inserts if not exists a record in the hall table in database
     * through Hibernate.
     *
     * @param hall
     */
    public static void mergeHall(Hall hall) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(hall);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the hall table in database through
     * Hibernate.
     *
     * @param halls
     */
    public static void insertHalls(List<Hall> halls) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            halls.forEach(hall -> session.save(hall));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the hall table in database through Hibernate.
     *
     * @param halls
     */
    public static void updateHalls(List<Hall> halls) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            halls.forEach(hall -> session.update(hall));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the hall table in database through Hibernate.
     *
     * @param halls
     */
    public static void deleteHalls(List<Hall> halls) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            halls.forEach(hall -> session.delete(hall));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the hall table in
     * database through Hibernate.
     *
     * @param halls
     */
    public static void mergeHalls(List<Hall> halls) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            halls.forEach(hall -> session.merge(hall));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the hall table in database through Hibernate.
     *
     * @return List &lt Hall>
     */
    public static List<Hall> selectHalls() {
        List<Hall> halls = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Hall.class);
            halls = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return halls;
    }

}
