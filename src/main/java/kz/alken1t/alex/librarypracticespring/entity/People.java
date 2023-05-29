package kz.alken1t.alex.librarypracticespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "people")
@Getter
@Setter
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "people")
    private List<Book> bookList;

    @NotEmpty(message = "Поле должно быть заполнено")
    @Pattern(regexp = "^([А-ЯЁ][а-яё]+)\\s+([А-ЯЁ][а-яё]+)\\s+([А-ЯЁ][а-яё]+)$", message = "Ваше ФИО должно соответствовать данному формату: Ф И О")
    private String LNM;

    @Column(name = "date_born")
    @NotNull(message = "Поле должно быть заполнено")
    @Min(value = 0, message = "Значение должно быть больше 0")
    private Integer dateBorn;
}