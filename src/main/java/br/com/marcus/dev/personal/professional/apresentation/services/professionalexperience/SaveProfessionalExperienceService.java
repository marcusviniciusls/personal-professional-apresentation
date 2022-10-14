package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.CurrentJobException;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ErrorDateAfterNowProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.repository.ProfessionalExperienceRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.SaveActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.SaveAssignmentsService;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindByIdOfficeService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory.ProfessionalExperienceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaveProfessionalExperienceService {

    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private FindByIdOfficeService findByIdOfficeService;
    @Autowired private ProfessionalExperienceFactory professionalExperienceFactory;
    @Autowired private ProfessionalExperienceRepository professionalExperienceRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private SaveAssignmentsService saveAssignmentsService;
    @Autowired private SaveActivitiesService saveActivitiesService;

    public ProfessionalExperienceResponse save(ProfessionalExperienceFormSave requestSave){
        dateFinishBeforeNotAllowed(requestSave.getDateFinish());
        ProfessionalExperience professionalExperience = mountObject(requestSave);
        if (verifyStatusJob() && requestSave.getDateFinish() == null){
            throw new CurrentJobException("There is a current job");
        }
        int statusWork = professionalExperience.getDateFinish() == null ? 0 : 1;
        professionalExperience.setStatusWork(StatusWork.toEnum(statusWork));
        professionalExperience = (ProfessionalExperience) centerEntityService.setDataToSave(professionalExperience);
        professionalExperience = professionalExperienceRepository.save(professionalExperience);
        ProfessionalExperienceResponse response = professionalExperienceFactory.convertEntityInResponse(professionalExperience);
        for (AssignmentsSaveForm assignmentsSaveForm : requestSave.getListAssignmentsSaveForm()){
            Assignments assignments = assignmentsFactory.convertSaveFormInEntity(assignmentsSaveForm);
            assignments.setProfessionalExperience(professionalExperience);
            assignments = saveAssignmentsService.save(assignments);
            AssignmentsResponse assignmentsResponse = assignmentsFactory.convertEntityInResponse(assignments);
            response.addListAssignmentsResponse(assignmentsResponse);
        }
        saveActivitiesService.saveMovementProfessionalExperience(professionalExperience);

        return response;
    }

    public ProfessionalExperience mountObject(ProfessionalExperienceFormSave requestSave){
        Partner partner = findByIdPartnerService.findByIdPartner(requestSave.getPartnerId());
        Office office = findByIdOfficeService.findByIdEntity(requestSave.getOfficeId());
        ProfessionalExperience professionalExperience = professionalExperienceFactory.convertSaveFormInEntity(requestSave);
        professionalExperience.setPartner(partner);
        professionalExperience.setOffice(office);

        return professionalExperience;
    }

    public boolean verifyStatusJob(){
        List<ProfessionalExperience> listProfessionalExperience = professionalExperienceRepository.findAll();
        return listProfessionalExperience.size() > 0 ? true : false;
    }

    public void dateFinishBeforeNotAllowed(LocalDate date){
        if (date.isAfter(LocalDate.now())){
            throw new ErrorDateAfterNowProfessionalExperience("ERROR DATE FINISH AFTER NOW");
        }
    }
}
