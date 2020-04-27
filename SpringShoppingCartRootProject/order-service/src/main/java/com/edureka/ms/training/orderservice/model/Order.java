package com.edureka.ms.training.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_order")
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Integer id;

    @Column(name = "user_id")
    String userId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "address")
    String address;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;


}