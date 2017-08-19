/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.LoanHasMetaItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class LoanHasMetaItemDAO {
 
    /**
     *
     * @param loanHasMetaItem
     */
    public static void insertLoanHasMetaItem(LoanHasMetaItem loanHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(loanHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the loanHasMetaItem table in database through Hibernate.
     *
     * @param loanHasMetaItem
     */
    public static void updateLoanHasMetaItem(LoanHasMetaItem loanHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(loanHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the loanHasMetaItem table in database through Hibernate.
     *
     * @param loanHasMetaItem
     */
    public static void deleteLoanHasMetaItem(LoanHasMetaItem loanHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(loanHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the loanHasMetaItem table in
     * database through Hibernate.
     *
     * @param loanHasMetaItem
     */
    public static void mergeLoanHasMetaItem(LoanHasMetaItem loanHasMetaItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(loanHasMetaItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the loanHasMetaItem table in database through
     * Hibernate.
     *
     * @param loanHasMetaItems
     */
    public static void insertLoanHasMetaItems(List<LoanHasMetaItem> loanHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasMetaItems.forEach(loanHasMetaItem -> session.save(loanHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the loanHasMetaItem table in database through
     * Hibernate.
     *
     * @param loanHasMetaItems
     */
    public static void updateLoanHasMetaItems(List<LoanHasMetaItem> loanHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasMetaItems.forEach(loanHasMetaItem -> session.update(loanHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the loanHasMetaItem table in database through
     * Hibernate.
     *
     * @param loanHasMetaItems
     */
    public static void deleteLoanHasMetaItems(List<LoanHasMetaItem> loanHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasMetaItems.forEach(loanHasMetaItem -> session.delete(loanHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the loanHasMetaItem table in
     * database through Hibernate.
     *
     * @param loanHasMetaItems
     */
    public static void mergeLoanHasMetaItems(List<LoanHasMetaItem> loanHasMetaItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasMetaItems.forEach(loanHasMetaItem -> session.merge(loanHasMetaItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the loanHasMetaItem table in database through
     * Hibernate.
     *
     * @return List &lt LoanHasMetaItem>
     */
    public static List<LoanHasMetaItem> selectLoanHasMetaItems() {
        List<LoanHasMetaItem> loanHasMetaItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(LoanHasMetaItem.class);          
            loanHasMetaItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return loanHasMetaItems;
    }
    
    
}
