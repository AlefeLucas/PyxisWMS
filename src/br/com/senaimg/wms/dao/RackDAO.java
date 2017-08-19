/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.place.Rack;

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
public abstract class RackDAO {

    /**
     *
     * @param rack
     */
    public static void insertRack(Rack rack) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(rack);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the rack table in database through Hibernate.
     *
     * @param rack
     */
    public static void updateRack(Rack rack) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(rack);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the rack table in database through Hibernate.
     *
     * @param rack
     */
    public static void deleteRack(Rack rack) throws ConstraintViolationException{

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(rack);

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
     * Updates or inserts if not exists a record in the rack table in database
     * through Hibernate.
     *
     * @param rack
     */
    public static void mergeRack(Rack rack) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(rack);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the rack table in database through
     * Hibernate.
     *
     * @param racks
     */
    public static void insertRacks(List<Rack> racks) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            racks.forEach(rack -> session.save(rack));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the rack table in database through Hibernate.
     *
     * @param racks
     */
    public static void updateRacks(List<Rack> racks) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            racks.forEach(rack -> session.update(rack));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the rack table in database through Hibernate.
     *
     * @param racks
     */
    public static void deleteRacks(List<Rack> racks) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            racks.forEach(rack -> session.delete(rack));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the rack table in
     * database through Hibernate.
     *
     * @param racks
     */
    public static void mergeRacks(List<Rack> racks) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            racks.forEach(rack -> session.merge(rack));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the rack table in database through Hibernate.
     *
     * @return List &lt Rack>
     */
    public static List<Rack> selectRacks() {
        List<Rack> racks = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Rack.class);
            racks = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return racks;
    }

}
