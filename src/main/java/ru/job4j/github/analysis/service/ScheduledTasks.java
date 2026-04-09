package ru.job4j.github.analysis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.job4j.github.analysis.task.*;

@Service
public class ScheduledTasks {
    private final RepositoryTask repositoryTask;
    private final CommitTask commitTask;
    private final String username;

    public ScheduledTasks(
            RepositoryTask repositoryTask,
            CommitTask commitTask,
            GitHubService gitHubService,
            @Value("${github.username:JediFoXxX}") String username) {
        this.repositoryTask = repositoryTask;
        this.commitTask = commitTask;
        this.username = username;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate}")
    public void scheduled() {
        var repositories = repositoryTask.fetchRepositories(username);
        repositories.forEach(commitTask::fetchCommits);
    }
}
