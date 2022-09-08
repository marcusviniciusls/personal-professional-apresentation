package br.com.marcus.dev.personal.professional.apresentation.services.hardskills.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.HardSkillsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.HardSkills;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.services.languageprogrammer.factory.LanguageProgrammerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HardSkillsFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private LanguageProgrammerFactory languageProgrammerFactory;

    public HardSkillsResponse convertEntityInResponse(HardSkills hardSkills){
        HardSkillsResponse hardSkillsResponse = modelMapper.map(hardSkills, HardSkillsResponse.class);
        for (Framework framework: hardSkills.getListFramework()){
            FrameworkResponse frameworkResponse = modelMapper.map(framework, FrameworkResponse.class);
            hardSkillsResponse.addListFrameworkResponse(frameworkResponse);
        }
        for (LanguageProgrammer languageProgrammer: hardSkills.getListLanguage()){
            LanguageProgrammerResponse languageProgrammerResponse = modelMapper.map(languageProgrammer, LanguageProgrammerResponse.class);
            hardSkillsResponse.addListLanguageProgrammerResponse(languageProgrammerResponse);
        }
        return hardSkillsResponse;
    }
}
