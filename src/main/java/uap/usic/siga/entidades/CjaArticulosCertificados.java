package uap.usic.siga.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


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
