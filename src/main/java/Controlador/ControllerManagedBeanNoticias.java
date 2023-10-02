/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.GestorBD;
import Modelo.Noticias;
import java.io.IOException;
import java.io.Serializable;
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
@Named("controllerManagedBeanNoticias")
@SessionScoped
public class ControllerManagedBeanNoticias implements Serializable{
    private String titulo,cuerpo,autor,fecha,foto,categoria,pais;
    private Noticias noticias;
    private GestorBD gestorBD;
    
    private static ArrayList<Noticias> ListaNoticias;
    public static ArrayList<ArrayList<String>> ListaPaises;
    public static ArrayList<ArrayList<String>> ListaCategorias;

    public ControllerManagedBeanNoticias() {
        gestorBD=new GestorBD();
        noticias=new Noticias(titulo,cuerpo,autor,fecha,foto,categoria,pais);
        
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public GestorBD getGestorBD() {
        return gestorBD;
    }

    public void setGestorBD(GestorBD gestorBD) {
        this.gestorBD = gestorBD;
    }

    public ArrayList<Noticias> getListaNoticias() {
        return ListaNoticias;
    }

    public static void setListaNoticias(ArrayList<Noticias> ListaNoticias) {
        ControllerManagedBeanNoticias.ListaNoticias = ListaNoticias;
    }

    public ArrayList<ArrayList<String>> getListaPaises() {
        return ListaPaises;
    }

    public static void setListaPaises(ArrayList<ArrayList<String>> ListaPaises) {
        ControllerManagedBeanNoticias.ListaPaises = ListaPaises;
    }

    public ArrayList<ArrayList<String>> getListaCategorias() {
        return ListaCategorias;
    }

    public static void setListaCategorias(ArrayList<ArrayList<String>> ListaCategorias) {
        ControllerManagedBeanNoticias.ListaCategorias = ListaCategorias;
    }
    
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    

    public void buscarNoticia(){
        gestorBD.vaciarNoticias();
        gestorBD.vaciarAutor();
        System.out.println("Pais: "+pais+", Categoria: "+categoria);
        try{
            Noticias buscar = new Noticias(titulo,cuerpo,autor,fecha,foto,categoria,pais);
            ListaNoticias=gestorBD.buscarNoticia(buscar);
            ListaPaises=gestorBD.organizarPaises(buscar);
            ListaCategorias=gestorBD.organizarCategorias(buscar);
            System.out.println(ListaPaises);
            FacesContext.getCurrentInstance().getExternalContext().redirect("Resultado.xhtml");
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Busqueda","Noticia encontrada"));
        }catch(IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Busqueda","Noticia no encontrada"));
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void localizarNoticia(String titulo,String cuerpo,String Autor, String Fecha, String Foto, String Categoria){
        this.titulo=titulo;
        this.cuerpo=cuerpo;
        this.autor=Autor;
        this.fecha=Fecha;
        this.foto=Foto;
        this.categoria=Categoria;
        
        if(gestorBD.localizarNoticia(titulo))
            try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("Mostrar.xhtml");
                System.out.println("Mostrando titulo "+titulo);
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    public void volver(){
        try{
                FacesContext.getCurrentInstance().getExternalContext().redirect("Resultado.xhtml");
                System.out.println("Volviendo...");
            }catch(IOException ex) {
                Logger.getLogger(ControllerManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
