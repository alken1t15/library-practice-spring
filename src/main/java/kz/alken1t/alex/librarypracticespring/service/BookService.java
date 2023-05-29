package kz.alken1t.alex.librarypracticespring.service;

import kz.alken1t.alex.librarypracticespring.entity.Book;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final PeopleService peopleService;


    public List<Book> mainPage(Integer page, Integer perPage, String sort){
        List<Book> books;
        if(page != null && perPage != null && sort == null){
            Pageable pageable = PageRequest.of(page,perPage);
            Page<Book> bookPage = bookRepository.findAll(pageable);
            books = bookPage.stream().toList();
        }
        else if(sort == null){
            books = bookRepository.findAll();
        }
        else if(page != null && perPage != null && sort.equals("true")){
            Sort sort1 = Sort.by(Sort.Order.asc("year"));
            Pageable pageable = PageRequest.of(page,perPage,sort1);
            Page<Book> bookPage = bookRepository.findAll(pageable);
            books = bookPage.stream().toList();
        }
        else {
            Sort sort1 = Sort.by(Sort.Order.asc("year"));
            books = bookRepository.findAll(sort1);
        }
        return books;
    }

    public void save(Book book){
        bookRepository.save(book);
    }

    public Book findById(Long id){
        return bookRepository.findById(id).orElseThrow();
    }

    public void takeBookPeople(Long idBook,Long idPeople){
        Book book = findById(idBook);
        People people = peopleService.findById(idPeople);
        book.setDateTakeBook(LocalDateTime.now());
        book.setPeople(people);
        bookRepository.save(book);
    }

    public void giveBookPeople(Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setDateTakeBook(null);
        book.setPeople(null);
        bookRepository.save(book);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public Book findBookByNameStartingWith(String bookName){
        return bookRepository.findBookByNameStartingWith(bookName).orElse(null);
    }
}