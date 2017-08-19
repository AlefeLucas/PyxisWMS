/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Customer;

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
public abstract class CustomerDAO {

    /**
     *
     * @param customer
     */
    public static void insertCustomer(Customer customer) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(customer);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();

    }

    /**
     * Updates a record in the customer table in database through Hibernate.
     *
     * @param customer
     */
    public static void updateCustomer(Customer customer) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(customer);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the customer table in database through Hibernate.
     *
     * @param customer
     */
    public static void deleteCustomer(Customer customer) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(customer);

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
     * Updates or inserts if not exists a record in the customer table in
     * database through Hibernate.
     *
     * @param customer
     */
    public static void mergeCustomer(Customer customer) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(customer);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the customer table in database through
     * Hibernate.
     *
     * @param customers
     */
    public static void insertCustomers(List<Customer> customers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            customers.forEach(customer -> session.save(customer));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the customer table in database through
     * Hibernate.
     *
     * @param customers
     */
    public static void updateCustomers(List<Customer> customers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            customers.forEach(customer -> session.update(customer));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the customer table in database through
     * Hibernate.
     *
     * @param customers
     */
    public static void deleteCustomers(List<Customer> customers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            customers.forEach(customer -> session.delete(customer));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the customer table in
     * database through Hibernate.
     *
     * @param customers
     */
    public static void mergeCustomers(List<Customer> customers) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            customers.forEach(customer -> session.merge(customer));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the customer table in database through
     * Hibernate.
     *
     * @return List &lt Customer>
     */
    public static List<Customer> selectCustomers() {
        List<Customer> customers = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Customer.class);
            customers = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return customers;
    }

    public static List<Customer> selectEmployees() {
        List<Customer> customers = selectCustomers();
        List<Customer> employees = new ArrayList<>();

        for (Customer customer : customers) {
            if (customer.isEmployee()) {
                employees.add(customer);
            }
        }
        return employees;
    }

}
