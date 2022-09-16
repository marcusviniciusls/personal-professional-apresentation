package br.com.marcus.dev.personal.professional.apresentation.services.subject;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.SubjectRepository;
import com.amazonaws.AmazonServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImageSubjectService {

    @Autowired private SubjectRepository subjectRepository;
    @Autowired private SendFileAwsS3 sendFileAwsS3;

    public void saveimage(MultipartFile multipartFile, UUID idSubject){
        Subject subject = subjectRepository.findById(idSubject).get();
        if (subject.getUrlImage() != null){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String urlImage = sendFileAwsS3.uploadFile(multipartFile).toString();
        subject.setUrlImage(urlImage);
        subjectRepository.save(subject);
    }
}
