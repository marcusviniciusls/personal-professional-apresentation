package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.TelephoneDto;
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

    @NotBlank(message = "Name cannot be blank")
    private String fullName;
    @NotBlank(message = "Name cannot be blank")
    @Min(18)
    @Max(100)
    private Byte age;
    @NotBlank(message = "Name cannot be blank")
    private LocalDate birthDate;
    @NotBlank(message = "Name cannot be blank")
    private List<TelephoneForm> listTelephoneForm = new ArrayList<>();
    @NotBlank(message = "Name cannot be blank")
    private List<EmailForm> listEmailForm = new ArrayList<>();
}
