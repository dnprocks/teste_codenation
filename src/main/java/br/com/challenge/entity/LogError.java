package br.com.challenge.entity;

import br.com.challenge.enums.Environment;
import br.com.challenge.enums.ErrorLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LogError")
public class LogError {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "users_id")
//    private Users users;

    @ManyToOne
    @JoinColumn(name="users_id", nullable=false)
    private Users users;

    @NotNull
    private Environment environment;

    @Column(length = 100)
    @NotNull
    private String requestIp;

    @NotNull
    private ErrorLevel level;

    @Column(length = 100)
    @NotNull
    private String title;

    @Column(length = 255)
    @NotNull
    private String details;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}
