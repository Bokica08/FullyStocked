package com.bazi.fullystocked.Models;

import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderid;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String supplierremark;
    private String managerremark;
    @Column(nullable = false)
    @NotNull(message = "Order must have creation date")
    private LocalDateTime datecreated;
    private LocalDateTime dateapproved;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderPriority priority;
    @ManyToOne
    @JoinColumn(name = "manageruserid")
    private Managers manager;
    @ManyToOne
    @JoinColumn(name = "supplieruserid")
    private Suppliers supplier;
    @OneToMany(mappedBy="order")
    private List<OrderedArticles> articlesList=new ArrayList<>();

    public Orders(OrderPriority priority, Managers manager, Suppliers supplier) {
        this.status = OrderStatus.CREATED;
        this.datecreated = LocalDateTime.now();
        this.priority = priority;
        this.manager = manager;
        this.supplier = supplier;
    }
}
