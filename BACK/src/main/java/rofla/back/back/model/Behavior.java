package rofla.back.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "behavior", schema = "grupgo")
public class Behavior {
    @Id
    @Column(name = "username", nullable = false)
    private Integer id;

    @Column(name = "rest", precision = 5, scale = 1)
    private BigDecimal rest;

    @Column(name = "meal", precision = 5, scale = 1)
    private BigDecimal meal;

    @Column(name = "study", precision = 5, scale = 1)
    private BigDecimal study;

    @Column(name = "exercise", precision = 5, scale = 1)
    private BigDecimal exercise;

    @Column(name = "hobby", precision = 5, scale = 1)
    private BigDecimal hobby;

}