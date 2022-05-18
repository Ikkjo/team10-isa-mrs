package team10.app.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import team10.app.model.Address;
import team10.app.model.VacationHomeOwner;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class VacationHomeOwnerRepositoryTest {

    @Autowired
    private VacationHomeOwnerRepository repo;

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    void itShouldReturnUserByEmail() {
        String email = "jackj@gmail.com";
        VacationHomeOwner user = new VacationHomeOwner(
                "Jack",
                "Jackson",
                email,
                "password123",
                "+381651726152", new Address("123", "123", "123"), "12.12.1999.");
        repo.save(user);

        Optional<VacationHomeOwner> expected = repo.findByEmail(email);
        assertThat(expected).isEqualTo(Optional.of(user));
    }

    @Test
    void itShouldNotReturnUserByEmail() {
        Optional<VacationHomeOwner> expected = repo.findByEmail("test@gmail.com");
        assertThat(expected).isEmpty();
    }

    @Test
    void itShouldEnableVacationHomeOwner() {
        String email = "jackj@gmail.com";
        VacationHomeOwner user = new VacationHomeOwner(
                "Jack",
                "Jackson",
                email,
                "password123",
                "+381651726152", new Address("123", "123", "123"), "12.12.1999.");
        repo.save(user);

        assertThat(repo.enableVacationHomeOwner(email)).isEqualTo(1);
    }

    @Test
    void itShouldNotEnableVacationHomeOwner() {
        assertThat(repo.enableVacationHomeOwner("test@gmail.com")).isNotEqualTo(1);
    }

}