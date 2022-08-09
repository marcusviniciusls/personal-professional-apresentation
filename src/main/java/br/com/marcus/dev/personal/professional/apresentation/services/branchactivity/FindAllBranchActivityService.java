package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import br.com.marcus.dev.personal.professional.apresentation.services.branchactivity.factory.BranchActivityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllBranchActivityService {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private BranchActivityFactory branchActivityFactory;

    public Page<BranchActivityResponse> findAll(Pageable page){
        Page<BranchActivity> listBranchActivity = branchActivityRepository.findAll(page);
        Page<BranchActivityResponse> listBranchActivityResponse = listBranchActivity.map(x -> branchActivityFactory.convertEntityInResponse(x));
        return listBranchActivityResponse;
    }
}
