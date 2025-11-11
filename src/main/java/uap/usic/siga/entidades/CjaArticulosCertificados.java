package uap.usic.siga.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Rectorado - USIC
 * Fecha; 2019-09-27
 * @author Freddy Morales
 */
@Entity
@Table(name = "cja_articulos_certificados")
public class CjaArticulosCertificados extends SigaUsicRevisiones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cja_articulo_certificado")
    private Long idCjaArtituloCertificado;

    @NotNull
    @Column(name = "art[iculo")
    private String articulo;

    @Column(name = "cantidad")
    private Integer cantidad;

}
