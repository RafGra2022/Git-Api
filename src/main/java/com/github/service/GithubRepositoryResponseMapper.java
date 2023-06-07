package com.github.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.controller.GithubBranchResponse;
import com.github.controller.GithubSingleRepositoryResponse;
import com.github.controller.GithubRepositoryResponse;
import com.github.dto.GithubRepositoryDto;

@Component
public class GithubRepositoryResponseMapper {

	public GithubRepositoryResponse mapToGithubRepositoryResponse(List<GithubRepositoryDto> userRepositoryDtos) {

		var repositoryResponse = userRepositoryDtos.stream()
				.map(userRepositoryDto -> new GithubSingleRepositoryResponse(
						userRepositoryDto.repository(),
						userRepositoryDto.login(),
						mapToGithubBranchResponses(userRepositoryDto)
						)).toList();
		return new GithubRepositoryResponse(repositoryResponse);
	}

	private static List<GithubBranchResponse> mapToGithubBranchResponses(GithubRepositoryDto userRepositoryDto) {
		return userRepositoryDto.branches().stream()
				.map(branch -> new GithubBranchResponse(branch.name(), branch.lastSha()))
				.toList();
	}
}
