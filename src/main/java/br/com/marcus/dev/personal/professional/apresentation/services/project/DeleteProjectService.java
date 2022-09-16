package br.com.marcus.dev.personal.professional.apresentation.services.project;

import br.com.marcus.dev.personal.professional.apresentation.entities.Project;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteProjectService {

    @Autowired private ProjectRepository projectRepository;
    @Autowired private FindByIdProjectService findByIdProjectService;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Project project = findByIdProjectService.findByIdEntity(id);
        try {
            projectRepository.deleteById(id);
        } catch(DataIntegrityViolationException ex){
            project = (Project) centerEntityService.setDataToDelete(project);
            projectRepository.save(project);
        }
    }
}
