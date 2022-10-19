package guru.springframework.spring5recipeapp.domains;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
/*
this is required to overcome stackovwerflow exception,
Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
 */

@EqualsAndHashCode(exclude = "recipe")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Recipe recipe;
    @Lob
    private String recipeNotes;

}
