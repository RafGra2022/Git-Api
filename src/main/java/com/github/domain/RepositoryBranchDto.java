package com.github.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RepositoryBranchDto {

	private String name;
	private String lastSha;
}
