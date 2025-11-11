   String.prototype.toTitleCase = function () {
    return this.replace(/\w\S*/g, function(txt){
        return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
    });
};
//INPUT PARA NOMBRES.- luis alberto -> Luis Alberto
$(".input-titlecase").on("keyup", function(e){
    this.value = this.value.toUpperCase()
})
//VALIDACIONES CI
let validarCi = new RegExp('(([A-Z]|[0-9]))?([A-Z]?[0-9]{4,14}|(-?([A-Z]|[0-9]))){3,20}') 
let textoCi = $(".error-ci")//tag <small> debajod el input
$(".validacion-ci").on("blur", function (e){
    if(!validarCi.test(this.value)){
        e.target.value = null
        textoCi.html("Ingrese un CI valido")
        textoCi.css("color", "#ff3333")
    }else{
        textoCi.html("Ingrese CI")
        textoCi.css("color", "#6c757d")
    }
})

//VALIDACIONES FECHA NACIMIENTO
function isDateBeforeToday(date) {
    //Validacion de la fecha actual - 10 anhos y la fecha de Nacimiento
    let fechaActual = new Date()
    fechaActual.setFullYear(new Date().getFullYear() - 10)
    return new Date(date.toDateString()) < new Date(fechaActual.toDateString())
}

//Usar para validar el ingreso a la URL
/*
Swal.fire({
    title: 'Confirmar',
    text: 'Ingreso a la pagina?',
    icon: 'success',
    confirmButtonText: 'Si'
})
*/

//Se consume el REST API presente en adminsitrarPostulantes
const pstServicio = new PostulanteServicioAjax()

async function validarCiAnadirPostulante(){
    const dataGeneral = await pstServicio.consumirPostulantesGeneral()
    const dataFiltrado = await pstServicio.consumirPostulantesPorPrograma($("#inputIdProgramaAnadir").val())
    let buscarGeneral = dataGeneral.find(e => e.ci == $("#inputCiAnadir").val())
    let buscarFiltro = dataFiltrado.find(e => e.ci == $("#inputCiAnadir").val())

    if(buscarGeneral == undefined){
        Swal.fire({
           title: 'Error',
           text: `El CI ${$("#inputCiAnadir").val()} no se encuentra registrado en el sistema`,
           icon: 'error',
           confirmButtonText: 'Entendido'})
        return false
    }
    else if(!(buscarFiltro == undefined)){
        Swal.fire({
            title: 'Error',
            text: `El CI ${$("#inputCiAnadir").val()} ya se encuentra registrado en este programa!`,
            icon: 'error',
            confirmButtonText: 'Entendido'})
        return false
    }
    else{
        return true
    }
}

async function validarCiNuevoPostulante(){
    let ciPostulante = $("#ciPostulante").val()

    const dataGeneral = await pstServicio.consumirPostulantesGeneral()
    const dataFiltrado = await pstServicio.consumirPostulantesPorPrograma($("#idPrograma").val())
    let buscarGeneral = dataGeneral.find(e => e.ci == ciPostulante)
    let buscarFiltro = dataFiltrado.find(e => e.ci == ciPostulante)

    if(buscarFiltro != undefined){
        Swal.fire({
           title: 'Error',
           text: `el CI ${$("#ciPostulante").val()} ya se encuentra postulado en este programa!`,
           icon: 'error',
           confirmButtonText: 'Entendido'})
        return false
    }
    else if(buscarGeneral != undefined){
        Swal.fire({
            title: 'Error',
            text: `El CI ${$("#ciPostulante").val()} ya se encuentra registrado en el sistema!`,
            icon: 'error',
            confirmButtonText: 'Entendido'})
        return false
    }
    else{
        return true
    }
}

async function validarCiExistentePostulante(){
    const datoBusqueda = await pstServicio.consumirBuscarPostulanteCi($("#ciPostulante").val())

    if(datoBusqueda == undefined){
        console.log("El ci esta disponible, modificar")
        return true 
    }else if(datoBusqueda.idPostulante == $("#idPostulante").val()){
        console.log("El ci pertenece a esta peticion, modificar")
        return true
    }else{
        Swal.fire({
            title: 'Error',
            text: `El CI ${$("#ciPostulante").val()} ya se encuentra registrado en el sistema!`,
            icon: 'error',
            confirmButtonText: 'Entendido'
        })
        return false
    }
}
//OPERACION TABLA
$(document).ready(function(){
    if($("#busquedaTabla".length>0)){
        $("#botonAnadir").click(function(e) {
            //Cancelar el submit y consumir el API
            e.preventDefault()
            validarCiAnadirPostulante().then(response => {
                if(response){
                    document.querySelector("#formularioAnadir").submit()
                }else{
                    e.preventDefault()
                }
            })
        });
    } 
    if($("#registrarNuevoPostulante").length > 0){
        $("#botonRegistrar").click(function(e) {
            e.preventDefault()
            validarCiNuevoPostulante().then(response => {
                if(response){
                    document.querySelector("#formulario").submit()
                }else{
                    e.preventDefault()
                }
            })
        })
    }
    if($("#modificarPostulante").length > 0){
        $("#botonModificar").click(function(e) {
            e.preventDefault()
            validarCiExistentePostulante().then(response =>{
                if(response){
                    let form = document.querySelector("#formulario")
                    Swal.fire({
                        title: 'Esta seguro de modificar?',
                        text: "La modificacion afectara a todos los registros del postulante!",
                        icon: 'warning',
                        showDenyButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#d33',
                        confirmButtonText: 'Si, modificar!',
                        denyButtonText: `Cancelar`,
                    }).then((result) => {
                        if (result.isConfirmed) {
                            Swal.fire({
                                title: 'Modicacion exitosa!',
                                text: 'La modificacion se aplico en todos los registros',
                                icon: 'success'
                            })
                            return new Promise(() => {
                                setTimeout(() => {document.querySelector("#formulario").requestSubmit()}, 2000)})
                        }
                    })
                }else{
                    e.preventDefault()
                }
            })
        })
    }
    if($("#eliminarPostulante").length > 0){
        $("#botonEliminar").click(function(e) {
            e.preventDefault()
            Swal.fire({
                title: '¿Esta seguro de eliminar?',
                text: 'Se eliminara la postulación del programa',
                icon: 'warning',
                showDenyButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Si, eliminar!',
                denyButtonText: `Cancelar`,
            }).then((result) => {
                if(result.isConfirmed){
                    Swal.fire({
                        title: 'Eliminacion confirmada!',
                        text: 'Se removio la postulación del programa',
                        icon: 'success'
                    })
                    return new Promise(() => {
                        setTimeout(() => {document.querySelector("#formulario").requestSubmit()}, 2000)})
                }
            })
        })
    }
})


//OPERACION REGISTRAR NUEVO
if($("#registrarNuevoPostulante").length > 0){
    $("#fecNacimiento").on("blur", function (e){
        let fecNacimiento = $("#fecNacimiento").val().split("-")
        let fechaFormato = new Date(fecNacimiento[0], fecNacimiento[1]-1, fecNacimiento[2])

        let textoFecha = $(".error-fecha-nacimiento")
        //Si la fecha NO es valida hacer lo siguiente:
        if(!isDateBeforeToday(fechaFormato)){
            e.target.value = null
            textoFecha.html("La fecha de nacimiento no es valida")
            textoFecha.css("color", "#ff3333")
        }else{
            textoFecha.html("Seleccione fecha de nacimiento")
            textoFecha.css("color", "#6c757d")
        }
    })
}