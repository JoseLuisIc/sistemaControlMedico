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
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
//import javax.swing.JTextField;
/**
 *
 * @author Jose Luis Caamal Ic
 */
public class controladorLibrerias {

/*  ----------------------------------------------------------------------------------
    Nombre: Clase validarFecha
    Función: valida fechas
    Aut@r: José Luis Caamal Ic
    Parametros: fecha -- Fecha Entrada
    ----------------------------------------------------------------------------------
*/
public boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
/*  ----------------------------------------------------------------------------------
    Nombre: Clase convertirDatos
    Función: Cobvierte fechas
    Aut@r: José Luis Caamal Ic
    Parametros: dateEntrada -- Fecha Entrada
                dateSalida -- Fecha Salida
    ----------------------------------------------------------------------------------
*/
public Date convertirDatos( String dateEntrada, Date dateSalida) {
                
                try{ 
                //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                //dateSalida = SimpleDateFormat.parse(dateEntrada);
                dateSalida = formatter.parse(dateEntrada);
                //String dateSalida2 = formatter.parse(dateSalida);
                }
                catch(ParseException e){
                    e.printStackTrace();
                    System.out.println("Error al convertir la fecha");
                }

        return dateSalida;
    }
    
/*  ----------------------------------------------------------------------------------
    Nombre: Clase dameFechaActual
    Función: Guarda los valores en la tabla correspondiente.
    Aut@r: José Luis Caamal Ic
    Parametros: darFecha -- Fecha dada en el registro
    ----------------------------------------------------------------------------------
*/
public String dameFechaActual( String darFecha){
    
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf  =new java.text.SimpleDateFormat("yyyy-MM-dd");
        darFecha = sdf.format(date);
        
        return darFecha;
}

/*  ----------------------------------------------------------------------------------
    Nombre: Clase validaMes
    Función: valida el rango de Mes
    Aut@r: José Luis Caamal Ic
    Parametros: mes -- Fecha mes
    ----------------------------------------------------------------------------------
*/
public boolean validaMes(String mes){
    int mesValida = 0;
    boolean mesVal = true;
    
    mesValida = Integer.parseInt(mes);
    
    mesVal = mesValida <13;
    
    return mesVal;
}
/*  ----------------------------------------------------------------------------------
    Nombre: Clase validaDia
    Función: valida el rango de Dia
    Aut@r: José Luis Caamal Ic
    Parametros: dia -- Fecha dia
    ----------------------------------------------------------------------------------
*/
public boolean validaDia(String dia){
    int diaValida = 0;
    boolean diaVal = true;
    
    diaValida = Integer.parseInt(dia);
    
    diaVal = diaValida <32;
    
    return diaVal;
}
/*  ----------------------------------------------------------------------------------
    Nombre: Clase validaAno
    Función: valida el rango de Ano
    Aut@r: José Luis Caamal Ic
    Parametros: año -- Fecha año
    ----------------------------------------------------------------------------------
*/
public boolean validaAno(String ano){
    
    int anoValida = 0;
    boolean anoVal = true;
    
    anoValida = Integer.parseInt(ano);
    
    anoVal = anoValida <2019;
    
    return anoVal;
    
}
   private static final String  ENCRYPT_KEY="KaabCode2022"; //Clave Encriptación No Compartir
   public  String encrypt(String value) {
    try {
        String cadenaCodificada = Base64.encodeBase64String(value.getBytes());
        System.out.println("codificado: " + cadenaCodificada);
        return cadenaCodificada;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

public  String decrypt( String value) {
    try {
        byte[] bytesDecodificados = Base64.decodeBase64(value);
        String cadenaDecodificada = new String(bytesDecodificados);
        System.out.println("cadenaDecodificada: " + cadenaDecodificada);
        return cadenaDecodificada;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}

   

}
