package ru.job4j.github.analysis.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.github.analysis.dto.RepositoryCommits;
import ru.job4j.github.analysis.model.Repository;
import ru.job4j.github.analysis.service.RepositoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class GitHubController {
    private RepositoryService repositoryService;

    @GetMapping("/repositories")
    public List<Repository> getAllRepositories() {
        return repositoryService.findAll();
    }

    @GetMapping("/commits/{name}")
    public RepositoryCommits getCommits(@PathVariable(value = "name") String name) {
        return repositoryService.findAllCommit(name);
    }

    @PostMapping("/repository")
    public ResponseEntity<Void> create(@RequestBody Repository repository) {
        Repository repo = repositoryService.create(repository);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(repo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
