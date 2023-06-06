package com.github.controller;

import java.util.List;

public record GithubSingleRepositoryResponse(String repository, String login, List<GithubBranchResponse> branches) {}

