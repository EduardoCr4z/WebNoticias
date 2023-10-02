/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author EduardoCruz
 */
public class GestorBD {
    private Connection conexion=null;
    private Statement stm = null;
    private ResultSet rs;
    private String dpi,pass,nombre,apellido,correo,genero;
    private String titulo,cuerpo,autor,fecha,foto,categoria,pais;
    private String nacimiento;
    private Blob avatar;
    
    private int resultUpdate = 0;
    
    
    public ArrayList<Usuario> ListaUsuarios(){
        ArrayList<Usuario> usu = new ArrayList<Usuario>();
        Usuario encontrada;
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            rs = stm.executeQuery("select * from Usuario");
            if(!rs.next()){
                System.out.println("GestorBD.ListaUsuarios: No se encontraron registros");
                conexion.close();
                return null;
            }else{
                do{
                    dpi = rs.getString("Dpi");
                    pass = rs.getString("Pass");
                    nombre = rs.getString("Nombre");
                    apellido = rs.getString("Apellido");
                    correo = rs.getString("Correo");
                    genero = rs.getString("Genero");
                    nacimiento = rs.getString("Nacimiento");
                    avatar = rs.getBlob("Avatar");
                    encontrada =new Usuario(dpi,pass,nombre,apellido,nacimiento,correo,genero,avatar);
                    usu.add(encontrada);
                    }while(rs.next());
                conexion.close();
                return usu;
                }
            }catch(Exception e){
                System.out.println("Error en la base de datos GestorBD.ListaUsuarios"+e.toString());
                e.printStackTrace();
            return null;}
    }
    
    public Usuario DatosUsuario(String dpiA){
        Usuario encontrada;
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            rs = stm.executeQuery("select * from Usuario where(Dpi='"+dpiA+"')");
            if(!rs.next()){
                System.out.println("GestorBD.ListaUsuarios: No se encontraron registros");
                conexion.close();
                return null;
            }else{
                do{
                    dpi = rs.getString("Dpi");
                    pass = rs.getString("Pass");
                    nombre = rs.getString("Nombre");
                    apellido = rs.getString("Apellido");
                    correo = rs.getString("Correo");
                    genero = rs.getString("Genero");
                    nacimiento = rs.getString("Nacimiento");
                    avatar = rs.getBlob("Avatar");
                    encontrada =new Usuario(dpi,pass,nombre,apellido,nacimiento,correo,genero,avatar);
                    }while(rs.next());
                conexion.close();
                return encontrada;
                }
            }catch(Exception e){
                System.out.println("Error en la base de datos GestorBD.ListaUsuarios"+e.toString());
                e.printStackTrace();
            return null;}
    }
    
    public boolean guardarUsuario(Usuario nuevo){
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("insert into Usuario values('"+nuevo.getDpi()+
                    "','"+nuevo.getPass()+"','"+nuevo.getNombre()+"','"+nuevo.getApellido()+"','"+nuevo.getNacimiento()+"','"+
                    nuevo.getCorreo()+"','"+nuevo.getGenero()+"',"+nuevo.getAvatar()+")");
            if(resultUpdate != 0){
                conexion.close();
                return true;
            }else{
                conexion.close();;
                System.out.println("No se pudo insertar El Usuario.");
            return false;
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos.guardarUsuario");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean borrarUsuario(Usuario borrar) {
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("delete from Usuario where(Correo= '"+borrar.getCorreo()+"')");
            if(resultUpdate != 0){
                conexion.close();
                return true;
            }else{
                conexion.close();;
                System.out.println("No se pudo borrar el Usuario en GestorBD");
                return false;
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos en GestorBD.borrarUsuario");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean localizarUsuario(String dpi,String pass) {
    try{
        ConectaBD conectaBD = new ConectaBD();
        conexion = conectaBD.getConexion();
        stm = conexion.createStatement();
        rs = stm.executeQuery("select * from Usuario where(Dpi='"+dpi+"' && Pass='"+pass+"')");
        if(!rs.next()){
            System.out.println("GestorBD.localizarUsuario: No se encontraron registros");
            conexion.close();
            return false;
        }else{
            conexion.close();
            return true; 
        }
        }catch(Exception e){
            System.out.println("Error en la base de datos  GestorBD.localizarUsuario");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean modificarUsuario(Usuario cambiar) {
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("update Usuario set Dpi= '"+cambiar.getDpi()
            +"', Pass = '"+ cambiar.getPass()
            +"', Nombre = '"+ cambiar.getNombre()
            +"',Apellido='"+cambiar.getApellido()
            +"',Nacimiento='"+cambiar.getNacimiento()
            +"',Correo='"+cambiar.getCorreo()
            +"',Genero='"+cambiar.getGenero()
            +"',Avatar='"+cambiar.getAvatar()
            +"' where Dpi = '"+cambiar.getDpi()+"';");
            if(resultUpdate != 0){
                conexion.close();
                return true;
            }else{
                conexion.close();;
                System.out.println("GestorBD.modificarUsuario: No se pudo borrar el usuario.");
                return false;
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos GestorBD.modificarUsuario");
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Noticias> buscarNoticia(Noticias noticia){
        ArrayList<Noticias> buscar=new ArrayList<>();
        String tituloA;
        String cuerpoA;
        String autorA;
        String fechaA;
        String fotoA;
        String categoriaA;
        String paisA;
        
        Noticias agregar;
        String enlace;
        if(noticia.getPais().equals("Internacional")){
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&categories="+noticia.getCategoria().toLowerCase();
            }
            
        }else{
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais().toLowerCase();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais()+"&&categories="+noticia.getCategoria().toLowerCase();
            }     
        }
       System.out.println(enlace);
        try{
            
            URL url=new URL(enlace);
            URLConnection request= url.openConnection();
            request.addRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.0)");
            
            JsonParser jp=new JsonParser();
            JsonElement root=jp.parse(new InputStreamReader((InputStream)request.getContent()));
            if(root.isJsonObject()){
                
                    JsonObject rootobj=root.getAsJsonObject();
                    JsonArray data=rootobj.get("data").getAsJsonArray();
                    

                    for(int i=0;i<data.size();i++){
                        
                        JsonObject obj=data.get(i).getAsJsonObject();
                        tituloA=obj.get("title").toString();
                        cuerpoA=obj.get("description").toString();
                        autorA=obj.get("author").toString();
                        if(autorA.equals("null")){
                            autorA="Anonimo";
                        }
                        fechaA=obj.get("published_at").toString();
                        
                        fotoA=obj.get("image").toString();
                        categoriaA=obj.get("category").toString();
                        paisA=obj.get("country").toString();
                        
                        
                        
                        agregar=new Noticias(tituloA,cuerpoA,autorA,fechaA,fotoA,categoriaA,paisA);
                        
                        
                        guardarAutor(agregar);
                        guardarNoticia(agregar);
                        buscar.add(agregar);
                        
                    }
                }
        }catch(Exception e){
            System.out.println("Problemas en GestorBD.buscarNoticia"+e.toString());
        }
        
        return buscar;
    }
    
    public void vaciarNoticias(){
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("TRUNCATE TABLE Noticias;");
            if(resultUpdate != 0){
                conexion.close();
            }else{
                conexion.close();;
                System.out.println("No se pudo borrar las Noticias en GestorBD");
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos en GestorBD.vaciarNoticias");
            e.printStackTrace();
        }
    }
    
        public void vaciarAutor(){
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("TRUNCATE TABLE Autor;");
            if(resultUpdate != 0){
                conexion.close();
            }else{
                conexion.close();;
                System.out.println("No se pudo borrar el  autor en GestorBD");
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos en GestorBD.vaciarAutor");
            e.printStackTrace();
        }
    }
    
    public boolean guardarAutor(Noticias nueva){
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("insert into Autor values('"+nueva.getAutor()+"','','')");
            System.out.println("Se inserto el autor: "+nueva.getAutor());
            if(resultUpdate != 0){
                conexion.close();
                return true;
            }else{
                conexion.close();;
                System.out.println("No se pudo insertar La noticia.");
            return false;
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos.guardarAutor");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean guardarNoticia(Noticias nueva){
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            resultUpdate = stm.executeUpdate("insert into Noticias values('"+nueva.getTitulo()+
                    "','"+nueva.getCuerpo()+"','"+nueva.getAutor()+"','"+nueva.getFecha()+"','"+nueva.getFoto()+"','"+nueva.getCategoria()+"')");
            System.out.println("Se inserto la noticia: "+nueva.getTitulo());
            if(resultUpdate != 0){
                conexion.close();
                return true;
            }else{
                conexion.close();
                System.out.println("No se pudo insertar La noticia.");
            return false;
            }
        }catch (Exception e) {
            System.out.println("Error en la base de datos.guardarNoticia");
            e.printStackTrace();
            
            return false;
        }
        
    }
    
    public ArrayList<Noticias> ListaNoticias(){
        ArrayList<Noticias> noti = new ArrayList<Noticias>();
        Noticias encontrada;
        try{
            ConectaBD conectaBD = new ConectaBD();
            conexion = conectaBD.getConexion();
            stm = conexion.createStatement();
            rs = stm.executeQuery("select * from Noticias");
            if(!rs.next()){
                System.out.println("GestorBD.ListaNoticias: No se encontraron registros");
                conexion.close();
                return null;
            }else{
                do{
                    titulo = rs.getString("Titulo");
                    cuerpo = rs.getString("Cuerpo");
                    autor = rs.getString("Autor");
                    fecha = rs.getString("Fecha");
                    foto = rs.getString("Foto");
                    categoria = rs.getString("Categoria");
                    pais="";
                    encontrada =new Noticias(titulo,cuerpo,autor,fecha,foto,categoria,pais);
                    noti.add(encontrada);
                    }while(rs.next());
                conexion.close();
                return noti;
                }
            }catch(Exception e){
                System.out.println("Error en la base de datos GestorBD.ListaNoticias: "+e.toString());
                e.printStackTrace();
            return null;}
    }
    
    
    
     public boolean localizarNoticia(String titulo) {
    try{
        ConectaBD conectaBD = new ConectaBD();
        conexion = conectaBD.getConexion();
        stm = conexion.createStatement();
        rs = stm.executeQuery("select * from Noticias where(Titulo='"+titulo+"')");
        if(!rs.next()){
            System.out.println("GestorBD.localizarNoticia: No se encontraron registros");
            conexion.close();
            return false;
        }else{
            conexion.close();
            return true; 
        }
        }catch(Exception e){
            System.out.println("Error en la base de datos  GestorBD.localizarNoticia");
            e.printStackTrace();
            return false;
        }
    }
     
    public ArrayList<ArrayList<String>> organizarPaises(Noticias noticia){
        ArrayList<String> buscar=new ArrayList<>();//{al,al,al,es}
        ArrayList<String> filtrado=new ArrayList<>();//{al,al,al}
        ArrayList<ArrayList<String>>  filtrado2=new ArrayList<>();//{{al,al,al}{es,es,es,es}}
        String paisA;
        String enlace;
        if(noticia.getPais().equals("Internacional")){
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&categories="+noticia.getCategoria().toLowerCase();
            }
            
        }else{
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais().toLowerCase();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais()+"&&categories="+noticia.getCategoria().toLowerCase();
            }     
        }
       System.out.println(enlace);
        try{
            
            URL url=new URL(enlace);
            URLConnection request= url.openConnection();
            request.addRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.0)");
            
            JsonParser jp=new JsonParser();
            JsonElement root=jp.parse(new InputStreamReader((InputStream)request.getContent()));
            if(root.isJsonObject()){
                
                    JsonObject rootobj=root.getAsJsonObject();
                    JsonArray data=rootobj.get("data").getAsJsonArray(); 
                    for(int i=0;i<data.size();i++){
                        
                        JsonObject obj=data.get(i).getAsJsonObject();
                        paisA=obj.get("country").toString();
                        buscar.add(paisA);//{es,al,es,us,al,es,es}
                        
                    }
                    
                    Collections.sort(buscar);//{es,es,es,es,al,al,us}
                    
                    for(int  i=0;i<buscar.size();i++){
                        if(filtrado.contains(buscar.get(i))){
                            filtrado.add(buscar.get(i));
                        }else{
                            filtrado=new ArrayList<>();
                            filtrado.add(buscar.get(i));
                            filtrado2.add(filtrado);
                        }
                    }
                   //BUSCAR //{es,es,al,al,us}
                   //FILTRADO //{al,al}
                   //FILTRADO2 //{{es,es}{al,al}}
                }
        }catch(Exception e){
            System.out.println("Problemas en GestorBD.buscarNoticia"+e.toString());
        }
        
        return filtrado2;
    } 
    
    public ArrayList<ArrayList<String>> organizarCategorias(Noticias noticia){
        ArrayList<String> buscar=new ArrayList<>();
        ArrayList<String> filtrado=new ArrayList<>();
        ArrayList<ArrayList<String>>  filtrado2=new ArrayList<>();
        String categoriaA;
        String enlace;
        if(noticia.getPais().equals("Internacional")){
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&categories="+noticia.getCategoria().toLowerCase();
            }
            
        }else{
            if(noticia.getCategoria().equals("cualquiera")){
               enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais().toLowerCase();
            }else{
                enlace="http://api.mediastack.com/v1/news?access_key=b2e945664161b56894b862b5dc8a09f5&keywords="+noticia.getTitulo()+"&countries="+noticia.getPais()+"&&categories="+noticia.getCategoria().toLowerCase();
            }     
        }
       System.out.println(enlace);
        try{
            
            URL url=new URL(enlace);
            URLConnection request= url.openConnection();
            request.addRequestProperty("User-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.0)");
            
            JsonParser jp=new JsonParser();
            JsonElement root=jp.parse(new InputStreamReader((InputStream)request.getContent()));
            if(root.isJsonObject()){
                
                    JsonObject rootobj=root.getAsJsonObject();
                    JsonArray data=rootobj.get("data").getAsJsonArray(); 
                    for(int i=0;i<data.size();i++){
                        
                        JsonObject obj=data.get(i).getAsJsonObject();
                        categoriaA=obj.get("category").toString();
                        buscar.add(categoriaA);
                        
                    }
                    
                    Collections.sort(buscar);
                    
                    for(int  i=0;i<buscar.size();i++){
                        if(filtrado.contains(buscar.get(i))){
                            filtrado.add(buscar.get(i));
                        }else{
                            filtrado=new ArrayList<>();
                            filtrado.add(buscar.get(i));
                            filtrado2.add(filtrado);
                        }
                    }
                    
                }
        }catch(Exception e){
            System.out.println("Problemas en GestorBD.buscarCategoria"+e.toString());
        }
        
        return filtrado2;
    } 
    
}
