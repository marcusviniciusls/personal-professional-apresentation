package br.com.marcus.dev.personal.professional.apresentation.services.certificate;

import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.activities.DeleteActivitiesService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCertificateService {

    @Autowired private FindByIdCertificateService findByIdCertificateService;
    @Autowired private CertificateRepository certificateRepository;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private DeleteActivitiesService deleteActivitiesService;

    public void delete(UUID id){
        Certificate certificate = findByIdCertificateService.findByIdEntity(id);
        deleteActivitiesService.deleteMovementCertificate(certificate.getId());
        try{
            certificateRepository.delete(certificate);
        } catch(DataIntegrityViolationException ex){
            certificate = (Certificate) centerEntityService.setDataToDelete(certificate);
            certificateRepository.save(certificate);
        }
    }
}