package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PartnerResponse {

    private UUID id;
    private String name;
    private String urlImage;
    private BranchActivityResponse branchActivityResponse;
    private String description;
}
