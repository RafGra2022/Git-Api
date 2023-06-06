package com.github.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.dto.GithubRepositoryBranchDto;
import com.github.dto.GithubRepositoryDto;
import com.github.model.RepositoryDetail;

@Component
public class GithubRepositoryMapper {

	public GithubRepositoryDto mapToGithubRepositoryDto(RepositoryDetail repository,List<GithubRepositoryBranchDto> branches) {

		return new GithubRepositoryDto(
									repository.name(),
								    repository.owner().login(),
								    branches,
								    repository.forks());

	}

}
