package com.github.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.domain.GithubService;
import com.github.exception.ApplicationRequestException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

	private final GithubService githubService;

	@GetMapping(value = "/repository", produces = "application/json" )
	public UserRepositoryResponse getRepo(@Valid @NotBlank(message = "user is required") @RequestHeader(required = true) String user) throws ApplicationRequestException {
			return UserRepositoryResponseMapper.mapToUserRepositoryResponse(githubService.getUserRepositories(user));
		
	}

}
