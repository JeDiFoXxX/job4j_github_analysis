package ru.job4j.github.analysis.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.github.analysis.model.Commit;
import ru.job4j.github.analysis.model.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class GitHubService {
    private final RestTemplate restTemplate;

    public List<Repository> fetchRepositories(String username) {
        var uri = String.format("https://api.github.com/users/%s/repos", username);
        return restTemplate.exchange(
                        uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Repository>>() {
                        }).getBody();
    }

    public List<Commit> fetchCommits(Repository repository) {
        var uri = String.format("https://api.github.com/repos/JeDiFoXxX/%s/commits", repository.getName());
        return restTemplate.exchange(
                        uri, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<JsonNode>>() {
                        }).getBody()
                .stream()
                .map(node -> {
                    Commit commit = new Commit();
                    commit.setMessage(node.at("/commit/message").asText());
                    commit.setAuthor(node.at("/commit/author/name").asText());
                    var dateStr = node.at("/commit/author/date").asText();
                    if (!dateStr.isEmpty()) {
                        commit.setDate(OffsetDateTime.parse(dateStr).toLocalDateTime());
                    }
                    commit.setRepository(repository);
                    return commit;
                })
                .toList();
    }
}