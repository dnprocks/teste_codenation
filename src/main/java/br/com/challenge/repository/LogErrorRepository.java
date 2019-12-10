package br.com.challenge.repository;

import br.com.challenge.entity.LogError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogErrorRepository extends JpaRepository<LogError, Long> {

    @Query(value = "SELECT * FROM LOG_ERROR log LIMIT :offset, :limit", nativeQuery = true)
    List<LogError> findAllWithLimitAndOffset(@Param("limit") int limit, @Param("offset") int offset);
}
