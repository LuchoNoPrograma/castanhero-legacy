package uap.usic.siga.servicios.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uap.usic.siga.entidades.InsSedes;
import uap.usic.siga.modelos.InsSedesRepository;
import uap.usic.siga.servicios.InsSedesService;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class InsSedesServiceImpl implements InsSedesService{
    
     
    private final InsSedesRepository insSedesRepository;

    @Override
    public boolean updateInsSedes(InsSedes ins) {
        return insSedesRepository.update(ins);
    }

    @Override
    public InsSedes getInsSedes(Long id) {
        return insSedesRepository.findOne(id);
    }

}
