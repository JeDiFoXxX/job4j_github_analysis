package ru.job4j.github.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.github.analysis.model.Commit;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepositoryCommits {
    private String repositoryName;
    private List<Commit> commits;
}
