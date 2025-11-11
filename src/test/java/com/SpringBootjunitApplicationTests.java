package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uap.usic.siga.dto.PostulantesResponse;
import uap.usic.siga.entidadesPg.Postulantes;
import uap.usic.siga.servicios.PostulantesServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SpringBootjunitApplicationTests {
    @Autowired PostulantesServicios pstServicios;

    @Test
    public void testCreate(){
    }
}
