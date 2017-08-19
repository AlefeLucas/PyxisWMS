/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.Settings;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class SettingsDAO {
//
//    /**
//     * Inserts a new record into the settings table in database through
//     * Hibernate.
//     *
//     * @param settings
//     */
//    public static void insertSettings(Settings settings) {
//
//        try {
//            Session session = SessionHibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//
//            session.save(settings);
//
//            transaction.commit();
//            session.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
//
//        Lang.loadLanguage();
//        
//    }

//    /**
//     * Updates a record in the settings table in database through Hibernate.
//     *
//     * @param settings
//     */
//    public static void updateSettings(Settings settings) {
//
//        try {
//            Session session = SessionHibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//
//            session.update(settings);
//
//            transaction.commit();
//            session.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
//
//    }

    /**
     * Deletes a record in the settings table in database through Hibernate.
     *
     * @param settings
     */
    public static void deleteSettings(Settings settings) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(settings);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the settings table in
     * database through Hibernate.
     *
     * @param settings
     */
    public static void mergeSettings(Settings settings) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(settings);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

//    /**
//     * Inserts several new records into the settings table in database through
//     * Hibernate.
//     *
//     * @param settingsList
//     */
//    public static void insertSettingsList(List<Settings> settingsList) {
//
//        try {
//            Session session = SessionHibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//
//            settingsList.forEach(settings -> session.save(settings));
//
//            transaction.commit();
//            session.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
//
//    }
//
//    /**
//     * Updates several records in the settings table in database through
//     * Hibernate.
//     *
//     * @param settingsList
//     */
//    public static void updateSettingsList(List<Settings> settingsList) {
//
//        try {
//            Session session = SessionHibernateUtil.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//
//            settingsList.forEach(settings -> session.update(settings));
//
//            transaction.commit();
//            session.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
//
//    }

    /**
     * Deletes several records in the settings table in database through
     * Hibernate.
     *
     * @param settingsList
     */
    public static void deleteSettingsList(List<Settings> settingsList) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            settingsList.forEach(settings -> session.delete(settings));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the settings table in
     * database through Hibernate.
     *
     * @param settingsList
     */
    public static void mergeSettingsList(List<Settings> settingsList) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            settingsList.forEach(settings -> session.merge(settings));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the settings table in database through
     * Hibernate.
     *
     * @return List &lt Settings>
     */
    public static List<Settings> selectSettingsList() {
        List<Settings> settingsList = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
        
            Criteria criteria = session.createCriteria(Settings.class);
            criteria.addOrder(Order.desc("dateSaved"));
         
            settingsList = criteria.list();
        
            session.close();
      
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    
        if(settingsList.isEmpty()){
     
        }
        for(Settings s : settingsList){
            System.out.println("Lang: " + s.getLanguageSystem().toString());
        }
            return settingsList;
    }

    /**
     *
     * @return
     */
    public static Settings selectSettingsLast() {
        List<Settings> settingsList = selectSettingsList();

        if(settingsList.isEmpty()){
            return null;
        }
        Settings set = settingsList.get(0);

        for (Settings s : settingsList) {
            if (s.getDateSaved().getTime() > set.getDateSaved().getTime()) {
                set = s;
            }
        }

        return set;
       
    }
}
