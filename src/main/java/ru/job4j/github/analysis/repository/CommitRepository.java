package ru.job4j.github.analysis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.github.analysis.model.Commit;

import java.util.List;

@Transactional(readOnly = true)
public interface CommitRepository extends JpaRepository<Commit, Long> {
    @Query(
            value = """
                    SELECT c.* FROM commits c
                    JOIN repositories r ON c.repository_id = r.id
                    WHERE r.name = :name
                    """, nativeQuery = true)
    List<Commit> findAllCommitsByRepositoryName(@Param("name") String name);
}
