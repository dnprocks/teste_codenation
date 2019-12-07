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

//-id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//-user_id (id do usuário que consumiu o método)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

//-environment_id (produção, homologação, desenvolvimento)
    @NotNull
    private Environment environment;

//-request_ip (obtido pela aplicação)
    @Column(length = 100)
    @NotNull
    private String requestIp;

//-level (error, warning, debug)
    @NotNull
    private ErrorLevel level;

//-title
    @Column(length = 100)
    @NotNull
    private String title;

//-details
    @Column(length = 255)
    @NotNull
    private String details;

//-created_at (setado pela aplicação)
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;
}
