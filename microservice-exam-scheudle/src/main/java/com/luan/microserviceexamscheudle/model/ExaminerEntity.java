package com.luan.microserviceexamscheudle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "examiner")
public class ExaminerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gvChamThi1;
    private String gvChamThi2;
    private String ct1;
    private String ct2;

    @ManyToOne
    @JoinColumn(name = "exam_schedule_id")
    private ExamScheduleEntity examSchedule;
}
