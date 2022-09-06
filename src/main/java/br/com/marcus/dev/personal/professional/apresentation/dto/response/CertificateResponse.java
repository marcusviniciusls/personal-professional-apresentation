package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CertificateResponse {

    private UUID id;
    private String name;
    private String logoImage;
    private PartnerResponse partnerResponse;
}
