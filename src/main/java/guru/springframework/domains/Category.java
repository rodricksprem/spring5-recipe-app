package guru.springframework.domains;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
/*
this is required to overcome stackovwerflow exception,
Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
 */

@EqualsAndHashCode(exclude = "recipes")
@Entity

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")

    private Set<Recipe> recipes;

}
