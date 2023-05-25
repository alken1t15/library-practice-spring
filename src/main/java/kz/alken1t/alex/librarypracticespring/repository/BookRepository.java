package kz.alken1t.alex.librarypracticespring.repository;

import kz.alken1t.alex.librarypracticespring.entity.Book;
import kz.alken1t.alex.librarypracticespring.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

}