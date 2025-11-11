package uap.usic.siga.controladores.pg.administrarDocumentos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import uap.usic.siga.entidades.Personas;
import uap.usic.siga.entidades.PrsTiposSexos;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.servicios.PersonasServicios;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;

@Controller
@RequestMapping("/api/v1/")
public class ExcelApi {
	@Autowired
	private PostulantesServicios pstServicios;

	@Autowired
	private PersonasServicios prsServicios;

	@Autowired
	private ProgramasService prgServicios;

    @GetMapping("/subirDocumentos")
    public String leerDocumentos(Model model, HttpSession session){
        model.addAttribute("operation", "formExcel");
        return "uap/usic/siga/pg/documentos/excel";
    }

    @ResponseBody
    @PostMapping(value = "/leerExcel")
    public String leerExcel(@RequestParam("excel") MultipartFile excel){
        try {
			XSSFWorkbook documento = new XSSFWorkbook(excel.getInputStream());
			XSSFSheet calculo = documento.getSheetAt(0);
			for(int i=1; i<calculo.getPhysicalNumberOfRows()-1;i++) {
				XSSFRow row = calculo.getRow(i);
				Postulantes p = new Postulantes(
					row.getCell(0).getStringCellValue(),row.getCell(1).getStringCellValue(),
					row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue(),
					row.getCell(4).getStringCellValue()+"@uap.pg.com",row.getCell(5).getStringCellValue(),
					row.getCell(6).getStringCellValue(),row.getCell(7).getStringCellValue(),
					row.getCell(8).getStringCellValue(),row.getCell(9).getDateCellValue(),
					Integer.parseInt(row.getCell(10).getStringCellValue()));
				p.setPrsCiExpedidos(prsServicios.buscarCiExpedidosPorId((long)row.getCell(11).getNumericCellValue()));
				p.setPrsEstadoCivil(prsServicios.buscarEstadoCivilPorId((long)row.getCell(12).getNumericCellValue()));
				p.setPrsGradosAcademicos(prsServicios.buscarGradoAcademicoPorId((long)row.getCell(13).getNumericCellValue()));
				p.setPrsPaises(prsServicios.buscarPaisPorId((long)row.getCell(14).getNumericCellValue()));
				p.setPrsTiposSexos(prsServicios.buscarSexoPorId((long)row.getCell(15).getNumericCellValue()));
				p.setProgramasPostulados(Arrays.asList(prgServicios.buscarProgramaPorIdPrograma((long)row.getCell(16).getNumericCellValue())));

				pstServicios.registrarPostulantesSET(p);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "Datos leidos";
    }
}
