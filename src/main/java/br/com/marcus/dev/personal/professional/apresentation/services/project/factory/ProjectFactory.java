package br.com.marcus.dev.personal.professional.apresentation.services.project.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectFactory {

    @Autowired private ModelMapper modelMapper;

    public ProjectResponse convertEntityInResponse(Project project){
        ProjectResponse projectResponse = modelMapper.map(project, ProjectResponse.class);
        for (LanguageProgrammer languageProgrammer : project.getListLanguageProgrammer()){
            LanguageProgrammerResponse languageProgrammerResponse = modelMapper.map(languageProgrammer, LanguageProgrammerResponse.class);
            projectResponse.addListLanguageProgrammerResponse(languageProgrammerResponse);
        }
        for (Framework framework : project.getListFramework()){
            FrameworkResponse frameworkResponse = modelMapper.map(framework, FrameworkResponse.class);
            projectResponse.addListFrameworkResponse(frameworkResponse);
        }
        return projectResponse;
    }
}
