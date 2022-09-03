package br.com.marcus.dev.personal.professional.apresentation.services.course;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.CourseResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.Course;
import br.com.marcus.dev.personal.professional.apresentation.repository.CourseRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.course.factory.CourseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllCourseService {

    @Autowired private CourseRepository courseRepository;
    @Autowired private CourseFactory courseFactory;

    public Page<CourseResponse> findAll(Pageable pageable){
        Page<Course> pageCourse = courseRepository.findAll(pageable);
        Page<CourseResponse> pageCourseResponse = pageCourse.map(course -> courseFactory.convertEntityInDto(course));
        return pageCourseResponse;
    }
}
