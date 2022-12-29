/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Entidad.Fabricante;
import Entidad.Producto;
import Principal.Persistencia.FabricanteDAO;
import Principal.Persistencia.ProductoDAO;
import Servicio.ServicioFabricante;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mario
 */
public class Menu {

    Scanner teclado;
    private int var;
    private ServicioFabricante SFab;
    private ServicioProducto Sprod;
    ProductoDAO P_DAO = new ProductoDAO();
    FabricanteDAO F_DAO = new FabricanteDAO();

    public Menu() {
        
        // ISO_8859_1 PERMITE USAR LOS CARACTERES ESPECIALES
        teclado = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n");
        var = 0;
        SFab = new ServicioFabricante();
        Sprod = new ServicioProducto();
        
    }

    public void menuPrincipal() throws Exception {

        do {
            try {
                System.out.println("MENU PRINCIPAL");
                System.out.println("1 Ver nombre del producto");
                System.out.println("2 Ver producto por nombre y precio");
                System.out.println("3 Ver producto entre $120 y $200");
                System.out.println("4 Ver todos los Portatiles");
                System.out.println("5 Ver Producto mas Barato");
                System.out.println("6 Nuevo Producto");
                System.out.println("7 Nuevo Fabricante");
                System.out.println("8 Editar un producto");
                System.out.println("9 SALIR");

                var = teclado.nextInt();
                
                switch (var) {
                    case 1:

                       MostrarProducto();
                        break;
                    case 2:

                        MostrarProductoPrecio();
                        break;
                    case 3:
                        
                        MostrarProductoPrecio120_200();

                        break;
                    case 4:
                        
                        MostrarProductoPortatil();

                        break;
                    case 5:
                        
                        MostrarProductoBarato();

                        break;
                    case 6:
                        NuevoProducto();

                        break;
                    case 7:
                        NuevoFabricante();

                        break;
                    case 8:
                        
                        EditarProducto();

                        break;
                    case 9:
                        System.out.println("A DIOS VUELVAS PRONTOS");
                        break;
                    default:
                        System.out.println("            CUALQUIER COSA PUSISTE"
                                + "\n -------------DALE DE NUEVO BLD@---------------"
                                + "\n");

                }
            } catch (Exception e) {
                e.getMessage();
                teclado.next();  // SALTO DE LINEA PARA QUE NO SE EJECUTE EN BUCLE
                System.out.println("                 SIN LETRAS"
                                + "\n -------------DALE DE NUEVO BLD@---------------"
                                + "\n");
            }
        } 
        while (var != 9);
    }

   
   
    
    
// OPCION MENU

// 1    
    public void MostrarProducto(){
        
            try {
                List<Producto> productos = P_DAO.ObtenerProd();
                if (productos.isEmpty()) {
                    throw new Exception ("NO HAY PRODUCTOS");
                }
                System.out.println("--------LISTA DE PRODUCTOS--------");
                System.out.println("NOMBRE");
                for (Producto aux : productos) {
                    System.out.println("["+ aux.getNombre() +"]");
                    
                }
                System.out.println(""); 
            } catch (Exception e) {
            }
            
        }
 
 // 2   
    public void MostrarProductoPrecio(){
        
        try {
             List<Producto> productos = P_DAO.ObtenerProd();
             if (productos.isEmpty()) {
                throw new Exception("NO HAY PRODUCTOS CON PRECIO");
            }
             
        // %-35s%-35s\n = DISTANCIA DE SEPARACION ENTRE LOS 2 STRING     
             System.out.println("--------LISTA DE PRODUCTOS CON PRECIO--------");
             System.out.printf("%-35s%-35s\n", "NOMBRE ", "PRECIO");
             
             for (Producto aux : productos) {
                 System.out.printf("%-35s%-35s\n", "[" + aux.getNombre() + "]" , "$" + aux.getPrecio());
            }
             System.out.println("");
        } catch (Exception e) {
        }
    }
    
 // 3    
    public void MostrarProductoPrecio120_200(){
        
        try {
             List<Producto> productos = P_DAO.ObtenerProductosRangoPrecio();
             if (productos.isEmpty()) {
                throw new Exception("NO HAY PRODUCTOS CON PRECIO");
            }
             
        // %-35s%-35s\n = DISTANCIA DE SEPARACION ENTRE LOS 2 STRING     
             System.out.println("--------LISTA DE PRODUCTOS CON PRECIO ENTRE 120 Y 200--------");
             System.out.printf("%-15s%-35s%-15s%-25s\n", "CODIGO", "NOMBRE ", "PRECIO", "FABRICANTE");
             
             for (Producto aux : productos) {
                 System.out.printf("%-15s%-35s%-15s%-25s\n", "["+ aux.getCodigo() +"]",  "[" + aux.getNombre() + "]" , "$" + aux.getPrecio() + "" , "[" + aux.getCod_Fabricante().getNombre() + "]");
            }
             System.out.println("");
        } catch (Exception e) {
        }
    }
 
 // 4   
    public void MostrarProductoPortatil(){
        
        try {
             List<Producto> productos = P_DAO.ObtenerProductosPortatil();
             if (productos.isEmpty()) {
                throw new Exception("NO HAY PRODUCTOS CON PRECIO");
            }
             
        // %-35s%-35s\n = DISTANCIA DE SEPARACION ENTRE LOS 2 STRING     
             System.out.println("--------LISTA DE PRODUCTOS CON PRECIO--------");
             System.out.printf("%-15s%-35s%-15s%-25s\n", "CODIGO", "NOMBRE ", "PRECIO", "FABRICANTE");
             
             for (Producto aux : productos) {
                 System.out.printf("%-15s%-35s%-15s%-25s\n", "["+ aux.getCodigo() +"]",  "[" + aux.getNombre() + "]" , "$" + aux.getPrecio() + "" , "[" + aux.getCod_Fabricante().getNombre() + "]");
            }
             System.out.println("");
        } catch (Exception e) {
        }
    } 
 // 5   
    public void MostrarProductoBarato(){
        
        try {
             List<Producto> productos = P_DAO.ObtenerProductoBarato();
             if (productos.isEmpty()) {
                throw new Exception("NO HAY PRODUCTOS CON PRECIO");
            }
             
        // %-35s%-35s\n = DISTANCIA DE SEPARACION ENTRE LOS 2 STRING     
             System.out.println("--------LISTA DE PRODUCTOS CON PRECIO--------");
             System.out.printf("%-15s%-35s%-15s%-25s\n", "CODIGO", "NOMBRE ", "PRECIO", "FABRICANTE");
             
             for (Producto aux : productos) {
                 System.out.printf("%-15s%-35s%-15s%-25s\n", "["+ aux.getCodigo() +"]",  "[" + aux.getNombre() + "]" , "$" + aux.getPrecio() + "" , "[" + aux.getCod_Fabricante().getNombre() + "]");
            }
             System.out.println("");
        } catch (Exception e) {
        }
    } 
 // 6   
     public void NuevoFabricante() throws Exception {
        
        
        System.out.println("INGRESE EL NOMBRE DEL NUEVO FABRICANTE");
        try{
        String palabra = teclado.next().trim();
    
        
        Integer codigo = F_DAO.FabricCodMax();  // CODIGO MAX FABRICANTE
        codigo = codigo + 1;                    // SE SUMA CODIGO + 1 PARA QUE EL SIGIENTE "fabricante" TENGA EL CODIGO SIGIENTE
        SFab.CrearFabricante(codigo, palabra);

        }catch(Exception e){
            e.getMessage();
        }
    }
 // 7   
     public void NuevoProducto() throws Exception {
        
        
        
        try{
            Fabricante fab = null;
            
            Integer codigo = P_DAO.ProdCodMax();  // CODIGO MAX PRODUCTO
            codigo = codigo + 1;                  // SE SUMA CODIGO + 1 PARA QUE EL SIGIENTE "producto" TENGA EL CODIGO SIGIENTE
            
        System.out.println("INGRESE EL NOMBRE DEL NUEVO PRODUCTO");    
        String palabra = teclado.next().trim();
        
            System.out.println("INGRESE EL PRECIO");
            Double precio = teclado.nextDouble();
            System.out.println("INGRESE EL CODIGO");
            int cod = teclado.nextInt();
            fab = SFab.VerificaFabricante(cod);
            
            
            
            Sprod.CrearProducto(codigo, palabra, precio, fab);
            
            if (precio == null | precio < 0) {
                throw new Exception ("EL NUEVO PRECIO NO ES VALIDO");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
 // 8
    public void EditarProducto() throws Exception{
        
             
        try {
            
            System.out.println("LISTA DE PRODUCTOS");
            
            List<Producto> productos = P_DAO.ObtenerProd();
                if (productos.isEmpty()) {
                    throw new Exception ("NO HAY PRODUCTOS");
                }
            System.out.println("--------LISTA DE PRODUCTOS CON PRECIO ENTRE 120 Y 200--------");
            System.out.printf("%-15s%-35s%-15s%-25s\n", "CODIGO", "NOMBRE ", "PRECIO", "FABRICANTE");
             
             for (Producto aux : productos) {
                 System.out.printf("%-15s%-35s%-15s%-25s\n", "["+ aux.getCodigo() +"]",  "[" + aux.getNombre() + "]" , "$" + aux.getPrecio() + "" , "[" + aux.getCod_Fabricante().getNombre() + "]");
             }
           
            System.out.println("COLOQUE EL CODIGO DEL PRODUCTO A MODIFICAR");
            int cod = teclado.nextInt();
            System.out.println("NOMBRE");
            String nombre = teclado.next();
            System.out.println("PRECIO");
            Double precio = teclado.nextDouble();
            System.out.println("CODIGO DEL FABRICANTE");
            Integer Cod_Fabricante = teclado.nextInt();
            
            Sprod.EditarProducto(cod, nombre, precio, Cod_Fabricante);
          
           
        } catch (Exception e) {
            throw e;
        }
        
    }
}