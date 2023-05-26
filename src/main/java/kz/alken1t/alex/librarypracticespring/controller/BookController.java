package kz.alken1t.alex.librarypracticespring.controller;

import jakarta.validation.Valid;
import kz.alken1t.alex.librarypracticespring.entity.Book;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.BookRepository;
import kz.alken1t.alex.librarypracticespring.repository.PeopleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
public class BookController {

    private PeopleRepository peopleRepository;

    private BookRepository bookRepository;

    public BookController(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String bookPage(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "book/book_main_page";
    }

    @GetMapping("/new")
    public String bookNew(Model model){
        model.addAttribute("book", new Book());
        return "book/book_new_page";
    }

    @PostMapping
    public String bookNewCreate(@ModelAttribute("book") @Valid Book book
            , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/book_new_page";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String profileBook(@PathVariable Long id,
                           Model model){
        Book book = bookRepository.findById(id).orElseThrow();
        List<People> peoples = peopleRepository.findAll();
        model.addAttribute("book",book);
        model.addAttribute("peoples",peoples);
        return "book/book_profile_page";
    }

    @PutMapping
    public String profileBook(@RequestParam(name = "id_book") Long idBook, @RequestParam("id_people") Long idPeople){
        Book book = bookRepository.findById(idBook).orElseThrow();
        People people = peopleRepository.findById(idPeople).orElseThrow();
        book.setPeople(people);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @PostMapping("/clear")
    public String profileBook(@RequestParam(name = "id") Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        book.setPeople(null);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @DeleteMapping
    public String profileBookDelete(@RequestParam(name = "id") Long id){
        Book book = bookRepository.findById(id).orElseThrow();
        bookRepository.delete(book);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable Long id,
                             Model model){
        Book book = bookRepository.findById(id).orElseThrow();
        model.addAttribute("book",book);
        return "book/book_edit_page";
    }

    @PatchMapping
    public String editBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "book/book_edit_page";
        }
        bookRepository.save(book);
        return "redirect:/books";
    }

}