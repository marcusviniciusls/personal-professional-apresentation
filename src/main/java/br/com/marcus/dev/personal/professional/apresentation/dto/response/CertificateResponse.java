package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CertificateResponse {

    private String name;
    private String logoImage;
    private PartnerResponse partnerResponse;
}
