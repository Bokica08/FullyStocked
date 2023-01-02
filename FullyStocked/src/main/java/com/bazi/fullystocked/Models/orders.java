package com.bazi.fullystocked.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orederid;
    @Column(nullable = false)
    @NotNull(message = "Order must have status")
    @NotEmpty(message = "Order must have status")
    private String status;
    private String supplierremark;
    private String managerremark;
    @Column(nullable = false)
    @NotNull(message = "Order must have creation date")
    @NotEmpty(message = "Order must have creation date")
    private LocalDateTime datecreated;
    private LocalDateTime dateapproved;
    @Column(nullable = false)
    @NotNull(message = "Order must have priority")
    @NotEmpty(message = "Order must have priority")
    private String priority;
    @ManyToOne
    @JoinColumn(name = "manageruserid")
    private managers manager;
    @ManyToOne
    @JoinColumn(name = "supplieruserid")
    private suppliers supplier;

    public orders(String status, String supplierremark, String managerremark,
                  LocalDateTime dateapproved, String priority, managers manager, suppliers supplier) {
        this.status = status;
        this.supplierremark = supplierremark;
        this.managerremark = managerremark;
        this.datecreated = LocalDateTime.now();
        this.dateapproved = dateapproved;
        this.priority = priority;
        this.manager = manager;
        this.supplier = supplier;
    }
}
