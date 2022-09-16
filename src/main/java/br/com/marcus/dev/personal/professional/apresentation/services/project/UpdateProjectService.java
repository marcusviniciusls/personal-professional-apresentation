package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProjectUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.project.factory.ProjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateProjectService {

    @Autowired private FindByIdProjectService findByIdProjectService;
    @Autowired private ProjectFactory projectFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProjectRepository projectRepository;

    public ProjectResponse update(UUID idProject, ProjectUpdateForm projectUpdateForm){
        Project project = findByIdProjectService.findByIdEntity(idProject);
        project = projectFactory.convertUpdateFormInEntity(projectUpdateForm, project);
        project = (Project) centerEntityService.setDataToUpdate(project);
        project = projectRepository.save(project);
        return projectFactory.convertEntityInResponse(project);
    }
}
