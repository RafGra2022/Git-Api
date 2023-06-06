package com.github.dto;

import java.util.List;

public record GithubRepositoryDto(String repository, String login, List<GithubRepositoryBranchDto> branches, int forks ) {}
