package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.GraduationResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private GraduationFactory graduationFactory;

    public Page<GraduationResponse> findAllGraduation(Pageable pageable){
        Page<Graduation> pageGraduation = graduationRepository.findAll(pageable);
        Page<GraduationResponse> pageGraduationResponse =
                pageGraduation.map(g -> graduationFactory.convertEntityInDto(g));
        return pageGraduationResponse;
    }
}
