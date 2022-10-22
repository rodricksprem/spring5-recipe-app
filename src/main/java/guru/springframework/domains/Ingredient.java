package guru.springframework.domains;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
/*
this is required to overcome stackovwerflow exception,
Caused by: org.hibernate.LazyInitializationException: failed to lazily initialize a collection of role:
 */
@EqualsAndHashCode(exclude = "recipe")
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne
    private Recipe recipe;

    @OneToOne(fetch=FetchType.EAGER)
    private UnitOfMeasure unitOfMeasure;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitOfMeasure) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.recipe = recipe;
        this.unitOfMeasure = unitOfMeasure;
    }

}
