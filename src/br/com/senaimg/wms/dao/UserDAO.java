/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.exception.UserDeletedException;
import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.UnresolvableObjectException;

/**
 *
 * @author Alefe Lucas
 */
public abstract class UserDAO {

    /**
     * Inserts a new record into the user table in database through Hibernate.
     *
     * @param user
     */
    public static void insertUser(User user) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the user table in database through Hibernate.
     *
     * @param user
     */
    public static void updateUser(User user) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the user table in database through Hibernate.
     *
     * @param user
     */
    public static void deleteUser(User user) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(user);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the user table in database
     * through Hibernate.
     *
     * @param user
     */
    public static void mergeUser(User user) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(user);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the user table in database through
     * Hibernate.
     *
     * @param users
     */
    public static void insertUsers(List<User> users) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            users.forEach(user -> session.save(user));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the user table in database through Hibernate.
     *
     * @param users
     */
    public static void updateUsers(List<User> users) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            users.forEach(user -> session.update(user));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the user table in database through Hibernate.
     *
     * @param users
     */
    public static void deleteUsers(List<User> users) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            users.forEach(user -> session.delete(user));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the user table in
     * database through Hibernate.
     *
     * @param users
     */
    public static void mergeUsers(List<User> users) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            users.forEach(user -> session.merge(user));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the user table in database through Hibernate.
     *
     * @return
     */
    public static List<User> selectUsers() {
        List<User> users = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(User.class);

            users = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return users;
    }

    /**
     *
     * @param user
     * @throws UserDeletedException
     */
    public static void refresh(User user) throws UserDeletedException {
        List<User> users = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            try {
                session.refresh(user);
            } catch (UnresolvableObjectException ex) {
                throw new UserDeletedException(ex.getMessage());
            }

            session.close();
        } catch (UserDeletedException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
}
