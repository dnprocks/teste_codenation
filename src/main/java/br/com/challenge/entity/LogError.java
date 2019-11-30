package br.com.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "LogError")
public class LogError {

//-id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//-user_id (id do usuário que consumiu o método)
//-environment_id (produção, homologação, desenvolvimento)
//-request_ip (obtido pela aplicação)
//-level (error, warning, debug)

//-title
    @Column(length = 100)
    @NotNull
    private String title;

//-details
    @Column(length = 255)
    @NotNull
    private String details;

//-created_at (setado pela aplicação)


}
