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
