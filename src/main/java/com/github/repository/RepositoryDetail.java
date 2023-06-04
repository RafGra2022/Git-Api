package com.github.repository;

import lombok.Getter;

@Getter
public class RepositoryDetail {

	private long id;
	private String name;
	private OwnerDetail owner;
	private boolean fork;
	private int forks;
}
