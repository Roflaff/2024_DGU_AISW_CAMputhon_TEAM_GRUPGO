package rofla.back.back.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diary", schema = "grupgo")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false, referencedColumnName = "username")
    private User username;

    @Column(name = "date", nullable = false, length = 15)
    private String date;

    @Column(name = "body", length = 1000)
    private String body;

    @Column(name = "empty_num", nullable = false)
    private Integer emptyNum;

}