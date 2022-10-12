package br.com.marcus.dev.personal.professional.apresentation.services.generalrule;

import br.com.marcus.dev.personal.professional.apresentation.config.security.UserSS;
import br.com.marcus.dev.personal.professional.apresentation.config.security.UserService;
import br.com.marcus.dev.personal.professional.apresentation.entities.SuperEntity;
import br.com.marcus.dev.personal.professional.apresentation.entities.User;
import br.com.marcus.dev.personal.professional.apresentation.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CenterEntityServiceTest {

    @Autowired private CenterEntityService centerEntityService;
    @MockBean private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
    @DisplayName("Setar para Salvar")
    public void setDataToSaveTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        LocalDateTime date = LocalDateTime.of(2022, 10, 10, 0, 0);
        User user = new User(id, date, date, "Marcus Create", "Marcus Refresh", true
                , "Marcus Vinicius", "marcus@gmail.com", "senha");
        userRepository.save(user);
        UserSS userSS = new UserSS(id, "Marcus", "senha");
        BDDMockito.given(userService.authenticated()).willReturn(userSS);
        SuperEntity superEntity = new SuperEntity(id, date, date, date, "userCreation", "userUpdate", true
        , user);
        // Executar método
        SuperEntity verify = centerEntityService.setDataToSave(superEntity);
        // Testes Unitários
        Assertions.assertTrue(verify != null);
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"), verify.getId());
        Assertions.assertEquals(date, verify.getDateIssue());
        Assertions.assertTrue(verify.getDateCreation() != null);
        Assertions.assertEquals(date, verify.getDateUpdate());
        Assertions.assertEquals("Marcus Vinicius", verify.getUserCreation());
        Assertions.assertEquals("userUpdate", verify.getUserUpdate());
        Assertions.assertEquals(true, verify.isStatus());
        Assertions.assertTrue(verify.getUser() != null);
    }

    @Test
    @DisplayName("Setar para Atualizar")
    public void setDataToUpdateTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        LocalDateTime date = LocalDateTime.of(2022, 10, 10, 0, 0);
        User user = new User(id, date, date, "Marcus Create", "Marcus Refresh", true
                , "Marcus Vinicius", "marcus@gmail.com", "senha");
        userRepository.save(user);
        UserSS userSS = new UserSS(id, "Marcus", "senha");
        BDDMockito.given(userService.authenticated()).willReturn(userSS);
        SuperEntity superEntity = new SuperEntity(id, date, date, date, "userCreation", "userUpdate", true
                , user);
        // Executar método
        SuperEntity verify = centerEntityService.setDataToUpdate(superEntity);
        // Testes Unitários
        Assertions.assertTrue(verify != null);
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"), verify.getId());
        Assertions.assertEquals(date, verify.getDateIssue());
        Assertions.assertTrue(verify.getDateUpdate() != null);
        Assertions.assertEquals("Marcus Vinicius", verify.getUserUpdate());
        Assertions.assertEquals(true, verify.isStatus());
        Assertions.assertTrue(verify.getUser() != null);
    }

    @Test
    @DisplayName("Setar para Apagar")
    public void setDataToDeleteTest(){
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        LocalDateTime date = LocalDateTime.of(2022, 10, 10, 0, 0);
        User user = new User(id, date, date, "Marcus Create", "Marcus Refresh", true
                , "Marcus Vinicius", "marcus@gmail.com", "senha");
        userRepository.save(user);
        UserSS userSS = new UserSS(id, "Marcus", "senha");
        BDDMockito.given(userService.authenticated()).willReturn(userSS);
        SuperEntity superEntity = new SuperEntity(id, date, date, date, "userCreation", "userUpdate", true
                , user);
        // Executar método
        SuperEntity verify = centerEntityService.setDataToDelete(superEntity);
        // Testes Unitários
        Assertions.assertTrue(verify != null);
        Assertions.assertEquals(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301"), verify.getId());
        Assertions.assertEquals(date, verify.getDateIssue());
        Assertions.assertTrue(verify.getDateUpdate() != null);
        Assertions.assertEquals("Marcus Vinicius", verify.getUserUpdate());
        Assertions.assertEquals(false, verify.isStatus());
        Assertions.assertTrue(verify.getUser() != null);
    }

    @Test
    @DisplayName("Testar o Status")
    public void isStatusSuperEntityTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        LocalDateTime date = LocalDateTime.of(2022, 10, 10, 0, 0);
        User user = new User(id, date, date, "Marcus Create", "Marcus Refresh", true
                , "Marcus Vinicius", "marcus@gmail.com", "senha");
        SuperEntity superEntity = new SuperEntity(id, date, date, date, "userCreation", "userUpdate", true
                , user);

        // Testar método
        boolean verify = centerEntityService.isStatusSuperEntity(superEntity);

        // Teste Unitário
        Assertions.assertTrue(verify);
    }

    @Test
    @DisplayName("Usuário logado")
    public void userLoggedTest(){
        // Dados de Entrada
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff301");
        LocalDateTime date = LocalDateTime.of(2022, 10, 10, 0, 0);
        User user = new User(id, date, date, "Marcus Create", "Marcus Refresh", true
                , "Marcus Vinicius", "marcus@gmail.com", "senha");
        userRepository.save(user);
        UserSS userSS = new UserSS(id, "Marcus", "senha");
        BDDMockito.given(userService.authenticated()).willReturn(userSS);

        // Testar método
        User userLogado = centerEntityService.userLogged();

        // Teste Unitário
        Assertions.assertEquals("Marcus Vinicius", userLogado.getName());
        Assertions.assertEquals("senha", userLogado.getPassword());
    }
}
