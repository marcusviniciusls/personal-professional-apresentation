package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.project.factory.ProjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveProjectService {

    @Autowired private ProjectFactory projectFactory;
    @Autowired private ProjectRepository projectRepository;
    @Autowired private CenterEntityService centerEntityService;

    public ProjectResponse save(ProjectSaveForm projectSaveForm){
        Project project = projectFactory.convertSaveFormInEntity(projectSaveForm);
        project = (Project) centerEntityService.setDataToSave(project);
        project = projectRepository.save(project);
        return projectFactory.convertEntityInResponse(project);
    }
}
