class EjecucionModuloServicio{
    constructor(){
        this.emAPI = '/aEjecuciones'
    }

    /**
     * @param {id} id de la version, usar input hidden para acceder a sus grupos
     * @returns funcion asincrona que retorna JSON, acceder con await antes de esta funcion
     */
    async consumirGruposPorIdVersion(idVersion){
        const promise = await fetch(this.emAPI+`/api/grupos-idVersion=${idVersion}`,{
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