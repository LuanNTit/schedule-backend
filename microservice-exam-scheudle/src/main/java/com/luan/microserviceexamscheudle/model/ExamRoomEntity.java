package com.luan.microserviceexamscheudle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam-room")
public class ExamRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phongThi;
    private int capacity;

    @OneToMany(mappedBy = "examRoom")
    private List<ExamScheduleEntity> examSchedules;
}
