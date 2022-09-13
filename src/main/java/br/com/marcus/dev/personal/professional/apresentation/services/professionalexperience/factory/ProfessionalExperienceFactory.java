package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.SaveAssignmentsService;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalExperienceFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private SaveAssignmentsService saveAssignmentsService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private OfficeFactory officeFactory;
    @Autowired private PartnerFactory partnerFactory;

    public ProfessionalExperience convertSaveFormInEntity(ProfessionalExperienceFormSave request){
        ProfessionalExperience professionalExperience = new ProfessionalExperience();
        professionalExperience.setDateInit(request.getDateInit());
        professionalExperience.setDateFinish(request.getDateFinish());
        professionalExperience.setDescription(request.getDescription());
        professionalExperience.setOfficeEnum(OfficeEnum.toEnum(request.getOfficeEnum()));

        return professionalExperience;
    }

    public ProfessionalExperienceResponse convertEntityInResponse(ProfessionalExperience entity){
        OfficeResponse officeResponse = officeFactory.convertEntityInResponse(entity.getOffice());
        PartnerResponse partnerResponse = partnerFactory.convertEntityInDto(entity.getPartner());
        ProfessionalExperienceResponse professionalExperienceResponse = new ProfessionalExperienceResponse();
        professionalExperienceResponse.setDateInit(entity.getDateInit());
        professionalExperienceResponse.setDateFinish(entity.getDateFinish());
        professionalExperienceResponse.setDescription(entity.getDescription());
        professionalExperienceResponse.setStatusWork(entity.getStatusWork());
        professionalExperienceResponse.setOfficeEnum(entity.getOfficeEnum());
        professionalExperienceResponse.setOfficeResponse(officeResponse);
        professionalExperienceResponse.setPartnerResponse(partnerResponse);

        return professionalExperienceResponse;
    }
}
