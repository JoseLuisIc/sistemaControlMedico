/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author joseluis.caamal
 */
public class ControlLoogs {
    
    public static void escribirLog(String rutaArchivo, String mensaje) {

        Logger logger = Logger.getLogger("MyLog");
        FileHandler fh;

        try {

            fh = new FileHandler(rutaArchivo,true); /*Se crea un único archivo para todo JLCI 12/03/2021*/
            logger.addHandler(fh);

            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.info(mensaje);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void main(String... args){
//        for(int i=1;i<6;i++){
//            escribirLog("SistemaLogger.log", "MensajePrueba"+i);
//        }
//    }
}
