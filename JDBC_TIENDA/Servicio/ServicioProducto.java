/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Entidad.Fabricante;
import Entidad.Producto;
import Principal.Persistencia.FabricanteDAO;
import Principal.Persistencia.ProductoDAO;
import java.util.List;

/**
 *
 * @author Mario
 */
public class ServicioProducto {
    
     private ProductoDAO P_DAO;
    
    public ServicioProducto(){
        P_DAO = new ProductoDAO();
    }
    
    public void CrearProducto(Integer cod, String nombre, Double precio, Fabricante f) throws Exception{
        
        
        try {
            
            Producto prod = new Producto();
            // VALIDACION
            
            if (cod == null || cod < 0) {
                throw new Exception("ERROR DE CODIGO (SP)");
            }
            prod.setCodigo(cod);
        
          // TRIM = RECORTA SI HAY ESPACIOS VACIOS
            if(nombre == null | nombre.trim().isEmpty()){
                throw new Exception("COLOQUE UN NOMBRE");
                
            }
            
         //   Producto prod = new Producto();
            
          //  prod.setCodigo(13);
            prod.setNombre(nombre);
        //    prod.setPrecio(precio);
         //   prod.setCod_Fabricante(f);
            
            
            if (precio == null | precio < 0){
            throw new Exception ("EL PRECIO NO ES VALIDO");
            }
            
            prod.setPrecio(precio);
            
            if (f == null) {
            throw new Exception ("EL CODIGO DEL FABRICANTE NO ES VALIDO");
            }
            
            prod.setCod_Fabricante(f);
            
            P_DAO.GuardarProducto(prod);
            
        } catch (Exception e) {
            throw e;  // ENVIA LAS EXCEPCIONES ANTERIORES
            
          //  throw new Exception("ERROR DE SISTEMA");
        }
        
    }
        
        // MODIFICAR
        
        public void ModificarFab(Integer codigo, String nombre) throws Exception{
            
            try {
                // VALIDACION 
                if (codigo == null | codigo < 0){
                    throw new Exception ("COLOQUE UN CODIGO");
                }
                if (nombre == null | nombre.trim().isEmpty()){
                    throw new Exception ("COLOQUE UN NOMBRE");
                }
                
                Producto fabricante = P_DAO.BuscarCodProd(codigo);
                
                if (fabricante == null){
                    throw new Exception ("EL CODIGO INGRESADO NO ES VALIDO");
                }
                
                fabricante.setNombre(nombre);
                
                P_DAO.ModificarProd(fabricante);
                
            } catch (Exception e) {
                throw new Exception ("ERROR DE SISTEMA");
            }
        }
        
        // ELIMINAR
        
        public void EliminarProducto(Integer cod) throws Exception{
            
            try {
                if (cod == null || cod < 0){
                    throw new Exception("COLOQUE UN CODIGO");
                }
                
                // BUSCO POR EL CODIGO EN LA (BD) SI EXISTE UN FABRICANTE
                
                Producto fabricante = P_DAO.BuscarCodProd(cod);
               
                // SI RETORNA NULL 
                if (fabricante == null){
                    throw new Exception("EL CODIGO INGRESADO NO ES VALIDO");
                }
                
                System.out.println("SE ELIMINA EL FABRICANTE " + fabricante.getNombre());
                
                P_DAO.EliminarPorCod(cod);
                
            } catch (Exception e) {
                throw new Exception ("ERROR DE SISTEMA");
            }
        }
        
        public void MostrarProducto(){
            
            try {
                List<Producto> productos = P_DAO.ObtenerProd();
                if (productos.isEmpty()) {
                    throw new Exception ("NO HAY PRODUCTOS");
                }
                System.out.println("--------LISTA DE PRODUCTOS--------");
                System.out.println("NOMBRE");
                for (Producto aux : productos) {
                    System.out.println(aux.getNombre());
                    
                }
            } catch (Exception e) {
            }
            
        }
        
        public void EditarProducto(Integer codigo, String nombre, Double precio, Integer cod_Fabricante) throws Exception{
            
            
            try {
                // VALIDACION
                if(codigo == null || codigo < 0){
                    throw new Exception ("DEBE INGRESAR UN CODIGO");
                    
                    
                }
                
                if(nombre == null || nombre.trim().isEmpty()){
                  throw new Exception ("DEBE INGRESAR UN NOMBRE");
                }
                
                if (precio == null) {
                    throw new Exception ("DEBE INGRESAR EL RECIO");
                }
                
                if (cod_Fabricante == null) {
                    throw new Exception ("DEBE INGRESAR EL CODIGO DEL FABRICANTE");
                }
                
                Producto P = P_DAO.BuscarCodProd(cod_Fabricante);
                
                
                
                if (P == null){
                    throw new Exception ("EL CODIGO NO ESTA ASOCIADO A NINGUN PRODUCTO");
                }
                
                P.setCodigo(codigo);
                P.setNombre(nombre);
                P.setPrecio(precio);
                
                
                Fabricante f = null;
                ServicioFabricante Sf = new ServicioFabricante();
                f = Sf.VerificaFabricante(cod_Fabricante);
                P.setCod_Fabricante(f);
                
                P_DAO.ModificarProd(P);
                
              
                
                
            } catch (Exception e) {
                //throw e;
                System.out.println(e.getMessage());
            }
            
            
            
        }
    
}
