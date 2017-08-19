/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.item.OperationType;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class OperationTypeDAO {
 
    /**
     *
     * @param operationType
     */
    public static void insertOperationType(OperationType operationType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(operationType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the operationType table in database through Hibernate.
     *
     * @param operationType
     */
    public static void updateOperationType(OperationType operationType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(operationType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the operationType table in database through Hibernate.
     *
     * @param operationType
     */
    public static void deleteOperationType(OperationType operationType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(operationType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the operationType table in
     * database through Hibernate.
     *
     * @param operationType
     */
    public static void mergeOperationType(OperationType operationType) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(operationType);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the operationType table in database through
     * Hibernate.
     *
     * @param operationTypes
     */
    public static void insertOperationTypes(List<OperationType> operationTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            operationTypes.forEach(operationType -> session.save(operationType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the operationType table in database through
     * Hibernate.
     *
     * @param operationTypes
     */
    public static void updateOperationTypes(List<OperationType> operationTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            operationTypes.forEach(operationType -> session.update(operationType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the operationType table in database through
     * Hibernate.
     *
     * @param operationTypes
     */
    public static void deleteOperationTypes(List<OperationType> operationTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            operationTypes.forEach(operationType -> session.delete(operationType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the operationType table in
     * database through Hibernate.
     *
     * @param operationTypes
     */
    public static void mergeOperationTypes(List<OperationType> operationTypes) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            operationTypes.forEach(operationType -> session.merge(operationType));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the operationType table in database through
     * Hibernate.
     *
     * @return List &lt OperationType>
     */
    public static List<OperationType> selectOperationTypes() {
        List<OperationType> operationTypes = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(OperationType.class);          
            operationTypes = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return operationTypes;
    }
    
    
}
