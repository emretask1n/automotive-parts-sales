package com.emretaskin.itg.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_logs")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime logDate;

    @Column(nullable = false)
    private String logMessage;

    public UserLog(User user, LocalDateTime logDate, String logMessage) {
        this.user = user;
        this.logDate = logDate;
        this.logMessage = logMessage;
    }
}
