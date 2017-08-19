/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.process.LoanHasItem;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class LoanHasItemDAO {
 
    /**
     *
     * @param loanHasItem
     */
    public static void insertLoanHasItem(LoanHasItem loanHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(loanHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the loanHasItem table in database through Hibernate.
     *
     * @param loanHasItem
     */
    public static void updateLoanHasItem(LoanHasItem loanHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(loanHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the loanHasItem table in database through Hibernate.
     *
     * @param loanHasItem
     */
    public static void deleteLoanHasItem(LoanHasItem loanHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(loanHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the loanHasItem table in
     * database through Hibernate.
     *
     * @param loanHasItem
     */
    public static void mergeLoanHasItem(LoanHasItem loanHasItem) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(loanHasItem);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the loanHasItem table in database through
     * Hibernate.
     *
     * @param loanHasItems
     */
    public static void insertLoanHasItems(List<LoanHasItem> loanHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasItems.forEach(loanHasItem -> session.save(loanHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the loanHasItem table in database through
     * Hibernate.
     *
     * @param loanHasItems
     */
    public static void updateLoanHasItems(List<LoanHasItem> loanHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasItems.forEach(loanHasItem -> session.update(loanHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the loanHasItem table in database through
     * Hibernate.
     *
     * @param loanHasItems
     */
    public static void deleteLoanHasItems(List<LoanHasItem> loanHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasItems.forEach(loanHasItem -> session.delete(loanHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the loanHasItem table in
     * database through Hibernate.
     *
     * @param loanHasItems
     */
    public static void mergeLoanHasItems(List<LoanHasItem> loanHasItems) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            loanHasItems.forEach(loanHasItem -> session.merge(loanHasItem));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the loanHasItem table in database through
     * Hibernate.
     *
     * @return List &lt LoanHasItem>
     */
    public static List<LoanHasItem> selectLoanHasItems() {
        List<LoanHasItem> loanHasItems = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(LoanHasItem.class);          
            loanHasItems = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return loanHasItems;
    }
    
    
}
