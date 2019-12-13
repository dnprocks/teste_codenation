package br.com.challenge.repository;

import br.com.challenge.entity.LogError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogErrorRepository extends JpaRepository<LogError, Long> {
}
