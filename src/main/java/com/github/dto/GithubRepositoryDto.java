package com.github.dto;

import java.util.List;

public record GithubRepositoryDto(String repository, String login, List<GithubBranchDto> branches, int forks ) {}
