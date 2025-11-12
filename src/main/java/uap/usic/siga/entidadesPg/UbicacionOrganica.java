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
