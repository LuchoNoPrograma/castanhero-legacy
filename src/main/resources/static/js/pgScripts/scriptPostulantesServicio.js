class PostulanteServicioAjax{
    constructor(){
        this.pstAPI = '/aPostulantes/api'
    }

    /**
     * @returns funcion asincrona que retorna JSON, acceder con await antes de esta funcion
     */
    async consumirPostulantesGeneral(){
        const promise = await fetch(this.pstAPI+'/postulantes',{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
        const result = await promise.json()
        return result
    }

    /**
     * @param {id} id del programa, usar input hidden que almacenan el id del Programa
     * @returns funcion asincrona que retorna JSON, acceder con await antes de esta funcion
     */
    async consumirPostulantesPorPrograma(idPrograma){
        const promise = await fetch(this.pstAPI+`/postulantes-programa=${idPrograma}`,{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
        const result = await promise.json()
        return result
    }

    /**
     * @param {number} id valor del input #idPostulante, usar input hidden
     * @returns funcion asincrona que retorna JSON, acceder con await antes de esta funcion
     */
    async consumirBuscarPortulanteId(idPostulante){
        const promise = await fetch(this.pstAPI+`/postulante-id=${idPostulante}`,{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
        const response = await promise.text()
        return response === "" ? undefined : JSON.parse(response)
    }

    /**
     * @param {string} ci valor del input #ciPostulante, usar input text
     * @returns funcion asincrona que retorna JSON, acceder con await antes de esta funcion
     */
    async consumirBuscarPostulanteCi(ciPostulante){
        
        const promise = await fetch(this.pstAPI+`/postulante-ci=${ciPostulante}`,{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
        const response = await promise.text()
        return response === "" ? undefined : JSON.parse(response);
    }
}