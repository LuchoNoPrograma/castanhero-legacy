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
@Table(name = "tipos_calificaciones")
public class TipoCalificacion extends SigaUsicRevisiones{
    /*====================RELACIONES=======================
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_calificacion")
    private Long id_tipo_calificacion;

    private String tipo_calificacion;

    private String codigo_calificacion;
}
