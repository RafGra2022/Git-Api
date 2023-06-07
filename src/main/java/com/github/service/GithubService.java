package com.github.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.dto.GithubRepositoryDto;
import com.github.repository.IGithubRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GithubService {

	private final IGithubRepository githubRepository;

	public List<GithubRepositoryDto> getRepositories(String user) {
		return githubRepository.getRepositories(user)
						  .stream()
						  .filter(repo -> repo.forks() < 1)
						  .toList();
	}

}
