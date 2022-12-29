/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Principal.Persistencia;

import Entidad.Fabricante;
import Entidad.Producto;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sun.security.pkcs11.P11TlsKeyMaterialGenerator;

/**
 *
 * @author Mario
 */
public class ProductoDAO extends DAO {

    public void GuardarProducto(Producto EntidadProd) throws Exception {

        try {
            if (EntidadProd == null) {
                throw new Exception("NO HAY PRODUCTO");
            }

            // SENTENCIA SQL PARA INSERTAR EN LA CONSULTA (EL CODIGO ES AUTO INCREMENTAL, NO SE COLOCA)
            //  INSERT INTO producto (`nombre`, `precio`, `codigo_fabricante`) VALUES ('parlante', '5000', '3');
            
            String sql = "INSERT INTO producto VALUE ('" + EntidadProd.getCodigo() + "', '" + EntidadProd.getNombre()
                    + "', '" + EntidadProd.getPrecio() + "', '" + EntidadProd.getCod_Fabricante().getCodigo() + "');";  // LLAVE FORANEA LLAMANDO AL CODIGO FABRICANTE

            //LLAMADO AL METODO MODIFICAR
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL GUARDAR PRODUCTO");
        }
    }

    public void ModificarProd(Producto EntidadFab) throws Exception {
        try {
            if (EntidadFab == null) {
                throw new Exception("NO HAY PRODUCTO");
            }

            /*
            SENTENCIA SQL DE MODIFICACION
            UPDATE producto SET codigo = ' ',
            nombre = ' ',
            precio = ' ',
            codigo_fabricante = ' '
            WHERE (codigo = ' ');
             */
            // UPDATE producto SET `nombre` = 'nuevo', `precio` = '2000', `codigo_fabricante` = '3' WHERE (`codigo` = '16');
            String sql = "UPDATE producto SET codigo = " + EntidadFab.getCodigo() + ", nombre = '" + EntidadFab.getNombre() + "', precio = " + EntidadFab.getPrecio() + ", codigo_fabricante = " + EntidadFab.getCod_Fabricante().getCodigo() + " WHERE (codigo = " + EntidadFab.getCodigo() + ");";

            // LLAMADO AL METODO MODIFICAR (CON LA LINEA sql)
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MODIFICAR PRODUCTO");
        }
    }

    public Producto BuscarCodProd(Integer CodFab) throws Exception {

        try {

            //SENTENCIA SQL DE CONSULTA
            //"SELECT * FROM producto p JOIN fabricante P ON P.codigo_fabricante WHERE (p.codigo = " + CodFab + ");";
            String sql = "SELECT * FROM producto INNER JOIN fabricante f ON codigo_fabricante = f.codigo WHERE (f.codigo = " + CodFab + ");";

            // LLAMADO AL METODO BUSCAR heredado (CON sql COMO PARAMETRO)
            Mostrar(sql);

            Producto P = null;
            Fabricante f = null;

            if (resultado.next()) {

                P = new Producto();
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));
                P.setNombre(resultado.getString(2));
                P.setPrecio(resultado.getDouble(3));
                // LLENO EL OBJETO CON LOS DATOS DEL RESULTADO (resultset)
                f.setCodigo(resultado.getInt(5));
                f.setNombre(resultado.getString(6));

                // LUEGO LE ASIGNO EL FABRICANTE AL PRODUCTO
                P.setCod_Fabricante(f);

            }
            return P;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception("ERROR AL BUSCAR PRODUCTO");

        } finally {
            desconectar();
        }

    }

    public void EliminarPorCod(int EntidadCod) throws Exception {

        try {
            String sql = "DELETE FROM producto WHERE codigo = '" + EntidadCod + "'; ";
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL ELIMINAR PRODUCTO");
        }

    }

    public List<Producto> ObtenerProd() throws Exception {

        try {
            String sql = "SELECT * FROM producto P JOIN fabricante f ON f.codigo = P.codigo_fabricante;";
            Mostrar(sql);

            List<Producto> ListProd = new ArrayList<>();
            Producto P = null;
            Fabricante f = null;

            while (resultado.next()) {
                P = new Producto();
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));
                P.setNombre(resultado.getString(2));
                P.setPrecio(resultado.getDouble(3));
                // LLENO EL OBJETO CON LOS DATOS DEL RESULTADO (resultset)
                f.setCodigo(resultado.getInt(5));
                f.setNombre(resultado.getString(6));

                // LUEGO LE ASIGNO EL FABRICANTE AL PRODUCTO
                P.setCod_Fabricante(f);

                ListProd.add(P);
            }
            return ListProd;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MOSTRAR PRODUCTOS");
        }
    }

    public List<Producto> ObtenerProductosRangoPrecio() throws Exception {

        try {
            String sql = "SELECT * FROM producto P JOIN fabricante f ON f.codigo = P.codigo_fabricante WHERE P.precio BETWEEN 120 AND 200";
            Mostrar(sql);

            List<Producto> ListProd = new ArrayList<>();
            Producto P = null;
            Fabricante f = null;

            while (resultado.next()) {
                P = new Producto();
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));
                P.setNombre(resultado.getString(2));
                P.setPrecio(resultado.getDouble(3));
                // LLENO EL OBJETO CON LOS DATOS DEL RESULTADO (resultset)
                f.setCodigo(resultado.getInt(5));
                f.setNombre(resultado.getString(6));

                // LUEGO LE ASIGNO EL FABRICANTE AL PRODUCTO
                P.setCod_Fabricante(f);

                ListProd.add(P);
            }
            return ListProd;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MOSTRAR PRODUCTOS");
        }
    }

    public List<Producto> ObtenerProductosPortatil() throws Exception {

        try {
            // Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nombre
            String sql = "SELECT * FROM producto P JOIN fabricante f ON f.codigo = P.codigo_fabricante WHERE P.nombre LIKE '%portatil%'";
            Mostrar(sql);

            List<Producto> ListProd = new ArrayList<>();
            Producto P = null;
            Fabricante f = null;

            while (resultado.next()) {
                P = new Producto();
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));
                P.setNombre(resultado.getString(2));
                P.setPrecio(resultado.getDouble(3));
                // LLENO EL OBJETO CON LOS DATOS DEL RESULTADO (resultset)
                f.setCodigo(resultado.getInt(5));
                f.setNombre(resultado.getString(6));

                // LUEGO LE ASIGNO EL FABRICANTE AL PRODUCTO
                P.setCod_Fabricante(f);

                ListProd.add(P);
            }
            return ListProd;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MOSTRAR PRODUCTOS");
        }
    }

    public List<Producto> ObtenerProductoBarato() throws Exception {

        try {
            // Devuelve el producto con el precio mas bajo 
            String sql = "SELECT * FROM producto P JOIN fabricante f ON f.codigo = P.codigo_fabricante ORDER BY precio ASC LIMIT 1";
            Mostrar(sql);

            List<Producto> ListProd = new ArrayList<>();
            Producto P = null;
            Fabricante f = null;

            while (resultado.next()) {
                P = new Producto();
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));
                P.setNombre(resultado.getString(2));
                P.setPrecio(resultado.getDouble(3));
                // LLENO EL OBJETO CON LOS DATOS DEL RESULTADO (resultset)
                f.setCodigo(resultado.getInt(5));
                f.setNombre(resultado.getString(6));

                // LUEGO LE ASIGNO EL FABRICANTE AL PRODUCTO
                P.setCod_Fabricante(f);

                ListProd.add(P);
            }
            return ListProd;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MOSTRAR PRODUCTOS");
        }
    }

    public Integer ProdCodMax() throws Exception {

        try {
            String sql = "SELECT codigo FROM producto ORDER BY codigo DESC LIMIT 1;";

            // LLAMADO AL METODO BUSCAR heredado (CON sql COMO PARAMETRO)
            Mostrar(sql);

            Producto P = null;

            while (resultado.next()) {

                P = new Producto();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                P.setCodigo(resultado.getInt(1));  // getInt(1) = codigo

            }

            return P.getCodigo();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception("ERROR AL BUSCAR CODIGO MAX FABRICANTE");

        } finally {
            desconectar();
        }

    }

}
