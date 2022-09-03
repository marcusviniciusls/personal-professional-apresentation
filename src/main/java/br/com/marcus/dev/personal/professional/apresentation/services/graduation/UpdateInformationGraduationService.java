package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.graduation.factory.GraduationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UpdateInformationGraduationService {

    @Autowired private GraduationFactory graduationFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private GraduationRepository graduationRepository;

    public Graduation updateToSave(Graduation graduation){
        BigDecimal noteRefresh = graduationFactory.getNoteFinish(graduation.getListSubject());
        graduation.setNoteFinish(noteRefresh);
        BigDecimal qtdHours = graduationFactory.getQtdHours(graduation.getListSubject());
        graduation.setQtdHours(qtdHours);
        graduation = (Graduation) centerEntityService.setDataToUpdate(graduation);
        graduation = graduationRepository.save(graduation);
        return graduation;
    }
}
