package ru.job4j.github.analysis.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.github.analysis.dto.RepositoryCommits;
import ru.job4j.github.analysis.model.Repository;
import ru.job4j.github.analysis.repository.CommitRepository;
import ru.job4j.github.analysis.repository.RepositoryRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class RepositoryService {
    private final RepositoryRepository repositoryRepository;
    private final CommitRepository commitRepository;

    public Repository create(Repository repository) {
        return repositoryRepository.save(repository);
    }

    public List<Repository> addAll(List<Repository> repository) {
        return repositoryRepository.saveAll(repository);
    }

    @Transactional
    public List<Repository> findAll() {
        return repositoryRepository.findAll();
    }

    @Transactional
    public RepositoryCommits findAllCommit(String name) {
        return new RepositoryCommits(name, commitRepository.findAllCommitsByRepositoryName(name));
    }
}
