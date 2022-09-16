package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.config.amazon.SendFileAwsS3;
import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.FileException;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindByIdCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SaveImageCourseService {

    @Autowired private FindByIdCourseService findByIdCourseService;
    @Autowired private SendFileAwsS3 sendFileAwsS3;
    @Autowired private CourseRepository courseRepository;

    public void saveImageCourse(MultipartFile multipartFile, UUID idCourse){
        Course course = findByIdCourseService.findByIdEntity(idCourse);
        if (course.getLogoImage() != null && !course.getLogoImage().equals("")){
            throw new FileException("RESOURCE IS ALREADY SAVED");
        }
        String url = sendFileAwsS3.uploadFile(multipartFile).toString();
        course.setLogoImage(url);
        courseRepository.save(course);
    }
}
