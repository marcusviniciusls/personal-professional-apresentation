package br.com.marcus.dev.personal.professional.apresentation.services.framework;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.framework.factory.FrameworkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllFrameworkService {

    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private FrameworkFactory frameworkFactory;

    public Page<FrameworkResponse> findAll(Pageable pageable){
        Page<Framework> pageFramework = frameworkRepository.findAll(pageable);
        Page<FrameworkResponse> pageFrameworkResponse = pageFramework.map(x -> frameworkFactory.convertEntityInDto(x));
        return pageFrameworkResponse;
    }
}
