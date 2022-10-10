package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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

    public DataPersonalFullForm(String fullName, String birthDate, Integer maritalStatus) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
    }

    public void addListTelephoneForm(TelephoneForm telephoneForm){
        this.listTelephoneForm.add(telephoneForm);
    }

    public void addListEmailForm(EmailForm emailForm){
        this.listEmailForm.add(emailForm);
    }
}
