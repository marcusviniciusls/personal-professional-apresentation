package br.com.marcus.dev.personal.professional.apresentation.services.professionalexperience.factory;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsProfessionalUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.AssignmentsSaveForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormSave;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.ProfessionalExperienceFormUpdate;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.AssignmentsResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.OfficeResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.PartnerResponse;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.ProfessionalExperienceResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.repository.AssignmentsRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.SaveAssignmentsService;
import br.com.marcus.dev.personal.professional.apresentation.services.assignments.factory.AssignmentsFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.FindByIdOfficeService;
import br.com.marcus.dev.personal.professional.apresentation.services.office.factory.OfficeFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.factory.PartnerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessionalExperienceFactory {

    @Autowired private ModelMapper modelMapper;
    @Autowired private AssignmentsFactory assignmentsFactory;
    @Autowired private SaveAssignmentsService saveAssignmentsService;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private AssignmentsRepository assignmentsRepository;
    @Autowired private OfficeFactory officeFactory;
    @Autowired private PartnerFactory partnerFactory;
    @Autowired private FindByIdOfficeService findByIdOfficeService;
    @Autowired private FindByIdPartnerService findByIdPartnerService;

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
        for (Assignments assignments : entity.getListAssignments()){
            AssignmentsResponse assignmentsResponse = assignmentsFactory.convertEntityInResponse(assignments);
            professionalExperienceResponse.addListAssignmentsResponse(assignmentsResponse);
        }

        return professionalExperienceResponse;
    }

    public ProfessionalExperience convertUpdateFormInEntity(ProfessionalExperience entity, ProfessionalExperienceFormUpdate update){
        if (update.getDateInit() != null){
            entity.setDateInit(update.getDateInit());
        }
        if (update.getDateFinish() != null){
            entity.setDateFinish(update.getDateFinish());
        }
        if (update.getDescription() != null && !update.getDescription().equals("")){
            entity.setDescription(update.getDescription());
        }
        if (!entity.getOffice().getId().equals(update.getOfficeId())){
            Office office = findByIdOfficeService.findByIdEntity(update.getOfficeId());
            entity.setOffice(office);
        }
        if (entity.getPartner().getId().equals(update.getPartnerId())){
            Partner partner = findByIdPartnerService.findByIdPartner(update.getPartnerId());
            entity.setPartner(partner);
        }
        if (update.getOfficeEnum() != null){
            entity.setOfficeEnum(OfficeEnum.toEnum(update.getOfficeEnum()));
        }

        List<Assignments> listAssignments = new ArrayList<>();
        if (update.getListAssignments().size() > 0){
            for (AssignmentsProfessionalUpdateForm assignmentsResponse : update.getListAssignments()){
                Assignments assignments = new Assignments();
                assignments.setDescription(assignmentsResponse.getDescription());
                listAssignments.add(assignments);
            }
        }
        entity.setListAssignments(listAssignments);

        return entity;
    }
}
