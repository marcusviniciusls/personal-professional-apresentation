package br.com.marcus.dev.personal.professional.apresentation.services.assignments;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveAddForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.FindByIdProfessionalExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAssignmentsService {

    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;

    public Assignments save(Assignments assignments){
        assignments = (Assignments) centerEntityService.setDataToSave(assignments);
        return assignmentsRepository.save(assignments);
    }

    public AssignmentsResponse addSave(AssignmentsSaveAddForm assignmentsSaveAddForm){
        Assignments assignments = assignmentsFactory.convertSaveFormAddInEntity(assignmentsSaveAddForm);
        ProfessionalExperience professionalExperience = professionalExperienceRepository.findById(assignmentsSaveAddForm.getIdProfessionalExperience()).get();
        assignments.setProfessionalExperience(professionalExperience);
        assignments = assignmentsRepository.save(assignments);
        return assignmentsFactory.convertEntityInResponse(assignments);
    }
}
