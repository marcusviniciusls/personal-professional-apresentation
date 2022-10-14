package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProfessionalExperienceResponse {

    private LocalDate dateInit;
    private LocalDate dateFinish;
    private String description;
    private OfficeEnum officeEnum;
    private StatusWork statusWork;
    private PartnerResponse partnerResponse;
    private OfficeResponse officeResponse;
    private List<AssignmentsResponse> listAssignmentsResponse = new ArrayList<>();

    public void addListAssignmentsResponse(AssignmentsResponse assignmentsResponse){
        this.listAssignmentsResponse.add(assignmentsResponse);
    }
}
