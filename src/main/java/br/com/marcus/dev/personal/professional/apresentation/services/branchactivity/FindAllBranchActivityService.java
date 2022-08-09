package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FindAllBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;

    public ResponseEntity<BranchActivityResponse> findAll(){
        branchActivityRepository.findAll()
    }
}
