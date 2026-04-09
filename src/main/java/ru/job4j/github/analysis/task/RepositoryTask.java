package ru.job4j.github.analysis.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.job4j.github.analysis.model.Repository;
import ru.job4j.github.analysis.service.GitHubService;
import ru.job4j.github.analysis.service.RepositoryService;

import java.util.List;

@Component
@AllArgsConstructor
public class RepositoryTask {
    private final GitHubService gitHubService;
    private final RepositoryService repositoryService;

    public List<Repository> fetchRepositories(String username) {
        var repositories = gitHubService.fetchRepositories(username);
        repositoryService.addAll(repositories);
        return repositories;
    }
}
