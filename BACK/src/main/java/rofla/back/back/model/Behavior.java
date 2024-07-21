package rofla.back.back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "behavior", schema = "grupgo")
public class Behavior {
    @Id
    @Column(name = "username", nullable = false, length = 10)
    private String username;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private User user;

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