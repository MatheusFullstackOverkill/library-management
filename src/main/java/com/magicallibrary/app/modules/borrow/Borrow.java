package com.magicallibrary.app.modules.borrow;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow", schema = "public")
public class Borrow {
    @Id
    @Column(name = "borrow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "employee_id")
    private long employeeId;

    private String status;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public Borrow() {}

    public Borrow(
        long customerId,
        long employeeId,
        String status
    ) {
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.status = status;
    }

    public long getId() {
        return this.id;
    }

    public long getCustomerId() {
        return this.customerId;
    }

    public long getEmployeeId() {
        return this.employeeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return String.format(
            """
                id: %d,
                customerId: %s,
                employeeId: %s,
                status: %s
            """,
            this.id,
            this.customerId,
            this.employeeId,
            this.status
        );
    }
}
