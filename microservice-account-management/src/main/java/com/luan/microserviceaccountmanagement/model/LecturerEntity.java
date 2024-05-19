package com.luan.microserviceaccountmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lecture")
public class LecturerEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String tenGV;
    private String boPhan;
    @ManyToOne
    private UserEntity user;
}
