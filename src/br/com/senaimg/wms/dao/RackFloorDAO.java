/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.place.RackFloor;

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
public abstract class RackFloorDAO {

    /**
     *
     * @param rackFloor
     */
    public static void insertRackFloor(RackFloor rackFloor) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(rackFloor);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the rackFloor table in database through Hibernate.
     *
     * @param rackFloor
     */
    public static void updateRackFloor(RackFloor rackFloor) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(rackFloor);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the rackFloor table in database through Hibernate.
     *
     * @param rackFloor
     */
    public static void deleteRackFloor(RackFloor rackFloor) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(rackFloor);

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
     * Updates or inserts if not exists a record in the rackFloor table in
     * database through Hibernate.
     *
     * @param rackFloor
     */
    public static void mergeRackFloor(RackFloor rackFloor) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(rackFloor);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the rackFloor table in database through
     * Hibernate.
     *
     * @param rackFloors
     */
    public static void insertRackFloors(List<RackFloor> rackFloors) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            rackFloors.forEach(rackFloor -> session.save(rackFloor));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the rackFloor table in database through
     * Hibernate.
     *
     * @param rackFloors
     */
    public static void updateRackFloors(List<RackFloor> rackFloors) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            rackFloors.forEach(rackFloor -> session.update(rackFloor));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the rackFloor table in database through
     * Hibernate.
     *
     * @param rackFloors
     */
    public static void deleteRackFloors(List<RackFloor> rackFloors) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            rackFloors.forEach(rackFloor -> session.delete(rackFloor));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the rackFloor table
     * in database through Hibernate.
     *
     * @param rackFloors
     */
    public static void mergeRackFloors(List<RackFloor> rackFloors) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            rackFloors.forEach(rackFloor -> session.merge(rackFloor));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the rackFloor table in database through
     * Hibernate.
     *
     * @return List &lt RackFloor>
     */
    public static List<RackFloor> selectRackFloors() {
        List<RackFloor> rackFloors = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(RackFloor.class);
            rackFloors = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return rackFloors;
    }

}
