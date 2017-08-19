/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public abstract class ConnectionFactory {

    public static Connection getConnection() {
        try {

            return DriverManager.getConnection("jdbc:mysql://localhost:3306/pyxis_wms", "root", "admin");

        } catch (SQLException ex) {
            System.err.println("Can't connect");  
            ex.printStackTrace();
            
        }

        return null;

    }

}