package uap.usic.siga.entidadesPg;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_trn_perfiles_conceptos")
public class PgTrnPerfilConcepto extends SigaUsicRevisiones{
    /*================RELACIONES===================
     * id_perfil
     * 
     * id_concepto
     * 
     * id_programa
     * 
     * id_tipo_perfil
     * 
     * id_tipo_clasificacion
     * 
     * id_tipo_estudiante
     * 
     * id_tipo_admision
     * 
     * id_tipo_moneda
     * 
     * ult_usuario did_usuario
     * 
     * id_tipo_cuenta dentero DEFAULT 1
     * 
     * _id_estado dtexto2
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_concepto")
    private Long id_perfil_concepto;

    //costo dmoneda <- Observar

    private Boolean descuento;

    private Float cantidad;
}
