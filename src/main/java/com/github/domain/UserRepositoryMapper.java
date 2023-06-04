package com.github.domain;

import java.util.List;

import com.github.repository.RepositoryDetail;

public class UserRepositoryMapper {

	public static UserRepositoryDto mapToUserRepositoryBean(RepositoryDetail repository,List<RepositoryBranchDto> branches) {

		return UserRepositoryDto.builder()
								   .repository(repository.getName())
								   .login(repository.getOwner().getLogin())
								   .branches(branches)
								   .build();

	}

}
