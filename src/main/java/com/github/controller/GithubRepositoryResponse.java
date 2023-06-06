package com.github.controller;

import java.util.List;

public record GithubRepositoryResponse(List<GithubSingleRepositoryResponse> repositories){}
	
