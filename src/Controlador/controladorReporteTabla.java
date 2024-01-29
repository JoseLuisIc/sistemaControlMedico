/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Vista.loginInMedico;
import Vista.vistaPrincipal;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author joseluis.caamal
 */
public class controladorReporteTabla { 
   private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(controladorReporteTabla.class);;
   
   //Importo los logs
   //private  Font fuenteNegra10 = new Font(Font.getFamily(""), 10, Font.BOLD, Color.BLACK);
   controladorBD cc= new controladorBD();
   public void crearPDF(int TipoArchivo) throws Exception {
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia CreadorReportes()"+TipoArchivo);
      /*Leo el log en caso de que quiera imprimirlo :D*/
      String cadena;
      String archivo = "SistemaControlMedico.log";
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      System.out.println(b.toString());
      // Creating a PdfDocument object
      java.util.Date fecha = new Date();
//      System.out.println (fecha.getDay());
//      System.out.println (fecha.getMonth());
      fecha.getHours();
      String fechaFinal = fecha.getDay()+"_"+fecha.getMonth()+"_"+fecha.getYear()+"_"+fecha.getSeconds();
      String dest = "";
      String nombreReporte = "";
      String sql = "";
      /*Defenimos el nombre del archivo y el destino:*/
      switch(TipoArchivo){
          case 1:
              dest = ".\\reportes\\ReporteUnidadMedica"+fechaFinal+".pdf";
              nombreReporte = "Reporte Unidad Medica";
               sql= "select * from tabla_unidadmedica";
          break;
          case 2:
                dest = ".\\reportes\\ReportePacientes"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Pacientes";
               sql= "select * from tabla_pacientes";
          break;
          case 3:
              dest = ".\\reportes\\ReporteRecetas"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Recetas";
               sql= "select * from tabla_recetas";
          break;
          case 4:
              dest = ".\\reportes\\ReporteUsuarios"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Usuarios";
               sql= "select * from tabla_usuarios";
          break;
          case 5:
              dest = ".\\reportes\\ReporteCitas"+fechaFinal+".pdf";
              nombreReporte = "Reporte Citas";
               sql= "select * from tabla_citas";
          break;
          case 6:
              dest = ".\\reportes\\ReporteLogs"+fechaFinal+".pdf";
              nombreReporte = "Reporte Logs";
              //sql= "select * from tabla_citas";
          break;
          
      }
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se nombra y obtiene consulta para el reporte"); 
      Document document = new Document();
//      PdfWriter writer = new PdfWriter(dest);       
         
      // Creating a PdfDocument object      
//      PdfDocument pdf = new PdfDocument(writer);                  
      PdfWriter.getInstance(document, new FileOutputStream(dest));
      document.open();
      Paragraph preface = new Paragraph();
// We add one empty line
      addEmptyLine(preface, 1);
// Lets write a big header
      preface.add(new Paragraph(nombreReporte));
      preface.add(new Paragraph("Reporte Generado Por: " + System.getProperty("user.name") + ", " + new Date()));
      document.add(preface);
        // We add one empty line
      addEmptyLine(preface, 5);
      // Creating a Document object       
//      Document doc = new Document(pdf);                       
         
      // Creating a table 
      //N+umero de columnas :p
      float [] pointColumnWidths = tamColumnas(TipoArchivo);
      PdfPTable table = new PdfPTable(pointColumnWidths);
      table.setWidthPercentage(100);
      table.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell cell = new PdfPCell(new Paragraph("Tabla:"+nombreReporte));
      cell.setColspan(pointColumnWidths.length);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
      cell.setBackgroundColor (BaseColor.BLUE);        
      table.addCell(cell);
      
      String rotulosColumnas[] = rotulosColumna(TipoArchivo);
      
      for(int i=0; i<rotulosColumnas.length; i++)
      {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor (BaseColor.WHITE);
                    table.addCell(cell);
      }
      
      try { 
          
          Statement st =cc.openConnection().createStatement();
          ResultSet rs=st.executeQuery(sql);
          
           while(rs.next()){
           System.out.println(TipoArchivo);
           switch(TipoArchivo){
               case 1:
                   
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id_unidadmedica"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("um_paciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_folio")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_medico")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_consultorio")));
                    table.addCell(cell);
                    //String [] rotulosColumnaTipo1 = {"id_unidadmedica","um_paciente","um_folio","um_medico","um_consultorio"};
                   
               break;
               case 2:
                   
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id_paciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_nombres")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_apellidopaterno")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_apellidomaterno")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_sexo")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("pac_edad"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_curp")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_lugar")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_direccion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_colonia")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_direccion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getDate("pac_fechanac").toString()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("pac_idunidadmedica"))));
                    table.addCell(cell);
                   
               break;
               case 3:
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idreceta"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idpaciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idunidadmedica"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_descripcion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_alergias")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_estatura")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_peso")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_presion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_tiposangre")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idcita"))));
                    table.addCell(cell);
              break;
              case 4:
                    cell = new PdfPCell(new Paragraph(rs.getString("id_usuario") ));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("username")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("password")));
                    table.addCell(cell);
              break;
              
              case 5:
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt ("cm_idcita")) ));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idpaciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getDate("cm_idfecha").toString()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_fechahora")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_servicio")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_analisispac")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idunidadmedica"))));
                    table.addCell(cell);
                     cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idrecetas"))));
                    table.addCell(cell);
                   
              break;
           }
//                    cell = new PdfPCell(new Paragraph(rs.getDate("fechaContratacion").toString ()) );
//                    table.addCell(cell);
                
           }
           
//           con.close();
//           rs.close();

        } catch (SQLException e) {
            System.out.println(""+e);
            //JOptionPane.showMessageDialog(null,"Error en el acceso, vuelva a intentarlo" + e.getMessage());
        }
      // Adding Table to document        
      document.add(table);
      
      if(TipoArchivo==6){
      Anchor anchor = new Anchor("Logs");
      anchor.setName("Incio");
      Chapter catPart = new Chapter(new Paragraph(anchor), 1);
      Section subCatPart = catPart.addSection(preface);
      subCatPart.add(new Paragraph("_________________________________________________"));
      //Section subCatPart = null;
      
          List list = new List(true, false, 10);
          while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            list.add(cadena);
          }
          subCatPart.add(list);
          document.add(subCatPart);
      }
      
      //Close Log
      b.close();
      // Closing the document       
      document.close();
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se Crea el Reporte con éxito"); 
      System.out.println("Table created successfully..");
      
   }
   /*Constructor que exporta el pdf para imprimir por Usuario*/
   public void crearPreeInscripcionMedicaxUsuario(int TipoArchivo,int idReceta, int id_paciente, int rec_idcita) throws Exception {
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se inicia CreadorReportes()"+TipoArchivo);
      /*Leo el log en caso de que quiera imprimirlo :D*/
      String cadena;
      String archivo = "SistemaControlMedico.log";
      FileReader f = new FileReader(archivo);
      BufferedReader b = new BufferedReader(f);
      System.out.println(b.toString());
      // Creating a PdfDocument object
      java.util.Date fecha = new Date();
//      System.out.println (fecha.getDay());
//      System.out.println (fecha.getMonth());
      fecha.getHours();
      String fechaFinal = fecha.getDay()+"_"+fecha.getMonth()+"_"+fecha.getYear()+"_"+fecha.getSeconds();
      String dest = "";
      String nombreReporte = "";
      String sql = "";
      /*Defenimos el nombre del archivo y el destino:*/
      switch(TipoArchivo){
          case 1:
              dest = ".\\reportes\\ReporteUnidadMedica"+fechaFinal+".pdf";
              nombreReporte = "Reporte Unidad Medica";
               sql= "select * from tabla_unidadmedica";
          break;
          case 2:
                dest = ".\\reportes\\ReportePacientes"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Pacientes";
               sql= "select * from tabla_pacientes";
          break;
          case 3:
              dest = ".\\reportes\\ReporteRecetas"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Recetas";
              sql = "SELECT * FROM bdconsultorio.tabla_recetas, bdconsultorio.tabla_pacientes, bdconsultorio.tabla_citas";
              sql += " WHERE id_paciente = '"+id_paciente+"' and rec_idreceta = '"+idReceta+"' and rec_idcita = '"+rec_idcita+"'";
          break;
          case 4:
              dest = ".\\reportes\\ReporteUsuarios"+fechaFinal+".pdf"; 
              nombreReporte = "Reporte Usuarios";
               sql= "select * from tabla_usuarios";
          break;
          case 5:
              dest = ".\\reportes\\ReporteCitas"+fechaFinal+".pdf";
              nombreReporte = "Reporte Citas";
               sql= "select * from tabla_citas";
          break;
          case 6:
              dest = ".\\reportes\\ReporteLogs"+fechaFinal+".pdf";
              nombreReporte = "Reporte Logs";
              //sql= "select * from tabla_citas";
          break;
          
      }
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se nombra y obtiene consulta para el reporte"); 
      Document document = new Document();
//      PdfWriter writer = new PdfWriter(dest);       
         
      // Creating a PdfDocument object      
//      PdfDocument pdf = new PdfDocument(writer);                  
      PdfWriter.getInstance(document, new FileOutputStream(dest));
      document.open();
      Paragraph preface = new Paragraph();
// We add one empty line
      addEmptyLine(preface, 1);
// Lets write a big header
      preface.add(new Paragraph(nombreReporte));
      preface.add(new Paragraph("Reporte Generado Por: " + System.getProperty("user.name") + ", " + new Date()));
      document.add(preface);
        // We add one empty line
      addEmptyLine(preface, 5);
      // Creating a Document object       
//      Document doc = new Document(pdf);                       
         
      // Creating a table 
      //N+umero de columnas :p
      float [] pointColumnWidths = tamColumnas(TipoArchivo);
      PdfPTable table = new PdfPTable(pointColumnWidths);
      table.setWidthPercentage(100);
      table.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell cell = new PdfPCell(new Paragraph("Tabla:"+nombreReporte));
      cell.setColspan(pointColumnWidths.length);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        //Color de fondo de la celda
      cell.setBackgroundColor (BaseColor.BLUE);        
      table.addCell(cell);
      
      String rotulosColumnas[] = rotulosColumna(TipoArchivo);
      
      for(int i=0; i<rotulosColumnas.length; i++)
      {
                    cell = new PdfPCell(new Paragraph(rotulosColumnas[i]));
                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor (BaseColor.WHITE);
                    table.addCell(cell);
      }
      
      try { 
          
          Statement st =cc.openConnection().createStatement();
          ResultSet rs=st.executeQuery(sql);
          
           while(rs.next()){
           System.out.println(TipoArchivo);
           switch(TipoArchivo){
               case 1:
                   
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id_unidadmedica"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("um_paciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_folio")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_medico")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("um_consultorio")));
                    table.addCell(cell);
                    //String [] rotulosColumnaTipo1 = {"id_unidadmedica","um_paciente","um_folio","um_medico","um_consultorio"};
                   
               break;
               case 2:
                   
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("id_paciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_nombres")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_apellidopaterno")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_apellidomaterno")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_sexo")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("pac_edad"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_curp")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_lugar")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_direccion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_colonia")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("pac_direccion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getDate("pac_fechanac").toString()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("pac_idunidadmedica"))));
                    table.addCell(cell);
                   
               break;
               case 3:
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idreceta"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idpaciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idunidadmedica"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_descripcion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_alergias")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_estatura")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_peso")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_presion")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("rec_tiposangre")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("rec_idcita"))));
                    table.addCell(cell);
              break;
              case 4:
                    cell = new PdfPCell(new Paragraph(rs.getString("id_usuario") ));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("username")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("password")));
                    table.addCell(cell);
              break;
              
              case 5:
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt ("cm_idcita")) ));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idpaciente"))));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getDate("cm_idfecha").toString()));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_fechahora")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_servicio")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(rs.getString("cm_analisispac")));
                    table.addCell(cell);
                    cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idunidadmedica"))));
                    table.addCell(cell);
                     cell = new PdfPCell(new Paragraph(String.valueOf(rs.getInt("cm_idrecetas"))));
                    table.addCell(cell);
                   
              break;
           }
//                    cell = new PdfPCell(new Paragraph(rs.getDate("fechaContratacion").toString ()) );
//                    table.addCell(cell);
                
           }
           
//           con.close();
//           rs.close();

        } catch (SQLException e) {
            System.out.println(""+e);
            //JOptionPane.showMessageDialog(null,"Error en el acceso, vuelva a intentarlo" + e.getMessage());
        }
      // Adding Table to document        
      document.add(table);
      
      if(TipoArchivo==6){
      Anchor anchor = new Anchor("Logs");
      anchor.setName("Incio");
      Chapter catPart = new Chapter(new Paragraph(anchor), 1);
      Section subCatPart = catPart.addSection(preface);
      subCatPart.add(new Paragraph("_________________________________________________"));
      //Section subCatPart = null;
      
          List list = new List(true, false, 10);
          while((cadena = b.readLine())!=null) {
            System.out.println(cadena);
            list.add(cadena);
          }
          subCatPart.add(list);
          document.add(subCatPart);
      }
      
      //Close Log
      b.close();
      // Closing the document       
      document.close();
      logger.info("SistemaLogger.log", "Usuario: Actividad: Se Crea el Reporte con éxito"); 
      System.out.println("Table created successfully..");
      
   }
   /*Metodo para validar el tipo de reporte y devolver cabeceras
    Jose Luis Caamal Ic 13/03/2021*/
   public String[] rotulosColumna(int tipoArchivo){
       /*Tabla Unidad Medica*/
       String [] rotulosColumna = null;
       String [] rotulosColumnaTipo1 = {"IDUnidadMedica","IDPaciente","Folio","Medico","Consultorio"};
       /*Tabla Pacientes*/
       String [] rotulosColumnaTipo2 = 
       {"Paciente","Nombre(s)","ApellidoPaterno",
           "ApellidoMaterno","Edad","Sexo","DNI",
           "LUGAR","DIRECCION","NUMEROCASA","COLONIA","FechaNacimiento","IDUnidadMedica"};
       /*Tabla Recetas*/
       String [] rotulosColumnaTipo3 = 
       {"id","idPaciente","idUnidadMedica","Descripcion",
           "Alergias","Estatura","Peso","Presión",
           "TipoSangre","idCita"};
       /*Tabla Usuarios*/
       String [] rotulosColumnaTipo4 = 
       {"IDUsuario","UserName","Password"};
       /*Tabla Citas*/
       String [] rotulosColumnaTipo5 = 
       {"IDCita","IDPaciente","IDFecha",
           "Fecha","Servicio","Analisis","IDUnidadMedica",
           "IDRecetas"};
       String [] rotulosColumnaTipo6 = 
       {"Logs:"};
           
      switch(tipoArchivo){
   
       case 1:
           rotulosColumna = rotulosColumnaTipo1;
       break;
       
       case 2:
           rotulosColumna = rotulosColumnaTipo2;
       break;
       case 3:
           rotulosColumna = rotulosColumnaTipo3;
       break;
       case 4:
           rotulosColumna = rotulosColumnaTipo4;
       break;
       case 5:
           rotulosColumna = rotulosColumnaTipo5;
       break;
       default:
           rotulosColumna = rotulosColumnaTipo6;
       break;
   
   }
   
   return rotulosColumna;
   }
   
      /*Metodo para validar el tipo de reporte y devolver anchos de columna
    Jose Luis Caamal Ic 13/03/2021*/
   public float[] tamColumnas(int tipoArchivo){
       /*Tabla Unidad Medica*/
       float [] pointColumnWidths = null;
       float [] rotulosColumnaTipo1 = {3f,3f,3f,3f,3f};
       /*Tabla Pacientes*/
       float [] rotulosColumnaTipo2 = {3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f,3f};
       /*Tabla Recetas*/
       float [] rotulosColumnaTipo3 = {3f,3f,3f,5f,3f,3f,3f,3f,3f,3f};
       /*Tabla Usuarios*/
       float [] rotulosColumnaTipo4 = {3f,3f,3f};
       /*Tabla Citas*/
       float [] rotulosColumnaTipo5 = {3f,3f,3f,3f,3f,3f,3f,3f};
       /*Logs*/
       float [] rotulosColumnaTipo6 = {20f};
           
      switch(tipoArchivo){
   
       case 1:
           pointColumnWidths = rotulosColumnaTipo1;
       break;
       
       case 2:
           pointColumnWidths = rotulosColumnaTipo2;
       break;
       case 3:
           pointColumnWidths = rotulosColumnaTipo3;
       break;
       case 4:
           pointColumnWidths = rotulosColumnaTipo4;
       break;
       case 5:
           pointColumnWidths = rotulosColumnaTipo5;
       break;
       case 6:
           pointColumnWidths = rotulosColumnaTipo6;
       break;
       default:
       break;
   
   }
   
   return pointColumnWidths;
   }
   
   private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
                }
   }
}