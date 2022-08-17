package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DataPersonalFullForm {

    @NotBlank(message = "FullName cannot be blank")
    private String fullName;
    @NotBlank(message = "BirthDate cannot be blank")
    private String birthDate;
    private Integer maritalStatus;
    private List<TelephoneForm> listTelephoneForm = new ArrayList<>();
    private List<EmailForm> listEmailForm = new ArrayList<>();
}
