package com.github.controller;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RepositoryResponse {

	private String repository;
	private String login;
	private List<RepositoryBranchResponse> branches;
	
}
