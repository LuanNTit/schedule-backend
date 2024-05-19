package com.luan.microserviceaccountmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
public class StudentEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String maSV;
    private String tenSV;
    private String lop;
    @ManyToOne
    private UserEntity user;
}
