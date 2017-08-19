/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.Permission;
import br.com.senaimg.wms.model.sistema.Profile;
import br.com.senaimg.wms.model.sistema.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alefe Lucas
 */
public abstract class ProfileDAO {

    /**
     * Inserts a new record into the profile table in database through Hibernate.
     * @param profile
     */
    public static void insertProfile(Profile profile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(profile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     *  Updates a record in the profile table in database through Hibernate.
     * @param profile
     */
    public static void updateProfile(Profile profile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();            
            session.update(profile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the profile table in database through Hibernate.
     * @param profile
     */
    public static void deleteProfile(Profile profile) {

        for(User user : profile.getUsers()){
            user.setProfile(null);
            user.update();            
        }
        
        profile.setUsers(null);
        
        for (Permission permission : profile.getPermissions()){
            permission.delete();
        }
        
        try {
            
            
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            
            
            session.delete(profile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the profile table in database through Hibernate.
     * @param profile
     */
    public static void mergeProfile(Profile profile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(profile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the profile table in database through Hibernate.
     * @param profiles
     */
    public static void insertProfiles(List<Profile> profiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            profiles.forEach(profile -> session.save(profile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the profile table in database through Hibernate.
     * @param profiles
     */
    public static void updateProfiles(List<Profile> profiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            profiles.forEach(profile -> session.update(profile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the profile table in database through Hibernate.
     * @param profiles
     */
    public static void deleteProfiles(List<Profile> profiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            profiles.forEach(profile -> session.delete(profile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the profile table in database through Hibernate.
     * @param profiles
     */
    public static void mergeProfiles(List<Profile> profiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            profiles.forEach(profile -> session.merge(profile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the profile table in database through Hibernate.
     * @return List &lt Profile>
     */
    public static List<Profile> selectProfiles() {
        List<Profile> profiles = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Profile.class);
        
profiles =  criteria.list();

            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return profiles;
    }
}
