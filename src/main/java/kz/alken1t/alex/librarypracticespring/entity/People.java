package kz.alken1t.alex.librarypracticespring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "people")
@Data
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "people")
    private List<Book> bookList;

    private String LNM;

    @Column(name = "date_born")
    private LocalDate dateBorn;
}