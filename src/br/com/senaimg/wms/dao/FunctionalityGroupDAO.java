/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.FunctionalityGroup;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alefe Lucas
 */
public abstract class FunctionalityGroupDAO {

    /**
     * Inserts a new record into the functionality_group table in database
     * through Hibernate.
     *
     * @param functionalityGroup
     */
    public static void insertFunctionalityGroup(FunctionalityGroup functionalityGroup) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(functionalityGroup);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the functionality_group table in database through
     * Hibernate.
     *
     * @param functionalityGroup
     */
    public static void updateFunctionalityGroup(FunctionalityGroup functionalityGroup) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(functionalityGroup);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the functionality_group table in database through
     * Hibernate.
     *
     * @param functionalityGroup
     */
    public static void deleteFunctionalityGroup(FunctionalityGroup functionalityGroup) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(functionalityGroup);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the functionality_group
     * table in database through Hibernate.
     *
     * @param functionalityGroup
     */
    public static void mergeFunctionalityGroup(FunctionalityGroup functionalityGroup) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(functionalityGroup);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the functionality_group table in
     * database through Hibernate.
     *
     * @param functionalityGroups
     */
    public static void insertFunctionalityGroups(List<FunctionalityGroup> functionalityGroups) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalityGroups.forEach(functionalityGroup -> session.save(functionalityGroup));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the functionality_group table in database
     * through Hibernate.
     *
     * @param functionalityGroups
     */
    public static void updateFunctionalityGroups(List<FunctionalityGroup> functionalityGroups) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalityGroups.forEach(functionalityGroup -> session.update(functionalityGroup));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the functionality_group table in database
     * through Hibernate.
     *
     * @param functionalityGroups
     */
    public static void deleteFunctionalityGroups(List<FunctionalityGroup> functionalityGroups) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalityGroups.forEach(functionalityGroup -> session.delete(functionalityGroup));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the
     * functionality_group table in database through Hibernate.
     *
     * @param functionalityGroups
     */
    public static void mergeFunctionalityGroups(List<FunctionalityGroup> functionalityGroups) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            functionalityGroups.forEach(functionalityGroup -> session.merge(functionalityGroup));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the functionality_group table in database
     * through Hibernate.
     *
     * @return List &lt FunctionalityGroup>
     */
    public static List<FunctionalityGroup> selectFunctionalityGroups() {
        List<FunctionalityGroup> functionalityGroups = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(FunctionalityGroup.class);
//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            Query query = session.createQuery("From FunctionalityGroup");
//            
//            functionalityGroups = query.list();
            functionalityGroups = criteria.list();

            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return functionalityGroups;
    }

    /**
     *
     * @param group
     */
    public static void refresh(FunctionalityGroup group) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            session.refresh(group);
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        } finally {

        }

    }
}
