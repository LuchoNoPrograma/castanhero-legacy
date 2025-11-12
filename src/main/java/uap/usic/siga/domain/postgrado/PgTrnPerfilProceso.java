package uap.usic.siga.domain.postgrado;

import uap.usic.siga.domain.compartido.base.EntidadAuditable;

import javax.persistence.*;

@Entity
@Table(name = "pg_trn_perfiles_procesos")
public class PgTrnPerfilProceso extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil_proceso")
    private Long idPerfilProceso;

    public Long getIdPerfilProceso() {
        return idPerfilProceso;
    }

    public void setIdPerfilProceso(Long idPerfilProceso) {
        this.idPerfilProceso = idPerfilProceso;
    }
}
