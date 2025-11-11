class DocenteServicio{
    constructor(){
        //Cambiara por /aDocentes
        this.emAPI = '/aEjecuciones'
    }

    /**
     * @returns funcion asincrona que retorna un JSON de todos los docentes con estado 'A'
     * no incluye sus datos dependientes como sexo, pais, ciExpedido entre otros
     */
    async consumirDocentesEstadoA(){
        const promise = await fetch(this.emAPI+`/api/docentes`,{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
        const result = await promise.json()
        return result
    }

    /**
     * 
     * @param {number} id del ejecucion modulo a consumir, se usara input hidden para obtener el id
     * @returns funcion asincrona que retorna una promesa, consumir su JSON para rellenar el modal
     */
    async consumirEjecucionModuloPorId(idEjecucion){
        const promise = await fetch(this.emAPI+`/api/ejecucionModulo=${idEjecucion}`,{
            method: "GET", headers: {"Content-Type": "application/json; charset=utf-8"}})
            .catch(error => {
                console.log(error)
            })
            const result = await promise.json()
            return result
    }

    /**
     * 
     * @param {id} idGrupo, renderizara el contenido de la pagina atravez de Thymeleaf
     */
    async renderizarModulosPorIdGrupo(idGrupo){
        const promise = await fetch(this.emAPI+`/administrarGrupo=${idGrupo}`, {
            method: "GET",headers: {}})
            .catch(error => {
                console.log(error)
            })
        return promise;
    }
}