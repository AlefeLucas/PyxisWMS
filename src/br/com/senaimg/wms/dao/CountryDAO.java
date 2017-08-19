/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.location.Country;
import br.com.senaimg.wms.model.location.State;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Alefe Lucas
 */
public abstract class CountryDAO {

    /**
     * Inserts a new record into the country table in database through Hibernate.
     * @param country
     */
    public static void insertCountry(Country country) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(country);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the country table in database through Hibernate.
     * @param country
     */
    public static void updateCountry(Country country) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(country);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the country table in database through Hibernate.
     * @param country
     */
    public static void deleteCountry(Country country) {

        
        List<State> states = country.getStates();
        if(states != null){
            for(State state: states){
                state.delete();
            }                
        }
        
        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(country);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the country table in database through Hibernate.
     * @param country
     */
    public static void mergeCountry(Country country) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(country);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the country table in database through Hibernate.
     * @param countries
     */
    public static void insertCountries(List<Country> countries) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            countries.forEach(country -> session.save(country));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the country table in database through Hibernate.
     * @param countries
     */
    public static void updateCountries(List<Country> countries) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            countries.forEach(country -> session.update(country));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the country table in database through Hibernate.
     * @param countries
     */
    public static void deleteCountries(List<Country> countries) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            countries.forEach(country -> session.delete(country));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the country table in database through Hibernate.
     * @param countries
     */
    public static void mergeCountries(List<Country> countries) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            countries.forEach(country -> session.merge(country));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the country table in database through Hibernate.
     * @return List &lt Country>
     */
    public static List<Country> selectCountries() {
        List<Country> countries = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Country.class);
            criteria.addOrder(Order.asc("name"));
            countries = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return countries;
    }   
}
