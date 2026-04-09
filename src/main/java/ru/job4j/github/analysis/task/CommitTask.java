package ru.job4j.github.analysis.task;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.job4j.github.analysis.model.Repository;
import ru.job4j.github.analysis.repository.CommitRepository;
import ru.job4j.github.analysis.service.GitHubService;

@Component
@AllArgsConstructor
public class CommitTask {
    private final GitHubService gitHubService;
    private final CommitRepository commitRepository;

    @Async
    public void fetchCommits(Repository repository) {
        System.out.println(Thread.currentThread().getName());
        var commits = gitHubService.fetchCommits(repository);
        commitRepository.saveAll(commits);
    }
}
