package com.github.repository;

import java.util.List;

import com.github.domain.RepositoryBranchDto;
import com.github.domain.UserRepositoryDto;

public class UserRepositoryMapper {

	public static UserRepositoryDto mapToUserRepositoryBean(RepositoryDetail repository, List<RepositoryBranchDto> branches) {
		
		return UserRepositoryDto.builder()
							.repository(repository.getName())
							.login(repository.getName())
							.branches(branches)
							.forks(repository.getForks())
							.build();
	}

}
