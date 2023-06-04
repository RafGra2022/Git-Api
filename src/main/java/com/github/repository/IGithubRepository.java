package com.github.repository;

import java.util.List;

import com.github.domain.UserRepositoryDto;

public interface IGithubRepository {

	List<UserRepositoryDto> getRepositories(String user);

}
