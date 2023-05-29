package kz.alken1t.alex.librarypracticespring.repository;

import jakarta.validation.constraints.NotEmpty;
import kz.alken1t.alex.librarypracticespring.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByNameStartingWith(@NotEmpty(message = "Поле должно быть заполнено") String name);

}