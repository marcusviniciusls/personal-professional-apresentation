package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.dto.request.GraduationFormSave;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.partner.FindByIdPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveGraduationService {

    @Autowired private GraduationFactory graduationFactory;
    @Autowired private GraduationRepository graduationRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void save(GraduationFormSave graduationFormSave){
        Graduation graduation = graduationFactory.convertFormSaveToEntity(graduationFormSave);

    }
}
