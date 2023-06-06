package com.github.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.service.GithubService;
import com.github.service.GithubRepositoryResponseMapper;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GithubRepositoryController {

	private final GithubService githubService;
	private final GithubRepositoryResponseMapper userRepositoryResponseMapper;

	@GetMapping(value = "/repository", produces = "application/json" )
	public GithubRepositoryResponse getRepo(@Valid @NotBlank(message = "user is required") @RequestHeader(required = true) String user)  {
			return userRepositoryResponseMapper.mapToGithubRepositoryResponse(githubService.getUserRepositories(user));
	}

}
