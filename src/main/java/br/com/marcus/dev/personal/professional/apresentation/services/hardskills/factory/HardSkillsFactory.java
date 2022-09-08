package br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HardSkillsFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    public HardSkillsResponse convertEntityInResponse(HardSkills hardSkills){
        HardSkillsResponse hardSkillsResponse = modelMapper.map(hardSkills, HardSkillsResponse.class);
        LanguageProgrammerResponse languageProgrammerResponse = modelMapper.map(hardSkills.getLanguage(), LanguageProgrammerResponse.class);
        FrameworkResponse frameworkResponse = modelMapper.map(hardSkills.getFramework(), FrameworkResponse.class);
        hardSkillsResponse.setFrameworkResponse(frameworkResponse);
        hardSkillsResponse.setLanguageProgrammerResponse(languageProgrammerResponse);
        return hardSkillsResponse;
    }
}
