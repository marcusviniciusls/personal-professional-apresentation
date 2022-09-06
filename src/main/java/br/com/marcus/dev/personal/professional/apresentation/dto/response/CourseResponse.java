package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal duration;
    private LocalDate dateInitExpected;
    private LocalDate dateFinishExpected;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String logoImage;
    private StatusCourse statusCourse;
    private LevelCourse levelCourse;
    private List<LanguageProgrammerResponse> listLanguageProgrammerResponse = new ArrayList<>();
    private List<FrameworkResponse> listFramework = new ArrayList<>();

    public void addListLanguageProgrammerResponse(LanguageProgrammerResponse languageProgrammerResponse){
        this.listLanguageProgrammerResponse.add(languageProgrammerResponse);
    }

    public void addListFrameworkResponse(FrameworkResponse frameworkResponse){
        this.listFramework.add(frameworkResponse);
    }
}
