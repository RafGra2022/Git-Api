package com.github.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.repository.IGithubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubService {

	private final IGithubRepository githubRepository;

	public List<UserRepositoryDto> getUserRepositories(String user) {
		return githubRepository.getRepositories(user)
						  .stream()
						  .filter(repo -> repo.getForks() < 1)
						  .toList();
	}

}
