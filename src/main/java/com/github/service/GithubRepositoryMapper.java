package com.github.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.dto.GithubBranchDto;
import com.github.dto.GithubRepositoryDto;
import com.github.model.BranchDetail;
import com.github.model.RepositoryDetail;

@Component
public class GithubRepositoryMapper {

	public GithubRepositoryDto mapToGithubRepositoryDto(RepositoryDetail repository,List<GithubBranchDto> branches) {

		return new GithubRepositoryDto(
									repository.name(),
								    repository.owner().login(),
								    branches,
								    repository.forks());

	}
	
	public List<GithubBranchDto> mapToGithubRepositoryBranchDtos(List<BranchDetail> branchDetails) {

		return branchDetails.stream()
						.map(branchDetail-> new GithubBranchDto(branchDetail.name(), branchDetail.commit().sha()))
						.toList();

	}

}
