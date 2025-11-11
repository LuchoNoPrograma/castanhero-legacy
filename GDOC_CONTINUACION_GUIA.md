# GUIA PARA CONTINUAR LA MIGRACION GDOC

## PATRON ESTABLECIDO

La migracion del modulo GDOC ha establecido un patron claro y replicable. Este documento proporciona instrucciones paso a paso para completar los controladores y vistas restantes.

---

## PATRON DE CONTROLADOR

### Estructura Base
```java
package uap.usic.siga.web.gdoc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/ruta-base")
public class AdministrarXXXController {

    // 1. INYECCION POR CONSTRUCTOR
    private final XXXService xxxService;
    private final GdocArchivosAdjuntosService archivosService;
    private final GdocConsejosService consejosService;
    private final UsuariosServicios usuariosServicios;

    public AdministrarXXXController(XXXService xxxService,
                                    GdocArchivosAdjuntosService archivosService,
                                    GdocConsejosService consejosService,
                                    UsuariosServicios usuariosServicios) {
        this.xxxService = xxxService;
        this.archivosService = archivosService;
        this.consejosService = consejosService;
        this.usuariosServicios = usuariosServicios;
    }

    // 2. ENDPOINT LISTADO
    @GetMapping("/inicio")
    public String inicio(Model model, HttpSession session) {
        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
    }

    // 3. ENDPOINT FORMULARIO NUEVO
    @PostMapping("/inicioForm")
    public String inicioForm(@ModelAttribute("objeto") Objeto objeto,
                            Model model, HttpSession session) {
        desplegarListasComunes(model, session);
        model.addAttribute("operation", "reg");
        return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
    }

    // 4. ENDPOINT GUARDAR
    @PostMapping("/registro")
    public String registrar(@Valid @ModelAttribute("objeto") Objeto objeto,
                           BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            desplegarListasComunes(model, session);
            model.addAttribute("operation", "reg");
            return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
        }

        // Logica de guardado con archivos
        xxxService.guardar(objeto);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
    }

    // 5. ENDPOINT VER PDF
    @GetMapping(value = "/openFile/{id}", produces = "application/pdf")
    @ResponseBody
    public Resource abrirArchivo(HttpServletResponse response, @PathVariable("id") Long id) {
        GdocArchivosAdjuntos archivo = xxxService.buscarArchivoPorXXX(id);
        File file = new File("C:/" + archivo.getRutaArchivo() + archivo.getNombreArchivo());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=" + file.getName());
        response.setHeader("Content-Length", String.valueOf(file.length()));

        return new FileSystemResource(file);
    }

    // 6. ENDPOINT MODIFICAR
    @PostMapping("/inicioModificar")
    public String inicioModificar(@RequestParam("id") Long id,
                                  Model model, HttpSession session) {
        model.addAttribute("objeto", xxxService.buscarPorId(id));
        model.addAttribute("operation", "mod");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
    }

    // 7. ENDPOINT ACTUALIZAR
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("objeto") Objeto objeto,
                            BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("operation", "mod");
            desplegarListasComunes(model, session);
            return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
        }

        xxxService.actualizar(objeto);

        model.addAttribute("busqueda", "find");
        desplegarListasComunes(model, session);
        return "uap/usic/siga/gdoc/administrarXXX/administrarXXX";
    }

    // 8. METODO PRIVADO REUTILIZABLE
    private void desplegarListasComunes(Model model, HttpSession session) {
        Long idUsuario = (Long) session.getAttribute("currentUserId");
        Long idMnuTipoFuncion = 13L;

        model.addAttribute("gdocConsejos",
            consejosService.buscarPorUsuarioYFuncion(idUsuario, idMnuTipoFuncion));
        model.addAttribute("listaXXX", xxxService.listarPorConsejo(...));
    }
}
```

---

## PATRON DE VISTA THYMELEAF

### Estructura Base
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{uap/usic/siga/gdoc/fragments/common :: head('GDOC - Titulo')}">
</head>

<body>
<div class="container">

    <!-- HEADER -->
    <div th:replace="~{uap/usic/siga/gdoc/fragments/common :: header('Titulo Modulo')}"></div>

    <section>
        <!-- ALERTAS -->
        <div th:replace="~{uap/usic/siga/gdoc/fragments/common :: alerts}"></div>

        <!-- MODO LISTADO -->
        <div th:if="${busqueda eq 'find'}">
            <div class="text-center mb-4">
                <h3><i class="fa fa-icono"></i> Listado de XXX</h3>
            </div>

            <table class="table table-striped table-hover data-table">
                <thead class="table-dark">
                    <tr>
                        <th>Columna 1</th>
                        <th>Columna 2</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr th:each="item : ${listaXXX}">
                        <td th:text="${item.campo1}"></td>
                        <td th:text="${item.campo2}"></td>
                        <td>
                            <a class="btn btn-sm btn-info"
                               th:href="@{/ruta/openFile/{id}(id=${item.id})}"
                               target="_blank">
                                <i class="fa fa-file-pdf"></i> PDF
                            </a>
                            <form method="post" th:action="@{/ruta/inicioModificar}" class="d-inline">
                                <input type="hidden" name="id" th:value="${item.id}">
                                <button class="btn btn-sm btn-success" type="submit">
                                    <i class="fa fa-pencil"></i> Modificar
                                </button>
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- MODO FORMULARIO -->
        <div th:if="${operation eq 'reg' or operation eq 'mod'}">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            <h4 th:text="${operation eq 'reg' ? 'Nuevo XXX' : 'Modificar XXX'}"></h4>
                        </div>
                        <div class="card-body">
                            <form method="post"
                                  th:action="${operation eq 'reg' ? '@{/ruta/registro}' : '@{/ruta/actualizar}'}"
                                  th:object="${objeto}"
                                  enctype="multipart/form-data">

                                <input type="hidden" th:field="*{id}" th:if="${operation eq 'mod'}">

                                <!-- CAMPOS DEL FORMULARIO -->
                                <div class="mb-3">
                                    <label for="campo1" class="form-label">Campo 1:</label>
                                    <input type="text" class="form-control" id="campo1"
                                           th:field="*{campo1}" required>
                                </div>

                                <!-- ARCHIVO PDF -->
                                <div class="mb-3">
                                    <label for="file" class="form-label">Archivo PDF:</label>
                                    <input type="file" class="form-control" id="file"
                                           th:field="*{file}" accept="application/pdf">
                                </div>

                                <!-- BOTONES -->
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <a th:href="@{/ruta/inicio}" class="btn btn-secondary">
                                        <i class="fa fa-times"></i> Cancelar
                                    </a>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fa fa-save"></i> Guardar
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- SCRIPTS -->
<div th:replace="~{uap/usic/siga/gdoc/fragments/common :: scripts}"></div>

</body>
</html>
```

---

## CONTROLADORES PENDIENTES - GUIA RAPIDA

### 1. AdministrarResolucionesDigitalesController

**Ruta:** `/resolucionesDigitales`
**Servicio:** GdocResolucionesService (ya existe)
**Vista:** `administrarResolucionesDigitales/administrarResolucionesDigitales.html`

**Particularidades:**
- No maneja archivos PDF (es digital/HTML)
- Campos: titulo, vistos, considerandos, porlotanto, resuelve
- Editor HTML para contenido

**Campos del Formulario:**
```html
<textarea class="form-control" id="vistosResolucion"
          th:field="*{vistosResolucion}" rows="5"></textarea>
<textarea class="form-control" id="considerandosResolucion"
          th:field="*{considerandosResolucion}" rows="5"></textarea>
<textarea class="form-control" id="resuelveResolucion"
          th:field="*{resuelveResolucion}" rows="5"></textarea>
```

---

### 2. AdministrarConsejosController

**Ruta:** `/consejos`
**Servicio:** GdocConsejosService (ya existe)
**Vista:** `administrarConsejos/administrarConsejos.html`

**Particularidades:**
- Catalogo simple (consejo, sigla)
- Sin archivos adjuntos
- Relacion con usuarios

**Campos del Formulario:**
```html
<input type="text" class="form-control" th:field="*{consejo}" required>
<input type="text" class="form-control" th:field="*{sigla}" required>
```

---

### 3. AdministrarGestionConsejosController

**Ruta:** `/gestionConsejos`
**Servicio:** GdocGestionConsejosService (ya existe)
**Vista:** `administrarGestionConsejos/administrarGestionConsejos.html`

**Particularidades:**
- Relacion con GdocConsejos
- Campos: gestionConsejo, fecInicio, fecFinal

**Campos del Formulario:**
```html
<select class="form-select" th:field="*{gdocConsejos}">
    <option th:each="consejo : ${listaConsejos}"
            th:value="${consejo.idGdocConsejo}"
            th:text="${consejo.consejo}"></option>
</select>
<input type="text" class="form-control" th:field="*{gestionConsejo}">
<input type="date" class="form-control" th:field="*{fecInicio}">
<input type="date" class="form-control" th:field="*{fecFinal}">
```

---

### 4. AdministrarFuncionesUsuariosController

**Ruta:** `/funcionesUsuarios`
**Servicio:** Crear GdocUsrTiposFuncionesService
**Vista:** `administrarFuncionesUsuarios/administrarFuncionesUsuarios.html`

**Particularidades:**
- Asignar funciones a usuarios
- Relacion triple: Usuario, Consejo, TipoFuncion

**Nuevo Servicio Necesario:**
```java
public interface GdocUsrTiposFuncionesService {
    List<GdocUsrTiposFunciones> listarTodos();
    List<GdocUsrTiposFunciones> listarPorUsuario(Long idUsuario);
    List<GdocUsrTiposFunciones> listarPorConsejo(Long idGdocConsejo);
    GdocUsrTiposFunciones guardar(GdocUsrTiposFunciones gdocUsrTiposFunciones);
    void eliminar(Long id);
}
```

---

### 5. ReportesConveniosController

**Ruta:** `/reportesConvenios`
**Servicio:** GdocConveniosService (ya existe)
**Vista:** `reportesConvenios/reportesConvenios.html`

**Particularidades:**
- Filtros por rango de fechas
- Filtros por tipo de convenio
- Exportacion a PDF

**Metodos Adicionales del Servicio:**
```java
List<GdocConvenios> listarPorRangoFechas(Date inicio, Date fin);
List<GdocConvenios> listarPorTipo(Long idTipo);
```

**Formulario de Filtros:**
```html
<div class="row">
    <div class="col-md-4">
        <label>Fecha Inicio:</label>
        <input type="date" name="fecInicio" class="form-control">
    </div>
    <div class="col-md-4">
        <label>Fecha Fin:</label>
        <input type="date" name="fecFinal" class="form-control">
    </div>
    <div class="col-md-4">
        <label>Tipo Convenio:</label>
        <select name="idTipo" class="form-select">
            <option th:each="tipo : ${listaTipos}"
                    th:value="${tipo.idGdocTipoConvenio}"
                    th:text="${tipo.tipoConvenio}"></option>
        </select>
    </div>
</div>
<button type="submit" class="btn btn-primary">Buscar</button>
```

---

## VISTAS DE IMPRESION - GUIA RAPIDA

### 6. imprimirResolucion.html

**Ruta:** `/resolucion/imprimir/{id}`
**Tipo:** Vista de impresion (sin menu, solo contenido)

**Estructura:**
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>Resolucion</title>
    <style>
        @media print {
            .no-print { display: none; }
        }
        body { font-family: Arial; margin: 2cm; }
    </style>
</head>
<body>
    <div class="text-center">
        <img src="/resource/images/logo.png" width="100">
        <h2>UNIVERSIDAD AUTONOMA DEL PERU</h2>
        <h3 th:text="'RESOLUCION N° ' + ${resolucion.nroResolucion}"></h3>
    </div>

    <p><strong>Fecha:</strong> <span th:text="${#dates.format(resolucion.fecResolucion, 'dd/MM/yyyy')}"></span></p>
    <p><strong>Folio:</strong> <span th:text="${resolucion.nroFolio}"></span></p>

    <h4>CONSIDERANDO:</h4>
    <p th:text="${resolucion.objetoResolucion}"></p>

    <div class="no-print mt-4">
        <button onclick="window.print()" class="btn btn-primary">Imprimir</button>
        <a th:href="@{/resolucion/inicioResol}" class="btn btn-secondary">Volver</a>
    </div>
</body>
</html>
```

### 7. imprimirResolucionDigital.html
Similar a imprimirResolucion.html pero con secciones adicionales:
- Vistos
- Considerandos
- Por lo tanto
- Resuelve

### 8. imprimirReporteConvenios.html
Tabla con listado de convenios filtrados

---

## CHECKLIST DE IMPLEMENTACION

Para cada controlador/vista pendiente:

- [ ] 1. Crear el controlador en `/web/gdoc/`
- [ ] 2. Verificar que el servicio exista (o crearlo)
- [ ] 3. Implementar todos los endpoints (minimo 6)
- [ ] 4. Crear la vista en `/templates/.../gdoc/`
- [ ] 5. Implementar modo listado con DataTable
- [ ] 6. Implementar formulario de registro
- [ ] 7. Implementar formulario de modificacion
- [ ] 8. Probar flujo completo: listar → nuevo → guardar → modificar → actualizar
- [ ] 9. Verificar visualizacion de PDF (si aplica)
- [ ] 10. Ajustar estilos y validaciones

---

## COMANDOS UTILES

### Compilar proyecto
```bash
./mvnw clean install
```

### Ejecutar aplicacion
```bash
./mvnw spring-boot:run
```

### Ver logs
```bash
tail -f logs/application.log
```

---

## CONTACTO Y SOPORTE

Para dudas o consultas sobre la migracion:
- Revisar este documento
- Revisar `GDOC_MIGRATION_REPORT_FINAL.md`
- Consultar los controladores ya migrados como referencia
- Consultar las vistas ya migradas como referencia

---

**Fecha:** 2025-11-11
**Version:** 1.0
**Autor:** Claude Sonnet 4.5
