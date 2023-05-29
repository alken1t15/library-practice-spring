package kz.alken1t.alex.librarypracticespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "people_id")
    private People people;

    @NotEmpty(message = "Поле должно быть заполнено")
    private String name;
    @NotEmpty(message = "Поле должно быть заполнено")
    private String author;
    @NotNull(message = "Поле должно быть заполнено")
    @Min(value = 0, message = "Значение должно быть больше 0")
    private Integer year;

    @Column(name = "date_take_book")
    private LocalDateTime dateTakeBook;

    @Column(name = "overdue_delivery_date")
    private Boolean overdueDeliveryDate;
}