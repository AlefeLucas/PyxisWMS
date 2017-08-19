/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.location.Address;
import br.com.senaimg.wms.model.location.City;
import br.com.senaimg.wms.model.location.State;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Alefe Lucas
 */
public abstract class CityDAO {

    /**
     * Inserts a new record into the city table in database through Hibernate.
     * @param city
     */
    public static void insertCity(City city) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(city);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the city table in database through Hibernate.
     * @param city
     */
    public static void updateCity(City city) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(city);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the city table in database through Hibernate.
     * @param city
     */
    public static void deleteCity(City city) {

        List<Address> addresses = city.getAddresses();
        if(addresses != null){
            for(Address address : addresses){
                address.setCity(null);
                address.update();
            }
        }
        
        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(city);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the city table in database through Hibernate.
     * @param city
     */
    public static void mergeCity(City city) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(city);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the city table in database through Hibernate.
     * @param cities
     */
    public static void insertCities(List<City> cities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            cities.forEach(city -> session.save(city));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the city table in database through Hibernate.
     * @param cities
     */
    public static void updateCities(List<City> cities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            cities.forEach(city -> session.update(city));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the city table in database through Hibernate.
     * @param cities
     */
    public static void deleteCities(List<City> cities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            cities.forEach(city -> session.delete(city));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the city table in database through Hibernate.
     * @param cities
     */
    public static void mergeCities(List<City> cities) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            cities.forEach(city -> session.merge(city));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the city table in database through Hibernate.
     * @return List &lt City>
     */
    public static List<City> selectCities() {
        List<City> cities = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(City.class);
criteria.addOrder(Order.asc("name"));
            cities = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return cities;
    }
    
    /**
     * Lists records from the city table associated with a given state record in database through Hibernate.
     * @param state
     * @return List &lt City>
     */
    public static List<City> selectCities(State state) {
        List<City> cities = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(City.class);

            if (state != null) {
                criteria.createAlias("state", "state");
                criteria.add(Restrictions.eq("state.id", state.getId()));
            }

            cities = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return cities;
    }
}
