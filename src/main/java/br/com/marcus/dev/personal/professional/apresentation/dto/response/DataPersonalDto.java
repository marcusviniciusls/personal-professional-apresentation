package br.com.marcus.dev.personal.professional.apresentation.dto.response;

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
    private String martialStatus;
    private LocalDate birthDate;
    private List<TelephoneDto> listTelephoneDto = new ArrayList<>();
    private List<EmailDto> listEmailDto = new ArrayList<>();

    public DataPersonalDto(String fullname, Integer age, String martialStatus, LocalDate birthDate) {
        this.fullname = fullname;
        this.age = age;
        this.martialStatus = martialStatus;
        this.birthDate = birthDate;
    }

    public void addListTelephoneDto(TelephoneDto telephoneDto){
        this.listTelephoneDto.add(telephoneDto);
    }

    public void addListEmailDto(EmailDto emailDto){
        this.listEmailDto.add(emailDto);
    }
}
