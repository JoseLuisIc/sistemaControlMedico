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
import Modelo.modeloUsuario;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;
import org.apache.logging.log4j.LogManager;

/**
 *
 * 
 */
public class controladorBD {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(controladorBD.class);;
   
    private static Connection Conexion; //Abro la conexión
    /*Inciamos el constructor*/
    controladorLibrerias lv = new controladorLibrerias();
     
     public Connection openConnection() {
        
        try {
            Properties propiedades = new Properties();
            propiedades.load(new FileReader("src/Controlador/propiedades.properties"));
            //Como obtener la información desde un archivo properties
            String db_nam = propiedades.getProperty("nombreBD");
            String use = propiedades.getProperty("user");
            String pas = propiedades.getProperty("password");
            String urlDB = propiedades.getProperty("urldb");
            //For MySql 5.5
            Class.forName("com.mysql.jdbc.Driver");
            //Conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + db_nam + "?characterEncoding=latin1&useConfigs=maxPerformance", use, pas);
            //For MySql 8.0
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Conexion = DriverManager.getConnection(urlDB + db_nam + "?characterEncoding=latin1&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", use, pas);
            System.out.println("Se ha iniciado la conexión con el servidor de forma exitosa");
        } catch (ClassNotFoundException | SQLException ex) {
           Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Conexion;
    }

/*  ----------------------------------------------------------------------------------
    Nombre: Clase closeConnection
    Función: Cierra La Conexión con la BD
    Parametros: 
    ----------------------------------------------------------------------------------
*/
    public void closeConnection() {
         logger.info("SistemaLogger.log", "Usuario: Actividad: Se cierra la conexión closeConnection()");
        try {
            Conexion.close();
            System.out.println("Se ha finalizado la conexión con el servidor");
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createDB(String name) { //ProMujer
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia createDB()");
        try {
            String Query = "CREATE DATABASE " + name;
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            openConnection();
            JOptionPane.showMessageDialog(null, "Se ha creado la base de datos " + name + " de forma exitosa");
        } catch (SQLException ex) {
            Logger.getLogger(controladorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createTableUser(String name) { // Name = Usuarios Provisional por que necesito tener la version completa 
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia createTableUser()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia createTable()");
       
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
         logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertDataUsuarioUM()");
       
        try {
            String Query = "INSERT INTO " + table_name + " VALUES("
                    + "\"" + id_unidadmedica + "\", "
                    + "\"" + um_paciente + "\", "
                    + "\"" + um_folio + "\", "
                    + "\"" + um_medico + "\", "
                    + "\"" + um_consultorio + "\")";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            System.out.println(Query);
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertDataUsuarioRecetas()");
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
            int pac_edad, String pac_sexo,String pac_curp,String pac_lugar, String pac_direccion, String pac_numcasa,String pac_codigopostal, String pac_colonia, String pac_fechanac,  int id_unidadmedica ) {
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertDataUsuarioPAC()");
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
                    + "\""+pac_codigopostal +"\", "
                    
                    + "\"" + pac_colonia + "\", "
                    + "\"" + pac_fechanac + "\", "
                    + "\"" + id_unidadmedica + "\")";
            Statement st = Conexion.createStatement();
            System.out.println(Query);
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertaCitas()");
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
            int pac_edad, String pac_sexo,String pac_curp,String pac_lugar, String pac_direccion, String pac_numcasa,String pac_codigopostal, String pac_colonia, String pac_fechanac,  int id_unidadmedica ) {
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia updateDataUsuarioPAC()");
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
                    + "pac_codigopostal = '" + pac_codigopostal + "', "
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia modifDataUsuarioPAC()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertDataUsuario()");
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
    logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia selectLlenaTabla()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertDatosUsuario()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia insertData()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia getValues()");
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
    Parametros: table_name -- Nombre de la tabla
                dni_user -- Nombre Usuario
                password -- Contraseña
    ----------------------------------------------------------------------------------
*/
    public boolean ConsultaUser(String table_name, String dni_user, String password) {
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia ConsultaUser()");
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
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia deleteRecord()");
        try {
            String Query = "DELETE FROM " + table_name + " WHERE ID = \"" + ID + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
        }
    }  

    
    public int validarInicioDeSesion(String user, String password) throws SQLException{
        int resultado = 0;
            
          String sql= "select * from tabla_usuarios where username=? and password=? ";
          logger.info("SistemaLogger.log", "Usuario: Actividad: Se Ejecuta la consulta: "+sql);
          
          PreparedStatement st = Conexion.prepareStatement(sql);
          st.setString(1, user);
          st.setString(2,password);
          logger.info("SistemaLogger.log", "Usuario: Actividad: Se Ejecuta la consulta: "+st.toString());
          ResultSet rs= st.executeQuery();
          
           while(rs.next()){
                resultado=1;
           }
           return resultado;
    }
    /*Retorna el modelo de usuarios para la tabla usuarios*/
    public DefaultTableModel modeloUsuarios(String columna[]){
        System.out.println(Arrays.toString(columna));
        DefaultTableModel modeloUsuarios = new DefaultTableModel(null, columna);
        try{
            String Sql = "SELECT * FROM bdconsultorio.tabla_usuarios;";
            System.out.println("Contenido: "+Sql);
  
            PreparedStatement us = Conexion.prepareStatement(Sql);
            ResultSet res = us.executeQuery();
            Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!
            
            while(res.next()){
                for (int i = 0; i<columna.length; i++){
                    objDatos[i] = res.getObject(i+1);
                }
                modeloUsuarios.addRow(objDatos);
            }
        }
        catch(SQLException ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
        return modeloUsuarios;
    }
    
    public int insertarDatosUsuario(int dni_user, String usuario, String password,String email,  
            int tipoRol, int edad, String nombres, String apellidos, String direccion) {
        logger.info("Usuario: Actividad: Se inicia insertarDatosUsuario()");
        try {
            String Query = "INSERT INTO bdconsultorio.tabla_usuarios VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = Conexion.prepareStatement(Query);
            statement.setInt(1,dni_user );
            statement.setString(2, usuario);
            statement.setString(3, password);
            statement.setString(4,email);
            statement.setInt(5, tipoRol);
            statement.setInt(6,edad);
            statement.setString(7,nombres);
            statement.setString(8,apellidos);
            statement.setString(9, direccion);
            statement.setString(10, lv.dameFechaActual(""));
            logger.info(statement.toString());
            statement.executeUpdate();
            
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public int editUsers(int dni_user, String usuario, String password,String email,  
            int tipoRol, int edad, String nombres, String apellidos, String direccion) {
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia editUsers()");
        try {
            String Query = "UPDATE tabla_usuarios"
                    + " SET username = ?, email = ?, password = ?, type_user = ?, age = ?,"
                    + " nombres = ?, apellidos = ?, direccion = ?"
                    + " WHERE id_usuario = ?;";
            PreparedStatement statement = Conexion.prepareStatement(Query);
            statement.setString(1, usuario);
            statement.setString(2,email);
            statement.setString(3, password);
            statement.setInt(4, tipoRol);
            statement.setInt(5, edad);
            statement.setString(6, nombres);
            statement.setString(7, apellidos);
            statement.setString(8, direccion);
            statement.setInt(9, dni_user);
            statement.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    /*
    El modelo permite obtener todos los datos del Usuario de acuerdo a su ID.
    @id_user
    */
    public modeloUsuario getDataUser(int id_user){
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia getDataUser()");
        modeloUsuario mUser = new modeloUsuario();
        try{
            String Sql = "SELECT * FROM tabla_usuarios where id_usuario = ?;";
            System.out.println("Contenido: "+Sql);
  
            PreparedStatement us = Conexion.prepareStatement(Sql);
            us.setInt(1, id_user);
            
            ResultSet res = us.executeQuery();
            while(res.next()){
                mUser.setId_user(res.getInt("id_usuario"));
                mUser.setUsuario(res.getString("username"));
                mUser.setPassword(res.getString("password"));
                mUser.setEmail(res.getString("email"));
                mUser.setTipoRol(res.getInt("type_user"));
                mUser.setEdad(res.getInt("age"));
                mUser.setFecha(res.getString("dateUpdate"));
                mUser.setApellidos(res.getString("apellidos"));
                mUser.setNombres(res.getString("nombres"));
                mUser.setDireccion(res.getString("direccion"));
            }
        }
        catch(SQLException ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
        return mUser;
    }
    
    /*
    Permite eliminar un usuario existente
    @id_user
    */
    public int deleteUser(int id_user){
        int resp = 0;
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia deleteRecord()");
        try {
            String Query = "DELETE FROM tabla_usuarios WHERE id_usuario = \"" + id_user + "\"";
            Statement st = Conexion.createStatement();
            st.executeUpdate(Query);
            resp = 1;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error borrando el registro especificado");
            resp = 0;
        }
        return resp;
    }
    
    /*
    Permite buscar un usuario existente
    @id_user
    */
    public DefaultTableModel modeloUsuarios(String columna[], String dato){
        logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia busqueda modeloUsuarios()");
        DefaultTableModel modeloUsuarios = new DefaultTableModel(null, columna);
        try{
            String Sql = "SELECT * FROM tabla_usuarios where username LIKE '%"+dato+"%' or email LIKE '%"+dato+"%';";
            System.out.println("Contenido: "+Sql);
  
            PreparedStatement us = Conexion.prepareStatement(Sql);
           
            ResultSet res = us.executeQuery();
            Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!
            
            while(res.next()){
                for (int i = 0; i<columna.length; i++){
                    objDatos[i] = res.getObject(i+1);
                }
                modeloUsuarios.addRow(objDatos);
            }
        }
        catch(SQLException ex){
            System.out.println("Exception: "+ ex.getMessage());
        }
        return modeloUsuarios;
    }
    
    /*
    * Valida la información del los datos del usuario para evitar mostrar la ventana vacía
    * Jose Luis Caamal Ic
    * jcaamalic@gmail.com
    */
        public int validarDatosPaciente(int idPaciente) throws SQLException{
        int resultado = 0;
            
          String sql = "SELECT * FROM bdconsultorio.tabla_pacientes, bdconsultorio.tabla_unidadmedica";
          sql += " WHERE id_paciente = ? and um_paciente = ?";
          PreparedStatement st = Conexion.prepareStatement(sql);
          st.setInt(1, idPaciente);
          st.setInt(2, idPaciente);
          logger.info("SistemaLogger.log", "Paciente: Actividad: Se Ejecuta la consulta: "+st.toString());
          ResultSet rs= st.executeQuery();
          
           while(rs.next()){
                resultado=1;
           }
           return resultado;
    }
    //Función que ingresa los datos de los medicos.   
    /* Jose Caamal Ic
        jcaamalic@gmail.com*/    
    public int insertarDatosMedicos(int idMedicos, String nombres, String apellidos,
            String cedula, String edad, String especialidades) {
        logger.info("Usuario: Actividad: Se inicia insertarDatosMedicos()");
        try {
            String Query = "INSERT INTO tabla_medicos VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = Conexion.prepareStatement(Query);
            statement.setInt(1,idMedicos );
            statement.setString(2, nombres);
            statement.setString(3, apellidos);
            statement.setString(4,cedula);
            statement.setInt(5,Integer.parseInt(edad));
            statement.setString(6, especialidades);
            logger.info(statement.toString());
            statement.executeUpdate();
            
            return 1;
        } catch (SQLException ex) {
            logger.info(ex.getMessage());
            return 0;
        }
    }
    
    public ArrayList<String> obtenerListaMedicos(){
        logger.info("Ejecutando obtenerListaMedicos");
        ArrayList<String> listaMedicos = new ArrayList<>();
        try{
            String Sql = "SELECT * FROM tabla_medicos";
            System.out.println("Contenido: "+Sql);
  
            PreparedStatement us = Conexion.prepareStatement(Sql);
            
            ResultSet res = us.executeQuery();
            while(res.next()){
                listaMedicos.add(res.getString("idMedicos")+" "+res.getString("nombres")+" "+res.getString("apellidos"));
            }
        }
        catch(SQLException ex){
            System.out.println("Exception: "+ ex.getMessage());
            logger.info(ex.getMessage());
        }
        return listaMedicos;
    }
    
    public DefaultTableModel modelTablaCitas(){
        
        try{
            String columna [] = new String[] {"ID_CITA","ID_PACIENTE","IDUMEDICA","FECHA_CITA","HORA_CITA","SERVICIO","ANALISIS","IDRECETAS"};
            DefaultTableModel modeloTablaCitas = new DefaultTableModel(null, columna);
            String Sql = "";
            
                Sql = "SELECT cm_idcita,cm_idpaciente,cm_idunidadmedica,cm_idfecha,cm_fechahora,cm_servicio,cm_analisispac,cm_idrecetas FROM tabla_citas";     
            
            System.out.println("Contenido: "+Sql);
            logger.info("SistemaLogger.log", "Usuario: Actividad: Se obtiene "+Sql);
 
            PreparedStatement us = openConnection().prepareStatement(Sql);
            ResultSet res = us.executeQuery();
            Object objDatos[] = new Object[columna.length]; //Siempre debe cconexoincidir con el numero de columnas!
            while(res.next()){

                for (int i = 0; i<columna.length; i++){
                    objDatos[i] = res.getObject(i+1);
                    
                }
                modeloTablaCitas.addRow(objDatos);
            }
            System.out.println(objDatos.toString());
            
            return modeloTablaCitas;
        }
        catch(SQLException ex){
            logger.info("SistemaLogger.log", "Usuario: Actividad: Ocurrio un Error "+ex.toString());
            return null;
        }
        
    }
}