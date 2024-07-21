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
@Table(name = "ordered_food", schema = "grupgo")
public class OrderedFood {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "food_cnt", nullable = false)
    private Integer foodCnt;

}