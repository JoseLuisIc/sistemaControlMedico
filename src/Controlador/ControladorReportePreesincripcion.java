/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/*
* @Author José Luis Caamal Ic
*/

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ControladorReportePreesincripcion {
    
    
    controladorBD cb = new controladorBD();
    public String returnPac(String idPaciente, String id_receta, String rec_idcita){
        String newSql = "";
        if(!"".equals(idPaciente) || !"".equals(id_receta) || !"".equals(rec_idcita)){
            newSql = "SELECT * FROM tabla_recetas trc LEFT JOIN tabla_pacientes tpa ON tpa.id_paciente = trc.rec_idpaciente\n" +
            "LEFT JOIN tabla_citas tpc ON tpc.cm_idcita = '"+rec_idcita+"' LEFT JOIN tabla_unidadmedica tum ON tum.id_unidadmedica = trc.rec_idunidadmedica ";
            newSql += " WHERE id_paciente = '"+idPaciente+"' and rec_idreceta = '"+id_receta+"' and rec_idcita = '"+rec_idcita+"'";
        }
        System.out.println(newSql);
        /*
        
        SELECT * FROM bdconsultorio.tabla_recetas, bdconsultorio.tabla_pacientes, bdconsultorio.tabla_citas
        WHERE id_paciente="8894839" and rec_idreceta = 4121926 and rec_idcita = 4699725;
        
        */
        return newSql;
    }

    public void generarInformePDF(String idPaciente, String id_receta, String rec_idcita) throws SQLException {
        String sql = returnPac( idPaciente,  id_receta,  rec_idcita);
        System.out.println(sql);
        PreparedStatement us = cb.openConnection().prepareStatement(sql);
        ResultSet res = us.executeQuery();
        
        java.util.Date fecha = new Date();
        // Crear el documento PDF
        Document document = new Document();
        String fechaFinal = fecha.getDay()+"_"+fecha.getMonth()+"_"+fecha.getYear()+"_"+fecha.getSeconds();
        String dest ="";
        try {
            // Especificar la ruta del archivo PDF de salida
            dest = ".\\reportes\\ReporteRecetas"+fechaFinal+".pdf"; 
            PdfWriter.getInstance(document, new FileOutputStream(dest));

            // Crear un evento para agregar encabezados y pies de página
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
            writer.setPageEvent(new HeaderFooterEvent());

            // Abrir el documento para escritura
            document.open();

            // Agregar contenido al documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE);
            Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            String name = "";
                String age = "";
                String sex = "";
                String pac = "";
                String desc= "";
                String fec = "";
                String alergia = "";
                String anPac = "";
                String estatura = "";
                String presion = "";
                String peso = "";
                String servicio = "";
                String medico = "";
                String tipoSangre = "";
                String fecNac = "";
            while(res.next()){
                 name = res.getString("pac_nombres") + " " +
                    res.getString("pac_apellidopaterno") + " " +
                    res.getString("pac_apellidomaterno");
                 age = res.getString("pac_edad");
                 sex = res.getString("pac_sexo");
                 pac = res.getString("id_paciente");
                 desc= res.getString("rec_descripcion");
                 fec = res.getString("cm_idfecha");
                 alergia = res.getString("rec_alergias");
                 anPac = res.getString("cm_analisispac");
                 estatura = res.getString("rec_estatura");
                 presion = res.getString("rec_presion");
                 peso = res.getString("rec_peso");
                 servicio = res.getString("cm_servicio");
                 medico = res.getString("um_medico");
                 tipoSangre = res.getString("rec_tiposangre");
                  fecNac = res.getString("pac_fechanac");
            }
            document.add(new Paragraph("Receta Médica", titleFont));
            document.add(new Paragraph("Fecha: " + fec,headingFont));
            document.add(new Paragraph("\nInformación del Paciente:", headingFont));
            document.add(new Paragraph("Nombre: " + name));
            document.add(new Paragraph("Edad: " + age));
            document.add(new Paragraph("Fecha de Nacimiento: " +fecNac ));
            document.add(new Paragraph("Sexo: " + sex));
            document.add(new Paragraph("ID del Paciente: " + pac));
            document.add(new Paragraph("Sintomas: " + anPac));
            
            
            document.add(new Paragraph("\nDatos Médicos del Paciente", headingFont));
            document.add(new Paragraph("Alergias: " + alergia));
            document.add(new Paragraph("Estatura: " + estatura));
             document.add(new Paragraph("Tipo de Sangre: " + tipoSangre));
            document.add(new Paragraph("Peso: " + peso));
            document.add(new Paragraph("Servicio: " + servicio));
            document.add(new Paragraph("Presión: " + presion));
            
            document.add(new Paragraph(""));
            
            document.add(new Paragraph("\nPreInscripción:", headingFont));
            document.add(new Paragraph(""));
            document.add(new Paragraph(desc));
            
            document.add(new Paragraph(""));
            document.add(new Paragraph("Firma:________________________________________"));
            document.add(new Paragraph(""));
            document.add(new Paragraph(""));
            document.add(new Paragraph("Medico: " + medico));
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el documento
            document.close();
        }
                    abrirPDFConAplicacionPredeterminada(dest);

    }
     private static void abrirPDFConAplicacionPredeterminada(String rutaPDF) {
        try {
            File archivoPDF = new File(rutaPDF);

            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(archivoPDF);
            } else {
                System.out.println("La apertura del PDF no es compatible con el entorno de ejecución actual.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Clase interna para agregar encabezados y pies de página
    private static class HeaderFooterEvent extends PdfPageEventHelper {
        public void onStartPage(PdfWriter writer, Document document) {
            PdfPTable header = new PdfPTable(1);
            try {
                // Configurar el estilo del encabezado
                header.setWidths(new int[]{1});
                header.setWidthPercentage(100);
                header.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                // Agregar contenido al encabezado
                header.addCell(new Phrase("PreInscripción Médica / Receta Médica", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));

                // Agregar el encabezado al documento
                document.add(header);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable footer = new PdfPTable(1);
            try {
                // Configurar el estilo del pie de página
                footer.setWidths(new int[]{1});
                footer.setWidthPercentage(100);
                footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);

                // Agregar número de página al pie de página
                footer.addCell(new Phrase("Página " + writer.getPageNumber(), FontFactory.getFont(FontFactory.HELVETICA, 8)));

                // Agregar el pie de página al documento
                document.add(footer);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }
}