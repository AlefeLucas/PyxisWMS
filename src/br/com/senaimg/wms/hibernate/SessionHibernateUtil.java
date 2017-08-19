/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.hibernate;

import br.com.senaimg.wms.language.Lang;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Alefe Lucas
 */
public class SessionHibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Gets the Session Factory
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Create the SessionFactory from standard (hibernate.cfg.xml) 
                // config file.
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (HibernateException ex) {

                // Log the exception. 
                System.err.println("Initial SessionFactory creation failed." + ex);
                JOptionPane.showMessageDialog(null, Lang.get("Can't load MySql"), Lang.get("Error"), JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
                System.exit(1);

                throw new ExceptionInInitializerError(ex);

            }
        }
        return sessionFactory;
    }

    public static void setSessionFactory(SessionFactory aSessionFactory) {
        if (aSessionFactory == null) {
            sessionFactory = aSessionFactory;
        }

    }

}
