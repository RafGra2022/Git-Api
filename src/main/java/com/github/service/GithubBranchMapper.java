package com.github.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.github.dto.GithubRepositoryBranchDto;
import com.github.model.BranchDetail;

@Component
public class GithubBranchMapper {

	public List<GithubRepositoryBranchDto> mapToGithubRepositoryBranchDtos(List<BranchDetail> branchDetails) {

		return branchDetails.stream()
						.map(branchDetail-> new GithubRepositoryBranchDto(branchDetail.name(), branchDetail.commit().sha()))
						.toList();

	}
}
