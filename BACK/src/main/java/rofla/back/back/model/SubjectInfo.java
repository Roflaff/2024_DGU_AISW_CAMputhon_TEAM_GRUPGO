package rofla.back.back.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subject_info", schema = "grupgo")
public class SubjectInfo {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "subject_num", nullable = false, length = 30)
    private String subjectNum;

    @Column(name = "start_time")
    private Float startTime;

    @Column(name = "end_time")
    private Float endTime;

    @Column(name = "classroom", length = 30)
    private String classroom;

}