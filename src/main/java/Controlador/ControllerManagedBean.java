/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package Controlador;

import Modelo.GestorBD;
import Modelo.Noticias;
import Modelo.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author EduardoCruz
 */
@Named("controllerManagedBean")
@SessionScoped
public class ControllerManagedBean implements Serializable{
    private String dpi,pass,nombre,apellido,correo,genero;
    private String menu;
    private String nacimiento;
    private Blob avatar;
    private GestorBD gestorBD;
    
    private static ArrayList<Usuario> ListaUsuarios;
    

    public ControllerManagedBean() {
        gestorBD=new GestorBD();
        ListaUsuarios=gestorBD.ListaUsuarios();
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Blob getAvatar() {
        return avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public static ArrayList<Usuario> getListaUsuarios() {
        return ListaUsuarios;
    }

    public static void setListaUsuarios(ArrayList<Usuario> ListaUsuarios) {
        ControllerManagedBean.ListaUsuarios = ListaUsuarios;
    }

    public GestorBD getGestorBD() {
        return gestorBD;
    }

    public void setGestorBD(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
    }
    
    
    
    public void DatosAgregar(){
    try{
        FacesContext.getCurrentInstance().getExternalContext().redirect("Registrar.xhtml");
        }catch(IOException ex) {
        Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarUsuario(){
        Usuario nuevo = new Usuario(dpi,pass,nombre,apellido,nacimiento,correo,genero,avatar);
        
        if (gestorBD.guardarUsuario(nuevo)){
            try{
                ListaUsuarios = gestorBD.ListaUsuarios();           
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void DatosBorrar(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }catch(IOException ex) {
            Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void borrarUsuario(){ 
        Usuario borrar = new Usuario(dpi,pass,nombre,apellido,nacimiento,correo,genero,avatar);
        if (gestorBD.borrarUsuario(borrar)){
            try{
                ListaUsuarios = gestorBD.ListaUsuarios();
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void localizarUsuario() {
        ArrayList<Usuario> usu=new ArrayList<>();
        
        
        this.nombre=gestorBD.DatosUsuario(dpi).getNombre();
        this.correo=gestorBD.DatosUsuario(dpi).getCorreo();
        this.genero=gestorBD.DatosUsuario(dpi).getGenero();
        this.apellido=gestorBD.DatosUsuario(dpi).getApellido();
        this.nacimiento=gestorBD.DatosUsuario(dpi).getNacimiento();
        this.pass=gestorBD.DatosUsuario(dpi).getPass();
        
        
        if(gestorBD.localizarUsuario(dpi,pass)==true)
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("Noticias.xhtml");
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        else{
            try{
                ListaUsuarios = gestorBD.ListaUsuarios();
                FacesContext.getCurrentInstance().getExternalContext().redirect("error.xhtml");
            }catch(IOException ex) {
                    Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    public void modificarUsuario(){
        Usuario cambiar = new Usuario(dpi,pass,nombre,apellido,nacimiento,correo,genero,avatar);
    

        if (gestorBD.modificarUsuario(cambiar)){
            try{
                ListaUsuarios = gestorBD.ListaUsuarios();
                FacesContext.getCurrentInstance().getExternalContext().redirect("Noticias.xhtml");
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
