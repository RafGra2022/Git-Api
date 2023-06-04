package com.github.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepositoryBranchResponse {

	private String name;
	private String lastSha;
}
