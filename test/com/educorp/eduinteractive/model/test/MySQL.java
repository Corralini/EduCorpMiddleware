package com.educorp.eduinteractive.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
 
public class MySQL {

    private static Connection Conexion;
 
    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://10.53.124.208:3306/" + db_name + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" , user, pass);
            JOptionPane.showMessageDialog(null, "Se ha iniciado la conexión con el servidor");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
 
 
    public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
 
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("ID_Estudiante") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido1") + " "
                        + "Email: "+ resultSet.getString("Email") + " " + "Fecha subs: " + resultSet.getDate("fecha_subscripcion"));
            }
            resultSet.close();
            st.close();
        } catch (SQLException ex) {
        	ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
    
    public void createTable(String name) {
        try {
            String Query = "CREATE TABLE " + name + ""
                    + "(ID VARCHAR(25),Nombre VARCHAR(50), Apellido VARCHAR(50),"
                    + " Edad VARCHAR(3), Sexo VARCHAR(1))";
            JOptionPane.showMessageDialog(null, "Se ha creado la base de tabla " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertData(String table_name,String email, String name, String lastname) {
        try {
            String Query = "INSERT INTO " + table_name + "(email, Nombre, Apellido1)" + " VALUES("
            		+ "\"" + email + "\", "
            		+ "\"" + name + "\", "
                    + "\"" + lastname + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            st.close();
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
        	ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
    public void closeConnection() {
        try {
            Conexion.close();
            JOptionPane.showMessageDialog(null,"Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID_PROFESOR >= \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            st.close();
            Conexion.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }
    
 
 
 public static void main(String[] args) {
        MySQL db = new MySQL();
        db.MySQLConnection("eclipse", "", "educorp");
        db.getValues("Estudiante");
        db.closeConnection();
    }
    
}