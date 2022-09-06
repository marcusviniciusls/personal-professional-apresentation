package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.request.CourseUpdateForm;
import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import br.com.marcus.dev.personal.professional.apresentation.services.generalrule.CenterEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateCourseService {

    @Autowired private CourseFactory courseFactory;
    @Autowired private CenterEntityService centerEntityService;
    @Autowired private FindByIdCourseService findByIdCourseService;
    @Autowired private CourseRepository courseRepository;

    public CourseResponse update(CourseUpdateForm courseUpdateForm, UUID id){
        Course course = findByIdCourseService.findByIdEntity(id);
        course = courseFactory.convertFormUpdateInEntity(courseUpdateForm, course);
        course = (Course) centerEntityService.setDataToUpdate(course);
        course = courseRepository.save(course);
        CourseResponse courseResponse = courseFactory.convertEntityInDto(course);
        return courseResponse;
    }
}
