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
@Table(name = "producciones_intelectuales")
public class ProduccionIntelectual extends SigaUsicRevisiones{
    /*==================RELACIONES======================
     * id_persona
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produccion_intelectual")
    private Long id_produccion_intelectual;

    private String produccion_intelectual;

    private String titulo;

    private String editorial;

    private String lugar;

    private String ip_registro;

    private String ip_modificacion;

    private String observaciones;
}
