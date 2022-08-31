package br.com.marcus.dev.personal.professional.apresentation.services.graduation;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.repository.GraduationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImageGraduationService {

    @Autowired private GraduationRepository graduationRepository;
    @Autowired private SendFileAwsS3 sendFileAwsS3;

    public void saveimage(MultipartFile multipartFile, UUID idGraduation){
        Graduation graduation = graduationRepository.findById(idGraduation).get();
        String urlImage = sendFileAwsS3.uploadFile(multipartFile).toString();
        graduation.setUrlUniversityDegree(urlImage);
        graduationRepository.save(graduation);
    }
}
