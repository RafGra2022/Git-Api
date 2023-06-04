package com.github.domain;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.repository.IGithubRepository;

@ExtendWith(MockitoExtension.class)
public class GithubServiceTests {

	@Mock
	private IGithubRepository githubRepository;
	@InjectMocks
	private GithubService githubService;

	@Test
	public void shouldReturn1UserRepository() {

		UserRepositoryDto userRepositoryDto1 = UserRepositoryDto.builder().repository("RafGra2022").forks(2).build();
		UserRepositoryDto userRepositoryDto2 = UserRepositoryDto.builder().repository("RafGra2023").forks(0).build();
		List<UserRepositoryDto> userRepositoryDtos = List.of(userRepositoryDto1,userRepositoryDto2);

		Mockito.when(githubRepository.getRepositories(Mockito.any())).thenReturn(userRepositoryDtos);

		List<UserRepositoryDto> repositories = githubService.getUserRepositories("RafGra2022");

		Assertions.assertEquals(1, repositories.size());
	}
	
	@Test
	public void shouldReturnEmptyList() {

		List<UserRepositoryDto> userRepositoryDtos = List.of();

		Mockito.when(githubRepository.getRepositories(Mockito.any())).thenReturn(userRepositoryDtos);

		List<UserRepositoryDto> repositories = githubService.getUserRepositories("RafGra2022");

		Assertions.assertEquals(0, repositories.size());
	}
}
