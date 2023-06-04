package com.github.controller;

import java.util.List;

import com.github.domain.UserRepositoryDto;

class UserRepositoryResponseMapper {

	static UserRepositoryResponse mapToUserRepositoryResponse(List<UserRepositoryDto> userRepositoryDtos) {

		List<RepositoryResponse> repositoryResponses = userRepositoryDtos.stream()
				.map(userRepositoryDto -> RepositoryResponse.builder()
						.login(userRepositoryDto.getLogin())
						.repository(userRepositoryDto.getRepository())
						.branches(mapToRepositoryBranchResponse(userRepositoryDto))
						.build())
				.toList();
		return new UserRepositoryResponse(repositoryResponses);
	}

	private static List<RepositoryBranchResponse> mapToRepositoryBranchResponse(UserRepositoryDto userRepositoryDto) {
		return userRepositoryDto.getBranches().stream()
				.map(branch -> new RepositoryBranchResponse(branch.getName(), branch.getLastSha())).toList();
	}
}
