/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.SecQuestion;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alefe Lucas
 */
public abstract class SecQuestionDAO {

    /**
     *  Inserts a new record into the security_question table in database through Hibernate.
     * @param secQuestion
     */
    public static void insertSecQuestion(SecQuestion secQuestion) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(secQuestion);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the security_question table in database through Hibernate.
     * @param secQuestion
     */
    public static void updateSecQuestion(SecQuestion secQuestion) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(secQuestion);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the security_question table in database through Hibernate.
     * @param secQuestion
     */
    public static void deleteSecQuestion(SecQuestion secQuestion) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(secQuestion);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the security_question table in database through Hibernate.
     * @param secQuestion
     */
    public static void mergeSecQuestion(SecQuestion secQuestion) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(secQuestion);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the security_question table in database through Hibernate.
     * @param secQuestions
     */
    public static void insertSecQuestions(List<SecQuestion> secQuestions) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            secQuestions.forEach(secQuestion -> session.save(secQuestion));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the security_question table in database through Hibernate.
     * @param secQuestions
     */
    public static void updateSecQuestions(List<SecQuestion> secQuestions) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            secQuestions.forEach(secQuestion -> session.update(secQuestion));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the security_question table in database through Hibernate.
     * @param secQuestions
     */
    public static void deleteSecQuestions(List<SecQuestion> secQuestions) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            secQuestions.forEach(secQuestion -> session.delete(secQuestion));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the security_question table in database through Hibernate.
     * @param secQuestions
     */
    public static void mergeSecQuestions(List<SecQuestion> secQuestions) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            secQuestions.forEach(secQuestion -> session.merge(secQuestion));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the security_question table in database through Hibernate.
     * @return List &lt SecQuestion>
     */
    public static List<SecQuestion> selectSecQuestions() {
        List<SecQuestion> secQuestions = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(SecQuestion.class);

            secQuestions = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return secQuestions;
    }
}
