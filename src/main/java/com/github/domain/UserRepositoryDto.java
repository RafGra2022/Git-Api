package com.github.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserRepositoryDto {

	private String repository;
	private String login;
	private List<RepositoryBranchDto> branches;
	private int forks;
	
}
