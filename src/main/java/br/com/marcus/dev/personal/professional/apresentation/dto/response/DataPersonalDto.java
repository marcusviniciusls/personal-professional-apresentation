package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DataPersonalDto {

    private String fullname;
    private Integer age;
    private Integer martialStatus;
    private LocalDate birthDate;
    private List<TelephoneDto> listTelephoneDto = new ArrayList<>();
    private List<EmailDto> listEmailDto = new ArrayList<>();

    public void addListTelephoneDto(TelephoneDto telephoneDto){
        this.listTelephoneDto.add(telephoneDto);
    }

    public void addListEmailDto(EmailDto emailDto){
        this.listEmailDto.add(emailDto);
    }
}
