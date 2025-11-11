package com.kemal.spring;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.entidadesPg.Programas;
import uap.usic.siga.servicios.PostulantesServicios;
import uap.usic.siga.servicios.ProgramasService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SpringThymleafJpaApplicationTests {
	@Autowired
    private PostulantesServicios postulantesServicios;

    @Autowired
    private ProgramasService programasService;

	
}
