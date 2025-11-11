package uap.usic.siga.entidadesPg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pg_ins_tipos_publicaciones")
public class PgInsTipoPublicacion extends SigaUsicRevisiones{
    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @JoinColumn(name = "fk_id_institucion", referencedColumnName = "id_institucion")
    private PgInstituciones id_institucion;
    /*==================RELACIONES=====================
     * id_institucion
     * 
     * id_rol integer DEFAULT 0
     * 
     * ult_usuario did_usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_publicacion")
    private Long id_tipo_publicacion;

    private String tipo_publicacion;
}
