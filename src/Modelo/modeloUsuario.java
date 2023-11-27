/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import lombok.Data;
/**
 *
 * @author joseluis.caamal
 */
@Data
public class modeloUsuario {
   int id_user;
   String usuario; 
   String password;
   String email;
   int tipoRol; 
   int edad;
   String nombres; 
   String apellidos; 
   String direccion; 
   String fecha;
}
