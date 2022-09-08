package br.com.marcus.dev.personal.professional.apresentation.services.hardskills;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.HardSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory.HardSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllHardSkillsService {

    @Autowired private HardSkillsRepository hardSkillsRepository;
    @Autowired private HardSkillsFactory hardSkillsFactory;

    public Page<HardSkillsResponse> findAll(Pageable pageable){
        Page<HardSkills> pageHardSkills = hardSkillsRepository.findAll(pageable);
        Page<HardSkillsResponse> pageHardSkillsResponse = pageHardSkills.map(hs -> hardSkillsFactory.convertEntityInResponse(hs));
        return pageHardSkillsResponse;
    }
}
