package kz.alken1t.alex.librarypracticespring.util;

import kz.alken1t.alex.librarypracticespring.entity.People;
import kz.alken1t.alex.librarypracticespring.repository.PeopleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class PeopleValidator implements Validator {
    private final PeopleRepository peopleRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return People.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        People people = (People) target;
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "validation.people.name.empty");
        System.out.println("fsdfdsf");
        if (peopleRepository.findByLNM(people.getLNM()).isPresent()) {
            errors.rejectValue("LNM", "", "Такой пользователь уже есть");
        }
    }
}