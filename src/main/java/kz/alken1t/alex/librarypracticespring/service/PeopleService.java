package kz.alken1t.alex.librarypracticespring.service;

import kz.alken1t.alex.librarypracticespring.entity.Book;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.BookRepository;
import kz.alken1t.alex.librarypracticespring.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
public class PeopleService {

    private final PeopleRepository peopleRepository;

    private final BookRepository bookRepository;

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public void save(People people) {
        peopleRepository.save(people);
    }

    public People findById(Long id) {
        return peopleRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        peopleRepository.deleteById(id);
    }

    public void showBook(People people) {
        if (people.getBookList() != null) {
            LocalDateTime dateTime1 = LocalDateTime.now();
            for (Book book : people.getBookList()) {
                long daysDifference = book.getDateTakeBook().until(dateTime1, ChronoUnit.DAYS);
                book.setOverdueDeliveryDate(daysDifference > 10);
                bookRepository.save(book);
            }
        }
    }
}