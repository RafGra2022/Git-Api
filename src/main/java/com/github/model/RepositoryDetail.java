package com.github.model;

public record RepositoryDetail(long id, String name, OwnerDetail owner, boolean fork, int forks) {}
