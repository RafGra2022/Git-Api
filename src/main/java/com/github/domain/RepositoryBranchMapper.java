package com.github.domain;

import java.util.ArrayList;
import java.util.List;

import com.github.repository.BranchDetail;

public class RepositoryBranchMapper {

	public static List<RepositoryBranchDto> mapToRepositoryBranchBean(List<BranchDetail> branchDetails) {

		List<RepositoryBranchDto> branches = new ArrayList<>();

		for (BranchDetail branchDetail : branchDetails) {
			branches.add(new RepositoryBranchDto(branchDetail.getName(),branchDetail.getCommit().getSha()));
		}

		return branches;

	}
}
