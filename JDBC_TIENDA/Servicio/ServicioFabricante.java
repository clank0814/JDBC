/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Entidad.Fabricante;
import Principal.Persistencia.FabricanteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mario
 */
public class ServicioFabricante {
    
    private FabricanteDAO f_DAO;
    
    public ServicioFabricante(){
        f_DAO = new FabricanteDAO();
    }
    
    public void CrearFabricante(Integer cod, String nombre) throws Exception{
    
        try {
            // VALIDACION
         //   if(codigo == null | codigo < 0) {
          //      throw new Exception("COLOQUE UN CODIGO");       
          //  }
          
          Fabricante fabricante = new Fabricante();
            
          
            if (cod == null || cod < 0) {
                throw new Exception("ERROR DE CODIGO (SF)");
            }
            
          fabricante.setCodigo(cod);
            
          // TRIM = RECORTA SI HAY ESPACIOS VACIOS
            if(nombre == null | nombre.trim().isEmpty()){
                throw new Exception("COLOQUE UN NOMBRE");
                
            }
            
            
         // fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);
            f_DAO.GuardarFabricante(fabricante);
            
        } catch (Exception e) {
            throw new Exception("ERROR DE SISTEMA");
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
                
                Fabricante fabricante = f_DAO.BuscarCodFabricante(codigo);
                
                if (fabricante == null){
                    throw new Exception ("EL CODIGO INGRESADO NO ES VALIDO");
                }
                
                fabricante.setNombre(nombre);
                
                f_DAO.modificarFabricante(fabricante);
                
            } catch (Exception e) {
                throw new Exception ("ERROR DE SISTEMA");
            }
        }
        
        // ELIMINAR
        
        public void EliminarFabricante(Integer cod) throws Exception{
            
            try {
                if (cod == null || cod < 0){
                    throw new Exception("COLOQUE UN CODIGO");
                }
                
                // BUSCO POR EL CODIGO EN LA (BD) SI EXISTE UN FABRICANTE
                
                Fabricante fabricante = f_DAO.BuscarCodFabricante(cod);
               
                // SI RETORNA NULL 
                if (fabricante == null){
                    throw new Exception("EL CODIGO INGRESADO NO ES VALIDO");
                }
                
                System.out.println("SE ELIMINA EL FABRICANTE " + fabricante.getNombre());
                
                f_DAO.EliminarPorCod(cod);
                
            } catch (Exception e) {
                throw new Exception ("ERROR DE SISTEMA");
            }
        }
        
        
        public Fabricante VerificaFabricante(Integer cod){
            
            Fabricante f = null;
            
        try {
            f = f_DAO.BuscarCodFabricante(cod);
            
        } catch (Exception ex) {
            Logger.getLogger(ServicioFabricante.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("LA VERIFICACION DEL CODIGO (F) FALLO");
        }
        
        return f;
        
        }
    
    
}
