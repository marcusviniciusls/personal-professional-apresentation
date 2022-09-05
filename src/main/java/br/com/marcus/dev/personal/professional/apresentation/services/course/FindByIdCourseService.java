package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.exception.custom.ResourceNotFoundException;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdCourseService {

    @Autowired private CourseRepository courseRepository;
    @Autowired private CourseFactory courseFactory;

    public CourseResponse findById(UUID id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()){
            throw new ResourceNotFoundException("COURSE NOT FOUND EXCEPTION");
        }
        if (!optionalCourse.get().isStatus()){
            throw new ResourceNotFoundException("COURSE NOT FOUND EXCEPTION");
        }
        CourseResponse courseResponse = courseFactory.convertEntityInDto(optionalCourse.get());
        return courseResponse;
    }

    public Course findByIdEntity(UUID id){
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()){
            throw new ResourceNotFoundException("COURSE NOT FOUND EXCEPTION");
        }
        if (!optionalCourse.get().isStatus()){
            throw new ResourceNotFoundException("COURSE NOT FOUND EXCEPTION");
        }
        return optionalCourse.get();
    }
}
