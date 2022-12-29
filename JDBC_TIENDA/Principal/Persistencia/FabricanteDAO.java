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

/**
 *
 * @author Mario
 */
public class FabricanteDAO extends DAO {

    public void GuardarFabricante(Fabricante EntidadFab) throws Exception {

        try {
            if (EntidadFab == null) {
                throw new Exception("NO HAY FABRICANTE");
            }

            // SENTENCIA SQL PARA INSERTAR EN LA CONSULTA
            //   String sql = "INSERT INTO fabricante (codigo,nombre) VALUE ("+ EntidadFab.getCodigo() + " , '" + EntidadFab.getNombre() + "');";
            String sql = "INSERT INTO fabricante VALUE (" + EntidadFab.getCodigo() + " , '" + EntidadFab.getNombre() + "');";

            //LLAMADO AL METODO MODIFICAR
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL GUARDAR FABRICANTE");
        }
    }

    public void modificarFabricante(Fabricante EntidadFab) throws Exception {
        try {
            if (EntidadFab == null) {
                throw new Exception("NO HAY FABRICANTE");
            }

            // SENTENCIA SQL PARA MODIFICAR
            String sql = "UPADTE fabricante SET nombre = '" + EntidadFab.getNombre() + "' WHERE (codigo = " + EntidadFab.getCodigo() + ");";

            // LLAMADO AL METODO MODIFICAR (CON SQL)
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MODIFICAR FABRICANTE");
        }
    }

    public Fabricante BuscarCodFabricante(Integer CodFab) throws Exception {

        try {
            String sql = ("SELECT * FROM fabricante WHERE codigo = '" + CodFab + "';");

            // LLAMADO AL METODO BUSCAR heredado (CON sql COMO PARAMETRO)
            Mostrar(sql);

            Fabricante f = null;

            if (resultado.next()) {

                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                f.setCodigo(resultado.getInt(1));
                f.setNombre(resultado.getString(2));
            }
            return f;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception("ERROR AL BUSCAR FABRICANTE");

        } finally {
            desconectar();
        }

    }

    public void EliminarPorCod(int EntidadCod) throws Exception {

        try {
            String sql = "DELETE FROM fabricante WHERE codigo = '" + EntidadCod + "'; ";
            Modificar(sql);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL ELIMINAR FABRICANTE");
        }

    }

    public List<Fabricante> ObtenerFab() throws Exception {

        try {
            String sql = "SELECT * FROM fabricante;";
            Mostrar(sql);

            List<Fabricante> ListFab = new ArrayList<>();
            Fabricante NuevoFab = null;

            while (resultado.next()) {
                NuevoFab = new Fabricante();

                NuevoFab.setCodigo(resultado.getInt(1));
                NuevoFab.setNombre(resultado.getNString(2));

                ListFab.add(NuevoFab);
            }
            return ListFab;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("ERROR AL MOSTRAR");
        }
    }

    public Integer FabricCodMax() throws Exception {

        try {
            String sql = "SELECT codigo FROM fabricante ORDER BY codigo DESC LIMIT 1;";

            // LLAMADO AL METODO BUSCAR heredado (CON sql COMO PARAMETRO)
            Mostrar(sql);

            Fabricante f = null;

            while (resultado.next()) {
                f = new Fabricante();

                // EL getInt INICIA EN 1 POR QUE EL Resultset TAMBIEN
                f.setCodigo(resultado.getInt(1));   // getInt(1) = codigo

            }

            return f.getCodigo();
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new Exception("ERROR AL BUSCAR CODIGO MAX PRODUCTO");

        } finally {
            desconectar();
        }

    }

}
