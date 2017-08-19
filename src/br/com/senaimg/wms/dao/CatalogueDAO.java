/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.agent.Catalogue;
import br.com.senaimg.wms.model.warehouse.agent.Supplier;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class CatalogueDAO {
 
    /**
     *
     * @param catalogue
     */
    public static void insertCatalogue(Catalogue catalogue) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(catalogue);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Lang.loadLanguage();
        
    }

    /**
     * Updates a record in the catalogue table in database through Hibernate.
     *
     * @param catalogue
     */
    public static void updateCatalogue(Catalogue catalogue) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(catalogue);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the catalogue table in database through Hibernate.
     *
     * @param catalogue
     */
    public static void deleteCatalogue(Catalogue catalogue) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(catalogue);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the catalogue table in
     * database through Hibernate.
     *
     * @param catalogue
     */
    public static void mergeCatalogue(Catalogue catalogue) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(catalogue);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Inserts several new records into the catalogue table in database through
     * Hibernate.
     *
     * @param catalogues
     */
    public static void insertCatalogues(List<Catalogue> catalogues) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            catalogues.forEach(catalogue -> session.save(catalogue));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the catalogue table in database through
     * Hibernate.
     *
     * @param catalogues
     */
    public static void updateCatalogues(List<Catalogue> catalogues) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            catalogues.forEach(catalogue -> session.update(catalogue));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the catalogue table in database through
     * Hibernate.
     *
     * @param catalogues
     */
    public static void deleteCatalogues(List<Catalogue> catalogues) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            catalogues.forEach(catalogue -> session.delete(catalogue));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the catalogue table in
     * database through Hibernate.
     *
     * @param catalogues
     */
    public static void mergeCatalogues(List<Catalogue> catalogues) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            catalogues.forEach(catalogue -> session.merge(catalogue));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the catalogue table in database through
     * Hibernate.
     *
     * @return List &lt Catalogue>
     */
    public static List<Catalogue> selectCatalogues() {
        List<Catalogue> catalogues = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Catalogue.class);          
            catalogues = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return catalogues;
    }

    public static List<Catalogue> selectCatalogues(Supplier supplier) {
        List<Catalogue> catalogues = selectCatalogues();
        
        ArrayList<Catalogue> selected = new ArrayList<>();
        
        for(Catalogue catalogue : catalogues){
            if(catalogue.getSupplier().equals(supplier)){
                selected.add(catalogue);
            }
        }
        
        return selected;
    }
    
    
}
