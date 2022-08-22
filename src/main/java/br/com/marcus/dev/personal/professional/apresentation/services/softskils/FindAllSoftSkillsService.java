package br.com.marcus.dev.personal.professional.apresentation.services.softskils;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.SoftSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import br.com.marcus.dev.personal.professional.apresentation.repository.SoftSkillsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.softskils.factory.SoftSkillsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindAllSoftSkillsService {

    @Autowired private SoftSkillsRepository softSkillsRepository;
    @Autowired private SoftSkillsFactory softSkillsFactory;

    public Page<SoftSkillsResponse> findAll(Pageable pageable){
        Page<SoftSkills> softSkillsPage = softSkillsRepository.findAll(pageable);
        List<SoftSkillsResponse> listSoftSkillsResponse = new ArrayList<>();
        for(SoftSkills softSkills : softSkillsPage.getContent()){
            if (softSkills.isStatusHas()){
                SoftSkillsResponse softSkillsResponse = softSkillsFactory.convertEntityInDto(softSkills);
                listSoftSkillsResponse.add(softSkillsResponse);
            }
        }
        Page<SoftSkillsResponse> pageSoftSkillsResponse =
                new PageImpl<SoftSkillsResponse>(listSoftSkillsResponse, pageable, (pageable.getOffset() + pageable.getOffset()));
        return pageSoftSkillsResponse;
    }
}
