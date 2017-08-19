/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.model.sistema.ImageFile;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Alefe Lucas
 */
public abstract class ImageFileDAO {

    /**
     * Inserts a new record into the image table in database through Hibernate.
     * @param imageFile
     */
    public static void insertImageFile(ImageFile imageFile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(imageFile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates a record in the image table in database through Hibernate.
     * @param imageFile
     */
    public static void updateImageFile(ImageFile imageFile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(imageFile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes a record in the image table in database through Hibernate.
     * @param imageFile
     */
    public static void deleteImageFile(ImageFile imageFile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(imageFile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists a record in the image table in database through Hibernate.
     * @param imageFile
     */
    public static void mergeImageFile(ImageFile imageFile) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.merge(imageFile);

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }
    
    /**
     * Inserts several new records into the image table in database through Hibernate.
     * @param imageFiles
     */
    public static void insertImageFiles(List<ImageFile> imageFiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            imageFiles.forEach(imageFile -> session.save(imageFile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates several records in the image table in database through Hibernate.
     * @param imageFiles
     */
    public static void updateImageFiles(List<ImageFile> imageFiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            imageFiles.forEach(imageFile -> session.update(imageFile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Deletes several records in the image table in database through Hibernate.
     * @param imageFiles
     */
    public static void deleteImageFiles(List<ImageFile> imageFiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            imageFiles.forEach(imageFile -> session.delete(imageFile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Updates or inserts if not exists several records in the image table in database through Hibernate.
     * @param imageFiles
     */
    public static void mergeImageFiles(List<ImageFile> imageFiles) {

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            imageFiles.forEach(imageFile -> session.merge(imageFile));

            transaction.commit();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Lists all the records from the image table in database through Hibernate.
     * @return List &lt ImageFile>
     */
    public static List<ImageFile> selectImageFiles() {
        List<ImageFile> imageFiles = new ArrayList<>();

        try {
            Session session = SessionHibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(ImageFile.class);

            imageFiles = criteria.list();
            session.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return imageFiles;
    }
}
