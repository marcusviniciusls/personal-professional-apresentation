package br.com.marcus.dev.personal.professional.apresentation.services.project.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListFramework;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ListLanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.FrameworkResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.LanguageProgrammerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.FrameworkRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.LanguageProgrammerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProjectFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private FrameworkRepository frameworkRepository;
    @Autowired private LanguageProgrammerRepository languageProgrammerRepository;

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

    public Project convertSaveFormInEntity(ProjectSaveForm projectSaveForm){
        Project project = new Project();
        project.setTitle(projectSaveForm.getTitle());
        project.setLinkYoutube(projectSaveForm.getLinkYoutube());
        project.setDescription(projectSaveForm.getDescription());
        project.setLinkGitHub(projectSaveForm.getLinkGitHub());
        for (ListFramework listFramework : projectSaveForm.getListFramework()){
            Optional<Framework> optionalFramework = frameworkRepository.findById(listFramework.getId());
            if (optionalFramework.isPresent()){
                project.addListFramework(optionalFramework.get());
            }
        }
        for (ListLanguageProgrammer listLanguageProgrammer : projectSaveForm.getListLanguageProgrammers()){
            Optional<LanguageProgrammer> optionalLanguageProgrammer = languageProgrammerRepository.findById(listLanguageProgrammer.getId());
            if (optionalLanguageProgrammer.isPresent()){
                project.addListLanguageProgrammer(optionalLanguageProgrammer.get());
            }
        }
        return project;
    }

    public Project convertUpdateFormInEntity(ProjectUpdateForm projectUpdateForm, Project project){
        if (projectUpdateForm.getTitle() != null && !projectUpdateForm.getTitle().equals("")){
            project.setTitle(projectUpdateForm.getTitle());
        }
        if (projectUpdateForm.getDescription() != null && !projectUpdateForm.getDescription().equals("")){
            project.setDescription(projectUpdateForm.getDescription());
        }
        if (projectUpdateForm.getLinkGitHub() != null && !projectUpdateForm.getLinkGitHub().equals("")){
            project.setLinkGitHub(projectUpdateForm.getLinkGitHub());
        }
        if (projectUpdateForm.getLinkYoutube() != null && !projectUpdateForm.getLinkYoutube().equals("")){
            project.setLinkYoutube(projectUpdateForm.getLinkYoutube());
        }

        List<Framework> listFrameworkNew = new ArrayList<>();
        List<LanguageProgrammer> listLanguageProgrammerNew = new ArrayList<>();
        for (ListFramework listFramework : projectUpdateForm.getListFramework()){
            Optional<Framework> optionalFramework = frameworkRepository.findById(listFramework.getId());
            if (optionalFramework.isPresent()){
                listFrameworkNew.add(optionalFramework.get());
            }
        }
        for (ListLanguageProgrammer listLanguageProgrammer : projectUpdateForm.getListLanguageProgrammers()){
            Optional<LanguageProgrammer> optionalLanguageProgrammer = languageProgrammerRepository.findById(listLanguageProgrammer.getId());
            if (optionalLanguageProgrammer.isPresent()){
                listLanguageProgrammerNew.add(optionalLanguageProgrammer.get());
            }
        }
        project.setListFramework(listFrameworkNew);
        project.setListLanguageProgrammer(listLanguageProgrammerNew);
        return project;
    }
}
