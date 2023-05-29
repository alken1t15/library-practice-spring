package kz.alken1t.alex.librarypracticespring.controller;

import jakarta.validation.Valid;
import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.service.PeopleService;
import kz.alken1t.alex.librarypracticespring.util.PeopleValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/people")
@AllArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;
    private final PeopleValidator peopleValidator;

    @GetMapping
    public String peoplePage(Model model) {
        List<People> peoples = peopleService.findAll();
        model.addAttribute("peoples", peoples);
        return "people/people_main_page";
    }

    @GetMapping("/new")
    public String peopleNew(Model model) {
        model.addAttribute("people", new People());
        return "people/people_new_page";
    }

    @PostMapping
    public String peopleNewCreate(@ModelAttribute("people") @Valid People people
            , BindingResult bindingResult) {
        peopleValidator.validate(people, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/people_new_page";
        }
        peopleService.save(people);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String profilePeople(@PathVariable Long id,
                                Model model) {
        People people = peopleService.findById(id);
        peopleService.showBook(people);
        model.addAttribute("people", people);
        return "people/people_profile_page";
    }

    @DeleteMapping
    public String profilePeople(@RequestParam Long id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }


    @GetMapping("/{id}/edit")
    public String editPeople(@PathVariable Long id,
                             Model model) {
        People people = peopleService.findById(id);
        model.addAttribute("people", people);
        return "people/people_edit_page";
    }

    @PatchMapping
    public String editPeople(@ModelAttribute("people") @Valid People people,
                             BindingResult bindingResult) {
        peopleValidator.validate(people, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/people_edit_page";
        }
        peopleService.save(people);
        return "redirect:/people";
    }

}