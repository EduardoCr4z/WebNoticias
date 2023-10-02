/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author EduardoCruz
 */
public class ConectaBD {
    private Connection conexion=null;
    private String servidor="localhost";
    private String database= "proyecto";
    private String usuario="root";
    private String password="123456";
    private String url="";
    
    public ConectaBD(){
        try {
            // Establece la conexión con la base de datos
            Class.forName("com.mysql.cj.jdbc.Driver");
            //?verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true";
            url="jdbc:mysql://"+servidor+"/"+database+"?verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true";
            conexion=DriverManager.getConnection(url, usuario,password);
            if (conexion!= null) {
                    System.out.println("Base de datos " + database + " disponible...");
                }        

            }
        catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Excepcion en ConectaBD "+ex.toString());
        }
    }
    
     public Connection getConexion(){
      return conexion;
    }
    
    public Connection cerrarConexion(){
    try {
      conexion.close();
    } catch (SQLException ex) {
    System.out.println(ex);
    }
    conexion=null;
    return conexion;
    }
}
