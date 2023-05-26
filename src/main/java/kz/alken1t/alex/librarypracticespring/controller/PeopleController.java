package kz.alken1t.alex.librarypracticespring.controller;

import jakarta.validation.Valid;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.BookRepository;
import kz.alken1t.alex.librarypracticespring.repository.PeopleRepository;
import kz.alken1t.alex.librarypracticespring.util.PeopleValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/people")
public class PeopleController {

    private PeopleValidator peopleValidator;
    private PeopleRepository peopleRepository;

    private BookRepository bookRepository;

    public PeopleController(PeopleValidator peopleValidator, PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleValidator = peopleValidator;
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
    public String peopleNew(Model model){
        model.addAttribute("people", new People());
        return "people/people_new_page";
    }

    @PostMapping
    public String peopleNewCreate(@ModelAttribute("people") @Valid People people
            , BindingResult bindingResult){
        peopleValidator.validate(people,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/people_new_page";
        }
        peopleRepository.save(people);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String profilePeople(@PathVariable Long id,
                             Model model){
        People people = peopleRepository.findById(id).orElseThrow();
        model.addAttribute("people",people);
        return "people/people_profile_page";
    }

    @DeleteMapping
    public String profilePeople(@RequestParam Long id){
        People people = peopleRepository.findById(id).orElseThrow();
        peopleRepository.delete(people);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String editPeople(@PathVariable Long id,
                             Model model){
        People people = peopleRepository.findById(id).orElseThrow();
        model.addAttribute("people",people);
        return "people/people_edit_page";
    }

    @PatchMapping
    public String editPeople(@ModelAttribute("people") @Valid People people,
                             BindingResult bindingResult){
        peopleValidator.validate(people,bindingResult);
        if(bindingResult.hasErrors()){
            return "people/people_edit_page";
        }
        System.out.println(people.getLNM());
        System.out.println(people.getId());
        peopleRepository.updatePeople(people.getLNM(),people.getDateBorn(),people.getId());
        return "redirect:/people";
    }

}