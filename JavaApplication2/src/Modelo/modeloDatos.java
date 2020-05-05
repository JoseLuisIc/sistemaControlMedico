/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JComboBox;

/**
 *
 * @author joseluis.caamal
 */
public class modeloDatos {
    /*BD*/
    private int id_pacientebd;
    private String pac_nombresbd;
    private String pac_apellidopaternobd;
    private String pac_apellidomaternobd;
    private int pac_edadbd;
    private String pac_sexobd;
    private String pac_curpbd;
    private String pac_lugarbd;
    private String pac_direccionbd;
    private String pac_numcasabd;
    private String pac_coloniabd;
    private String pac_fechanacbd;
    private int pac_idunidadmedicabd;
    /*Unidad Medica*/
    private int id_unidadmedica;
    private int um_paciente; 
    private String um_folio; 
    private String um_medico; 
    private String um_consultorio;
    private String um_seguro;

    public String getUm_seguro() {
        return um_seguro;
    }

    public void setUm_seguro(String um_seguro) {
        this.um_seguro = um_seguro;
    }

    public int getId_pacientebd() {
        return id_pacientebd;
    }

    public void setId_pacientebd(int id_pacientebd) {
        this.id_pacientebd = id_pacientebd;
    }

    public String getPac_nombresbd() {
        return pac_nombresbd;
    }

    public void setPac_nombresbd(String pac_nombresbd) {
        this.pac_nombresbd = pac_nombresbd;
    }

    public String getPac_apellidopaternobd() {
        return pac_apellidopaternobd;
    }

    public void setPac_apellidopaternobd(String pac_apellidopaternobd) {
        this.pac_apellidopaternobd = pac_apellidopaternobd;
    }

    public String getPac_apellidomaternobd() {
        return pac_apellidomaternobd;
    }

    public void setPac_apellidomaternobd(String pac_apellidomaternobd) {
        this.pac_apellidomaternobd = pac_apellidomaternobd;
    }

    public int getPac_edadbd() {
        return pac_edadbd;
    }

    public void setPac_edadbd(int pac_edadbd) {
        this.pac_edadbd = pac_edadbd;
    }

    public String getPac_sexobd() {
        return pac_sexobd;
    }

    public void setPac_sexobd(String pac_sexobd) {
        this.pac_sexobd = pac_sexobd;
    }

    public String getPac_curpbd() {
        return pac_curpbd;
    }

    public void setPac_curpbd(String pac_curpbd) {
        this.pac_curpbd = pac_curpbd;
    }

    public String getPac_lugarbd() {
        return pac_lugarbd;
    }

    public void setPac_lugarbd(String pac_lugarbd) {
        this.pac_lugarbd = pac_lugarbd;
    }

    public String getPac_direccionbd() {
        return pac_direccionbd;
    }

    public void setPac_direccionbd(String pac_direccionbd) {
        this.pac_direccionbd = pac_direccionbd;
    }

    public String getPac_numcasabd() {
        return pac_numcasabd;
    }

    public void setPac_numcasabd(String pac_numcasabd) {
        this.pac_numcasabd = pac_numcasabd;
    }

    public String getPac_coloniabd() {
        return pac_coloniabd;
    }

    public void setPac_coloniabd(String pac_coloniabd) {
        this.pac_coloniabd = pac_coloniabd;
    }

    public String getPac_fechanacbd() {
        return pac_fechanacbd;
    }

    public void setPac_fechanacbd(String pac_fechanacbd) {
        this.pac_fechanacbd = pac_fechanacbd;
    }

    public int getPac_idunidadmedicabd() {
        return pac_idunidadmedicabd;
    }

    public void setPac_idunidadmedicabd(int pac_idunidadmedicabd) {
        this.pac_idunidadmedicabd = pac_idunidadmedicabd;
    }

    public int getId_unidadmedica() {
        return id_unidadmedica;
    }

    public void setId_unidadmedica(int id_unidadmedica) {
        this.id_unidadmedica = id_unidadmedica;
    }

    public int getUm_paciente() {
        return um_paciente;
    }

    public void setUm_paciente(int um_paciente) {
        this.um_paciente = um_paciente;
    }

    public String getUm_folio() {
        return um_folio;
    }

    public void setUm_folio(String um_folio) {
        this.um_folio = um_folio;
    }

    public String getUm_medico() {
        return um_medico;
    }

    public void setUm_medico(String um_medico) {
        this.um_medico = um_medico;
    }

    public String getUm_consultorio() {
        return um_consultorio;
    }

    public void setUm_consultorio(String um_consultorio) {
        this.um_consultorio = um_consultorio;
    }

    
}
