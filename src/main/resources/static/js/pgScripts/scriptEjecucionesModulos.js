console.log("xd")
const app = document.querySelector("#tabla")
//Instanciando la clase que tiene todos los metodos para consumir el REST API De una entidad y sus dependientes
const ejecucionServicio = new EjecucionModuloServicio()
const docenteServicio = new DocenteServicio()

//METODOS DE MAIN
const formGrupos = document.querySelectorAll(".formGrupos")
formGrupos.forEach(target => {
    target.addEventListener("click", async () => {
        const grupos = await ejecucionServicio.consumirGruposPorIdVersion(
            target.childNodes[1].value
        );

        //convertir el objeto JSON en un diccionario, donde el id es la clave y valor el nombre de grupo
        let diccionario = Object.fromEntries(grupos.map(e => [e.idPgPrgPlnGrupos, e.grupo]));
        const {value: clave} = await Swal.fire({
            title: 'Grupos disponibles',
            input: 'select',
            inputOptions: diccionario,
            showCancelButton: true,
            inputValidator: (value) => {
                return new Promise((resolve, reject) => {
                    try{
                        resolve()
                    }catch(exception){
                        reject("Error inesperado")
                    }
                })
            }
        })
        if(clave){
            window.location.replace(`/aEjecuciones/administrarGrupo=${clave}`);
        }
    })
})

const botonEjecucionModal = document.querySelectorAll(".botonEjecucionModal")
botonEjecucionModal.forEach(target => {
    target.addEventListener("click", async () => {
        const ejecucion = await ejecucionServicio.consumirEjecucionModuloPorId(target.childNodes[1].value);
        const listaDocentes = await docenteServicio.consumirDocentesEstadoA();
        //Tratar las promesas como si fuera un objeto y acceder a sus atributos
        console.log(ejecucion)
        console.log(listaDocentes)

        document.querySelector("#idEjecucionModal").value = target.childNodes[1].value;
        document.querySelector("#moduloModal").value = ejecucion.modulo.modulo
    
        let selectDocentes = document.querySelector("#idDocenteModal")
        if(selectDocentes.options.length == 1){ //Hacer condicional, de lo contrario, anhadira mas options cada vez que se oprima el boton
            listaDocentes.map(e => selectDocentes.appendChild(
                new Option(`${e.nombres} ${e.paterno} ${e.materno}`, e.idDocente)));
        }
    })
})

function verInformacion() {
    Swal.fire({
        title: 'Prueba',
        icon: 'success',
        confirmButtonText: 'Entendido'
    })
}