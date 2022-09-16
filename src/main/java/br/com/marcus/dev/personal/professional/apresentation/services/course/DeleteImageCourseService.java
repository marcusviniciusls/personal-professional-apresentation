package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.entities.Certificate;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.repository.CertificateRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.certificate.FindByIdCertificateService;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import br.com.marcus.dev.personal.professional.apresentation.services.s3.DeleteFileService;
import br.com.marcus.dev.personal.professional.apresentation.services.utils.GetKeyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteImageCourseService {

    @Autowired private FindByIdCourseService findByIdCourseService;
    @Autowired private GetKeyFile getKeyFile;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private CourseRepository courseRepository;
    @Autowired private DeleteFileService deleteFileService;

    public void deleteImageS3(UUID id){
        Course course = findByIdCourseService.findByIdEntity(id);
        String urlImage = course.getLogoImage();
        String keyName = getKeyFile.getKeyFile(urlImage);
        course.setLogoImage("");
        course = (Course) centerEntityService.setDataToUpdate(course);
        courseRepository.save(course);
        deleteFileService.deleteObjectS3(keyName);
    }
}
