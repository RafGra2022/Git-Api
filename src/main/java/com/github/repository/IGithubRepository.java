package com.github.repository;

import java.util.List;

import com.github.dto.GithubRepositoryDto;

public interface IGithubRepository {

	List<GithubRepositoryDto> getRepositories(String user);

}
