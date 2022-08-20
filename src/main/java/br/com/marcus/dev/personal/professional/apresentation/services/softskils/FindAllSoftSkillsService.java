package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class FindAllSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private SoftSkillsFactory softSkillsFactory;

    public Page<SoftSkillsResponse> findAll(Pageable pageable){
        Page<SoftSkills> softSkillsPage = softSkillsRepository.findAll(pageable);
        Page<SoftSkillsResponse> pageSoftSkillsResponse =
                softSkillsPage.stream().filter(x -> x.isStatusHas() == true)
                        .map(x -> softSkillsFactory.convertEntityInDto(x)).collect(Collectors.toSet());
        return pageSoftSkillsResponse;
    }
}
