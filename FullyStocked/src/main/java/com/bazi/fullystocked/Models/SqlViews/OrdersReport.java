package com.bazi.fullystocked.Models.SqlViews;

import com.bazi.fullystocked.Models.Enumerations.OrderPriority;
import com.bazi.fullystocked.Models.Enumerations.OrderStatus;
import lombok.Getter;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Immutable
@Entity
@Getter
@Table(name="orders_report")
public class OrdersReport {
    @Id
    Integer orderid;
    String supplierremark;
    String managerremark;
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    LocalDateTime datecreated;
    @Enumerated(EnumType.STRING)
    OrderPriority priority;
    Integer supplieruserid;
    String supplierinfo;
    Integer manageruserid;
}
