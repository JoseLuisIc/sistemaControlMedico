/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author joseluis.caamal
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author José Luis Caamal Ic
 */
public class controladorBD {

    private static Connection Conexion; //Abro la conexión
/*Inciamos el constructor*/

     controladorLibrerias lv = new controladorLibrerias();
/*  ----------------------------------------------------------------------------------
    Nombre: Clase conex()
    Función: Apertura La Conexión con la BD/ Utilizado para la consulta de tablas
    Aut@r: José Luis Caamal Ic
    Parametros: 
    ----------------------------------------------------------------------------------
*/
    public Connection conex() {
        
        try {
            String db_nam = "bdconsultorio";
            String use = "root";
            String pas ="sakai";
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_nam, use, pas);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
           // Logger.getLogger(LibreriaHerramientas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conexion;
    }
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase MySQLConnection
    Función: Apertura La Conexión con la BD
    Aut@r: José Luis Caamal Ic
    Parametros: 
    ----------------------------------------------------------------------------------
*/
    public void MySQLConnection(String user, String pass, String db_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_name, user, pass);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*  ----------------------------------------------------------------------------------
    Nombre: Clase closeConnection
    Función: Cierra La Conexión con la BD
    Aut@r: José Luis Caamal Ic
    Parametros: 
    ----------------------------------------------------------------------------------
*/
    public void closeConnection() {
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDB(String name) { //ProMujer
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            MySQLConnection("root", "", name);
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTableUser(String name) { // Name = Usuarios Provisional por que necesito tener la version completa 
        try {
            String Query = "CREATE TABLE " + name + ""
                    + "(dni_user VARCHAR(25), usuario VARCHAR(50), password VARCHAR(50))";
            JOptionPane.showMessageDialog(null, "Se ha creado la base de tabla " + name + " de forma exitosa");
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_unidadmedica
                Columns:
                id_unidadmedica int(11) PK 
                um_paciente int(11) 
                um_folio varchar(45) 
                um_medico varchar(45) 
                um_consultorio varchar(45)
    ----------------------------------------------------------------------------------
*/
    public int insertDataUsuarioUM(String table_name, int id_unidadmedica, int um_paciente, String um_folio,String um_medico, String um_consultorio) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + id_unidadmedica + "\", "
                    + "\"" + um_paciente + "\", "
                    + "\"" + um_folio + "\", "
                    + "\"" + um_medico + "\", "
                    + "\"" + um_consultorio + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: tabla_recetas
                Columns:
                rec_idreceta int(11) PK 
                rec_idpaciente int(11) 
                rec_idunidadmedica int(11) 
                rec_descripcion varchar(500) 
                rec_alergias varchar(45) 
                rec_estatura varchar(45) 
                rec_peso varchar(45) 
                rec_presion varchar(45) 
                rec_tiposangre varchar(45)
    ----------------------------------------------------------------------------------
*/
    public int insertDataUsuarioRecetas(String table_name, int rec_idreceta, int rec_idpaciente, int rec_idunidadmedica,String rec_descripcion, String rec_alergias, String rec_estatura,
            String rec_peso, String rec_presion, String rec_tiposangre, int rec_idcita ) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + rec_idreceta + "\", "
                    + "\"" + rec_idpaciente + "\", "
                    + "\"" + rec_idunidadmedica + "\", "
                    + "\"" + rec_descripcion + "\", "
                    + "\"" + rec_alergias + "\", "
                    + "\"" + rec_estatura + "\", "
                    + "\"" + rec_peso + "\", "
                    + "\"" + rec_presion + "\", "
                    + "\"" + rec_tiposangre + "\", "
                    + "\"" + rec_idcita + "\")";
            
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
    
    /*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pacientes
                Columns:
                id_paciente int(11) PK 
                pac_nombres varchar(45) 
                pac_apellidopaterno varchar(45) 
                pac_apellidomaterno varchar(45) 
                pac_edad int(11) 
                pac_sexo varchar(45) 
                pac_curp varchar(45) 
                pac_lugar varchar(45) 
                pac_direccion varchar(45) 
                pac_numcasa varchar(45) 
                pac_colonia varchar(45) 
                pac_fechanac date 
                pac_idunidadmedica int(11)
    ----------------------------------------------------------------------------------
*/
    public int insertDataUsuarioPAC(String table_name,int um_paciente, String pac_nombres, String pac_apellidopaterno, String pac_apellidomaterno, 
            int pac_edad, String pac_sexo,String pac_curp,String pac_lugar, String pac_direccion, String pac_numcasa, String pac_colonia, String pac_fechanac,  int id_unidadmedica ) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + um_paciente + "\", "
                    + "\"" + pac_nombres + "\", "
                    + "\"" + pac_apellidopaterno + "\", "
                    + "\"" + pac_apellidomaterno + "\", "
                    + "\"" + pac_edad + "\", "
                    + "\"" + pac_sexo + "\", "
                    + "\"" + pac_curp + "\", "
                    + "\"" + pac_lugar + "\", "
                    + "\"" + pac_direccion + "\", "
                    + "\"" + pac_numcasa + "\", "
                    + "\"" + pac_colonia + "\", "
                    + "\"" + pac_fechanac + "\", "
                    + "\"" + id_unidadmedica + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            //st.execute
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
    /*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pacientes
                Columns:
                id_paciente int(11) PK 
                pac_nombres varchar(45) 
                pac_apellidopaterno varchar(45) 
                pac_apellidomaterno varchar(45) 
                pac_edad int(11) 
                pac_sexo varchar(45) 
                pac_curp varchar(45) 
                pac_lugar varchar(45) 
                pac_direccion varchar(45) 
                pac_numcasa varchar(45) 
                pac_colonia varchar(45) 
                pac_fechanac date 
                pac_idunidadmedica int(11)
    ----------------------------------------------------------------------------------
*/
    public int insertaCitas(String table_name, int generaIdCita, 
            int ncita_idpaciente,
            String ncita_fecha,
            String ncita_hora,
            String ncita_servicio,
            String ncita_analisis, 
            String ncita_idunidadmedica, int id_recetas){
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + generaIdCita + "\", "
                    + "\"" + ncita_idpaciente + "\", "
                    + "\"" + ncita_fecha + "\", "
                    + "\"" + ncita_hora + "\", "
                    + "\"" + ncita_servicio + "\", "
                    + "\"" + ncita_analisis + "\", "
                    + "\"" + ncita_idunidadmedica + "\", "
                    + "\"" + id_recetas + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            //st.execute
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
    
        /*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pacientes
                Columns:
                id_paciente int(11) PK 
                pac_nombres varchar(45) 
                pac_apellidopaterno varchar(45) 
                pac_apellidomaterno varchar(45) 
                pac_edad int(11) 
                pac_sexo varchar(45) 
                pac_curp varchar(45) 
                pac_lugar varchar(45) 
                pac_direccion varchar(45) 
                pac_numcasa varchar(45) 
                pac_colonia varchar(45) 
                pac_fechanac date 
                pac_idunidadmedica int(11)
    ----------------------------------------------------------------------------------
*/
    public int updateDataUsuarioPAC(String table_name,int um_paciente, String pac_nombres, String pac_apellidopaterno, String pac_apellidomaterno, 
            int pac_edad, String pac_sexo,String pac_curp,String pac_lugar, String pac_direccion, String pac_numcasa, String pac_colonia, String pac_fechanac,  int id_unidadmedica ) {
        try {
            //int response;
            String sql = ("UPDATE  " + table_name  + " "
                    + "SET pac_nombres =  '" + pac_nombres + "', "
                    + "pac_apellidopaterno = '" + pac_apellidopaterno + "', "
                    + "pac_apellidomaterno = '" + pac_apellidomaterno + "', "
                    + "pac_edad = '" + pac_edad + "', "
                    + "pac_sexo = '" + pac_sexo + "', "
                    + "pac_curp = '" + pac_curp + "', "
                    + "pac_lugar = '" + pac_lugar + "', "
                    + "pac_direccion = '" + pac_direccion + "', "
                    + "pac_numcasa = '" + pac_numcasa + "', "
                    + "pac_colonia = '" + pac_colonia + "', "
                    + "pac_fechanac = '" + pac_fechanac + "' " 
                + "WHERE id_paciente = '"+um_paciente+"' and pac_idunidadmedica = '"+id_unidadmedica+"'");
            System.out.println("ConsultaPreparada"+sql);
            Statement st = Conexion.createStatement();
            st.executeUpdate(sql);	
            //System.out.println(" Response: "+ response);
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
     /*  ----------------------------------------------------------------------------------
    Nombre: Clase updateDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: Table: tabla_pacientes
                Columns:
                c
    ----------------------------------------------------------------------------------
*/
    public int modifDataUsuarioPAC(String table_name, int id_unidadmedica, int um_paciente, String um_folio,String um_medico, String um_consultorio) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + id_unidadmedica + "\", "
                    + "\"" + um_paciente + "\", "
                    + "\"" + um_folio + "\", "
                    + "\"" + um_medico + "\", "
                    + "\"" + um_consultorio + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
            return 0;
        }
    }
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDataUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: table_name -- Nombre de la tabla
                dni_user -- Nombre Usuario
                usuario --- Usuario que recibe el nombre
                password -- Contraseña
    ----------------------------------------------------------------------------------
*/
    public void insertDataUsuario(String table_name, String dni_user, String usuario, String password) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + dni_user + "\", "
                    + "\"" + usuario + "\", "
                    + "\"" + password + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDatosUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: table_name -- Nombre de la tabla
                dni_user -- Nombre Usuario
                usuario --- Usuario que recibe el nombre
                password -- Contraseña
    ----------------------------------------------------------------------------------
*/ 
public Object[] selectLlenaTabla(){
     Object llenaTabla[] = null;
    
    try {
            String Query = "SELECT * FROM bdconsultorio.tabla_pacientes;";
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);
            ResultSetMetaData metaDatos = resultSet.getMetaData();
            while (resultSet.next()) {
                                    // Se obtiene el número de columnas.
                    int numeroColumnas = metaDatos.getColumnCount();

                    // Se crea un array de etiquetas para rellenar
                    llenaTabla = new Object[numeroColumnas];
                    
                    // Se obtiene cada una de las etiquetas para cada columna
                    for (int i = 0; i < numeroColumnas; i++)
                    {
                       // Nuevamente, para ResultSetMetaData la primera columna es la 1.
                       llenaTabla[i] = metaDatos.getColumnLabel(i + 1);
                    }
            }
            
            //JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
            return llenaTabla;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            //JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
        return llenaTabla;

}

 /*  ----------------------------------------------------------------------------------
    Nombre: Clase insertDatosUsuario
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: table_name -- Nombre de la tabla
                dni_user -- Nombre Usuario
                usuario --- Usuario que recibe el nombre
                password -- Contraseña
    ----------------------------------------------------------------------------------
*/    
    public void insertDatosUsuario(String table_name, String dni_user, String nombres, String apellidos, String fechaNac,
            String email,String telefono, String celular, int numHijos, String estadoCivil, String ocupacion, String otraOcupacion,
            Double precio, String tieneCasa, String rentaCasa, String tieneCoche, String recomendadoPor, String grupo, 
            String usuario, String password, String fechaRegistro) {
        Date fechaNacAux = null;
        //Convierto String a Date  JLCI 08/07/2018
        //lv = new LibreriasValidacion();
        fechaNacAux = lv.convertirDatos(fechaNac, fechaNacAux); //Guardo Fecha Nacimiento JLCI 08/07/2018
        fechaRegistro = lv.dameFechaActual(fechaRegistro); //Guardo Fecha Actual JLCI 08/07/2018
        
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + dni_user + "\", " //1
                    + "\"" + nombres + "\", " //2
                    + "\"" + apellidos + "\", " //3
                    + "\"" + fechaNacAux + "\", " //4
                    + "\"" + email + "\", "  //5
                    + "\"" + telefono + "\", "  //6
                    + "\"" + celular + "\", "  //7
                    + "\"" + numHijos + "\", "  //8
                    + "\"" + estadoCivil + "\", "  //9
                    + "\"" + ocupacion + "\", "  //10
                    + "\"" + otraOcupacion + "\", "  //11
                    + "\"" + precio + "\", "  //12
                    + "\"" + tieneCasa + "\", "  //13
                    + "\"" + rentaCasa + "\", "  //14
                    + "\"" + tieneCoche + "\", "  //15
                    + "\"" + recomendadoPor + "\", "  //16
                    + "\"" + grupo + "\", "  //17
                    + "\"" + usuario + "\", "  //18
                    + "\"" + password + "\"," //19
                    + "\"" + fechaRegistro +"\" )";// 20
                    
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
 /*Ejemplo:*/   
    public void insertData(String table_name, String ID, String name, String lastname, String age, String gender) {
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + ID + "\", "
                    + "\"" + name + "\", "
                    + "\"" + lastname + "\", "
                    + "\"" + age + "\", "
                    + "\"" + gender + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos almacenados de forma exitosa");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error en el almacenamiento de datos");
        }
    }
    
    

    public void getValues(String table_name) {
        try {
            String Query = "SELECT * FROM " + table_name;
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
    }
 
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase Consulta ValidaUsuarios
    Función: Valida los usuarios en la bd, de acuerdo al inicio de sesión
    Aut@r: José Luis Caamal Ic
    Parametros: table_name -- Nombre de la tabla
                dni_user -- Nombre Usuario
                password -- Contraseña
    ----------------------------------------------------------------------------------
*/
    public boolean ConsultaUser(String table_name, String dni_user, String password) {
        boolean userfind = false;
        String Usuario1 = "";
        String Pass1 = "";
        try {
            String Query = "SELECT * FROM " + table_name + " WHERE usuario = '"+ dni_user +"'";
            Statement st = Conexion.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(Query);

            while (resultSet.next()) {
                /*System.out.println("ID: " + resultSet.getString("ID") + " "
                        + "Nombre: " + resultSet.getString("Nombre") + " " + resultSet.getString("Apellido") + " "
                        + "Edad: " + resultSet.getString("Edad") + " "
                        + "Sexo: " + resultSet.getString("Sexo"));*/
                System.out.println("dni_user" +  resultSet.getString("dni_user") + " " 
                            + "Usuario: " +  resultSet.getString("usuario")+ " "
                            + "password: " + resultSet.getString("password"));
                
                Usuario1 = resultSet.getString("usuario"); //Valido los datos
                Pass1 = resultSet.getString("password"); //Valido la contraseña
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la adquisición de datos");
        }
        
        if(Usuario1.equals(dni_user) && Pass1.equals(password))
           userfind = true;
        
        return userfind;
    }

    public void deleteRecord(String table_name, String ID) {
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }  
}
