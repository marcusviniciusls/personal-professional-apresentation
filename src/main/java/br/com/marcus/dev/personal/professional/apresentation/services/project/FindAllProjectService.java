package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProjectResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.project.factory.ProjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllProjectService {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private ProjectFactory projectFactory;

    public Page<ProjectResponse> findAll(Pageable pageable){
        Page<Project> pageProject = projectRepository.findAll(pageable);
        Page<ProjectResponse> pageProjectResponse = pageProject.map(p -> projectFactory.convertEntityInResponse(p));
        return pageProjectResponse;
    }
}
