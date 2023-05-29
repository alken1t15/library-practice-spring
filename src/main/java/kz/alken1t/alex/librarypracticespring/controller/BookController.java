package kz.alken1t.alex.librarypracticespring.controller;

import jakarta.validation.Valid;
import kz.alken1t.alex.librarypracticespring.entity.Book;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.service.BookService;
import kz.alken1t.alex.librarypracticespring.service.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    private final PeopleService peopleService;


    @GetMapping
    public String bookPage(@RequestParam(name = "page", required = false) Integer page
            , @RequestParam(name = "books_per_page", required = false) Integer perPage
            , @RequestParam(name = "sort_by_year", required = false) String sort
            , Model model) {
        List<Book> books = bookService.mainPage(page, perPage, sort);
        model.addAttribute("books", books);
        return "book/book_main_page";
    }

    @GetMapping("/new")
    public String bookNew(Model model) {
        model.addAttribute("book", new Book());
        return "book/book_new_page";
    }

    @PostMapping
    public String bookNewCreate(@ModelAttribute("book") @Valid Book book
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/book_new_page";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String profileBook(@PathVariable Long id,
                              Model model) {
        Book book = bookService.findById(id);
        List<People> peoples = peopleService.findAll();
        model.addAttribute("book", book);
        model.addAttribute("peoples", peoples);
        return "book/book_profile_page";
    }

    @PutMapping
    public String profileBook(@RequestParam(name = "id_book") Long idBook, @RequestParam("id_people") Long idPeople) {
        bookService.takeBookPeople(idBook, idPeople);
        return "redirect:/books";
    }

    @PostMapping("/clear")
    public String profileBook(@RequestParam(name = "id") Long id) {
        bookService.giveBookPeople(id);
        return "redirect:/books";
    }

    @DeleteMapping
    public String profileBookDelete(@RequestParam(name = "id") Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }


    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable Long id,
                           Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book/book_edit_page";
    }

    @PatchMapping
    public String editBook(@ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/book_edit_page";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping(path = "/search")
    public String searchPage(@RequestParam(name = "name_book", required = false) String bookName, Model model) {
        if (bookName != null) {
            if (!bookName.isEmpty()) {
                Book book = bookService.findBookByNameStartingWith(bookName);
                if (book == null) {
                    model.addAttribute("noBook", "true");
                }
                model.addAttribute("book", book);
            } else {
                model.addAttribute("noBook", "true");
            }
        }
        return "book/book_search_page";
    }
}