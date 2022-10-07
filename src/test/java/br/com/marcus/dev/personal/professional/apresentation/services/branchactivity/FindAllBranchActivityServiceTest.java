package br.com.marcus.dev.personal.professional.apresentation.services.branchactivity;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.BranchActivityResponse;
import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import br.com.marcus.dev.personal.professional.apresentation.repository.BranchActivityRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class FindAllBranchActivityServiceTest {

    @Autowired private BranchActivityRepository branchActivityRepository;
    @Autowired private FindAllBranchActivityService findAllBranchActivityService;

    @BeforeEach
    public void setupInit(){
        BranchActivity branchActivity1 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"), "Name1");
        BranchActivity branchActivity2 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff716"), "Name2");
        BranchActivity branchActivity3 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff717"), "Name3");
        BranchActivity branchActivity4 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff718"), "Name4");
        BranchActivity branchActivity5 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff719"), "Name5");
        BranchActivity branchActivity6 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff710"), "Name6");
        BranchActivity branchActivity7 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff711"), "Name7");
        BranchActivity branchActivity8 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff712"), "Name8");
        BranchActivity branchActivity9 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff713"), "Name9");
        BranchActivity branchActivity10 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff714"), "Name10");
        BranchActivity branchActivity11 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"), "Name11");
        BranchActivity branchActivity12 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"), "Name12");
        BranchActivity branchActivity13 = new BranchActivity(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff715"), "Name13");
        branchActivity1.setStatus(false);
        branchActivity2.setStatus(false);
        branchActivity3.setStatus(false);
        branchActivityRepository.saveAll(Arrays.asList(branchActivity1, branchActivity2, branchActivity3, branchActivity4
        , branchActivity5, branchActivity6, branchActivity7, branchActivity8, branchActivity9, branchActivity10
        , branchActivity11, branchActivity12, branchActivity13));
    }

    @Test
    @DisplayName("Buscar todos as Branch Activity - Total 10")
    public void findAllTest(){
        Page<BranchActivityResponse> page = findAllBranchActivityService.findAll(PageRequest.of(1, 10));
        Assertions.assertEquals(10, page.getSize());
    }
}
