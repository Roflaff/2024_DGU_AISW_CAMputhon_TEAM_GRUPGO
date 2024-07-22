package rofla.back.back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "food", schema = "grupgo")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "restaurant", nullable = false, length = 10)
    private String restaurant;

    @Column(name = "waiting")
    private Integer waiting;

    @Column(name = "food_info")
    private String foodInfo;

    @Column(name = "cost")
    private Integer cost;
}