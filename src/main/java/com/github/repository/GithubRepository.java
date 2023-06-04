package com.github.repository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.domain.RepositoryBranchDto;
import com.github.domain.RepositoryBranchMapper;
import com.github.domain.UserRepositoryDto;
import com.github.domain.UserRepositoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
@RequiredArgsConstructor
class GithubRepository implements IGithubRepository {

	private final WebClient gitHubApi;
	private final static String PATH_USER_REPO ="users/%s/repos";
	private final static String PATH_USER_BRANCHES = "repos/%s/%s/branches";
	
	@Override
	public List<UserRepositoryDto> getRepositories(String user) {
		
		return fetchUserRepositories(user).stream().map(repositoryDetail -> {
			List<RepositoryBranchDto> branches = RepositoryBranchMapper
					.mapToRepositoryBranchBean(fetchBranchDetails(user, repositoryDetail.getName()));
			
			return UserRepositoryMapper.mapToUserRepositoryBean(repositoryDetail, branches);
		}).toList();
		
	}
	
	private List<RepositoryDetail> fetchUserRepositories(String user) {
		return  gitHubApi.get()
				.uri(uriBuilder -> uriBuilder
						.path(PATH_USER_REPO.formatted(user))
						.build())
				.retrieve()
				.onStatus(status -> HttpStatus.NOT_FOUND.equals(status), resp -> {
				    log.error("ClientError {}", resp.statusCode());
				    return Mono.error(new UserPrincipalNotFoundException("User not found"));
				  })
				.onStatus(HttpStatusCode::is4xxClientError, resp -> {
				    log.error("ClientError {}", resp.statusCode());
				    return Mono.error(new RuntimeException("ClientError"));
				  })
				.onStatus(HttpStatusCode::is5xxServerError, resp -> {
				    log.error("ServerError {}", resp.statusCode());
				    return Mono.error(new RuntimeException("ServerError"));
				  })
				.bodyToMono(new ParameterizedTypeReference<List<RepositoryDetail>>() {})
				.block();
	}
	
	private List<BranchDetail> fetchBranchDetails(String user, String repository) {
		return gitHubApi.get()
				.uri(uriBuilder -> uriBuilder
						.path(PATH_USER_BRANCHES.formatted(user,repository))
						.build())
				.retrieve()
				.onStatus(HttpStatusCode::is4xxClientError, resp -> {
				    log.error("ClientError {}", resp.statusCode());
				    return Mono.error(new RuntimeException("ClientError"));
				  })
				.onStatus(HttpStatusCode::is5xxServerError, resp -> {
				    log.error("ServerError {}", resp.statusCode());
				    return Mono.error(new RuntimeException("ServerError"));
				  })
				.bodyToMono(new ParameterizedTypeReference<List<BranchDetail>>() {})
				.block();
	}
	

	
}
