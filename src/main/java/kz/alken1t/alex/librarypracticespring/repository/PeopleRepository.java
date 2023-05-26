package kz.alken1t.alex.librarypracticespring.repository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import kz.alken1t.alex.librarypracticespring.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<People,Long> {

    @Transactional
    @Modifying
    @Query("update People p set p.LNM = ?1, p.dateBorn = ?2 where p.id = ?3")
    void updatePeople(String LMN, Integer dateBorn, Long id);

    Optional<People> findByLNM(@NotEmpty(message = "Поле должно быть заполнено") @Pattern(regexp = "^([А-ЯЁ][а-яё]+)\\s+([А-ЯЁ][а-яё]+)\\s+([А-ЯЁ][а-яё]+)$", message = "Ваше ФИО должно соответствовать данному формату: Ф И О") String LNM);
}