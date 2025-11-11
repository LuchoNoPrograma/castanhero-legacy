package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ubicaciones_organicas")
public class UbicacionOrganica extends SigaUsicRevisiones {
    /*=================RELACIONES=================
     * id_ubicacion_organica_padre //no existe la tabla
     * 
     * id_tipo_calificacion
     * 
     * id_apertura_programatica //no existe la tabla
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion_organica")
    private Long id_ubicacion_organica;

    private String codigo_ubicacion_organica;

    private String ubicacion_organica;

    private Integer nivel;

    private Integer correlativo;
}
