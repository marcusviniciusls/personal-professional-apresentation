package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseService {

    @Autowired private FindByIdCourseService findByIdCourseService;
    @Autowired private CourseRepository courseRepository;
    @Autowired private CenterEntityService centerEntityService;

    public void delete(UUID id){
        Course course = findByIdCourseService.findByIdEntity(id);
        try{
            courseRepository.delete(course);
        } catch(DataIntegrityViolationException ex){
            course = (Course) centerEntityService.setDataToDelete(course);
            courseRepository.save(course);
        }
    }
}
