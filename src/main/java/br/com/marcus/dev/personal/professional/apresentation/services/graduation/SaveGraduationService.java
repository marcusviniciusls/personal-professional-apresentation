package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveGraduationService {

    @Autowired private FindByIdPartnerService findByIdPartnerService;
    @Autowired private GraduationFactory graduationFactory;

    public void save(GraduationFormSave graduationFormSave){
        Partner partner = findByIdPartnerService.findByIdPartner(graduationFormSave.getPartnerId());
        Graduation graduation = graduationFactory.convertFormSaveToEntity(graduationFormSave);
        graduation.setPartner(partner);
    }
}
