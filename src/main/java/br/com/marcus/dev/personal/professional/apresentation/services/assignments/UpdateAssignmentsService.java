package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.FindByIdProfessionalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateAssignmentsService {

    @Autowired private FindByIdAssignmentsService findByIdAssignmentsService;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private FindByIdProfessionalExperienceService findByIdProfessionalExperienceService;

    public void update(AssignmentsUpdateForm assignmentsUpdateForm, UUID idAssignments){
        Assignments assignments = findByIdAssignmentsService.findByIdEntity(idAssignments);
        assignments = assignmentsFactory.convertUpdateFormInEntity(assignmentsUpdateForm, assignments);
        assignments = (Assignments) centerEntityService.setDataToUpdate(assignments);
        if (assignmentsUpdateForm.getIdProfessionalExperience() != null){
            if (!assignmentsUpdateForm.getIdProfessionalExperience().equals(assignments.getProfessionalExperience().getId())){
                ProfessionalExperience professionalExperience = findByIdProfessionalExperienceService.findByIdEntity(assignmentsUpdateForm.getIdProfessionalExperience());
                assignments.setProfessionalExperience(professionalExperience);
            }
        }
        assignmentsRepository.save(assignments);
    }
}
