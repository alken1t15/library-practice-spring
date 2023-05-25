package kz.alken1t.alex.librarypracticespring.controller;

import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.BookRepository;
import kz.alken1t.alex.librarypracticespring.repository.PeopleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/people")
public class MainController {

    private PeopleRepository peopleRepository;

    private BookRepository bookRepository;

    public MainController(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String peoplePage(Model model){
        List<People> peoples = peopleRepository.findAll();
        model.addAttribute("peoples", peoples);
        return "people/people_main_page";
    }

    @GetMapping("/new")
    public String peopleNew(){
        return "people/people_main_page";
    }

    @PostMapping
    public String peopleNewCreate(Model model){
        return "people/people_main_page";
    }

}