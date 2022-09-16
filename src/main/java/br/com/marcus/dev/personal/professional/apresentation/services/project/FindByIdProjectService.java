package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.project.factory.ProjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdProjectService {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private ProjectFactory projectFactory;

    public ProjectResponse findById(UUID id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()){
            throw new ResourceNotFoundException("Project Not Found Exception");
        }
        Project project = optionalProject.get();
        if (!centerEntityService.isStatusSuperEntity(project)){
            throw new ResourceNotFoundException("Project Not Found Exception");
        }
        return projectFactory.convertEntityInResponse(project);
    }

    public Project findByIdEntity(UUID id){
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()){
            throw new ResourceNotFoundException("Project Not Found Exception");
        }
        Project project = optionalProject.get();
        if (!centerEntityService.isStatusSuperEntity(project)){
            throw new ResourceNotFoundException("Project Not Found Exception");
        }
        return project;
    }
}
