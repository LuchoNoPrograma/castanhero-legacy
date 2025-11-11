package uap.usic.siga.dto;

import java.util.Date;
import java.util.List;

import uap.usic.siga.entidades.PrsCiExpedidos;
import uap.usic.siga.entidades.PrsPaises;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidadesPg.PgPstProgramas;
import uap.usic.siga.entidadesPg.Postulantes;

public class PostulantesResponse {
    private Long idPostulante;
    private String nombres;
    private String paterno;
    private String materno;
    private String ci;
    private String celular;
    private Date fecNacimiento;
    private String email;

    private PrsTiposSexos prsTiposSexos;
    private PrsPaises prsPaises;
    private PrsCiExpedidos prsCiExpedidos;
    private List<PgPstProgramas> pgPstProgramas;
    
    public Long getIdPostulante() {
        return idPostulante;
    }
    public void setIdPostulante(Long idPostulante) {
        this.idPostulante = idPostulante;
    }
    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getPaterno() {
        return paterno;
    }
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
    public String getMaterno() {
        return materno;
    }
    public void setMaterno(String materno) {
        this.materno = materno;
    }
    public String getCi() {
        return ci;
    }
    public void setCi(String ci) {
        this.ci = ci;
    }
    public String getCelular() {
        return celular;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public Date getFecNacimiento() {
        return fecNacimiento;
    }
    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public PrsTiposSexos getPrsTiposSexos() {
        return prsTiposSexos;
    }
    public void setPrsTiposSexos(PrsTiposSexos prsTiposSexos) {
        this.prsTiposSexos = prsTiposSexos;
    }
    public PrsPaises getPrsPaises() {
        return prsPaises;
    }
    public void setPrsPaises(PrsPaises prsPaises) {
        this.prsPaises = prsPaises;
    }
    public PrsCiExpedidos getPrsCiExpedidos() {
        return prsCiExpedidos;
    }
    public void setPrsCiExpedidos(PrsCiExpedidos prsCiExpedidos) {
        this.prsCiExpedidos = prsCiExpedidos;
    }
    public List<PgPstProgramas> getPgPstProgramas() {
        return pgPstProgramas;
    }
    public void setPgPstProgramas(List<PgPstProgramas> pgPstProgramas) {
        this.pgPstProgramas = pgPstProgramas;
    }    
    
}
