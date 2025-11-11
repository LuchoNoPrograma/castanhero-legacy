package uap.usic.siga.domain.compartido;

/**
 * Clase DTO para manejo de archivos de comprobantes.
 * Nota: Referencia a SacComprobantes comentada por migración parcial
 */
public class ArchivoComprobante {

    // TODO: Descomentar cuando se migre SacComprobantes
    // private SacComprobantes sacComprobantes;
    private ArchivoAdjunto archivoAdjunto;

    public ArchivoComprobante() {
    }

    public ArchivoComprobante(ArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }

    // public SacComprobantes getSacComprobantes() {
    //     return sacComprobantes;
    // }

    // public void setSacComprobantes(SacComprobantes sacComprobantes) {
    //     this.sacComprobantes = sacComprobantes;
    // }

    public ArchivoAdjunto getArchivoAdjunto() {
        return archivoAdjunto;
    }

    public void setArchivoAdjunto(ArchivoAdjunto archivoAdjunto) {
        this.archivoAdjunto = archivoAdjunto;
    }
}
