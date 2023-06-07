package com.github.domain;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.dto.GithubRepositoryDto;
import com.github.repository.IGithubRepository;
import com.github.service.GithubService;

@ExtendWith(MockitoExtension.class)
public class GithubServiceTests {

	@Mock
	private IGithubRepository githubRepository;
	@InjectMocks
	private GithubService githubService;

	@Test
	public void shouldReturn1UserRepository() {

		GithubRepositoryDto userRepositoryDto1 = new GithubRepositoryDto("RafGra2022","RafGra2022",new ArrayList<>(),2);
		GithubRepositoryDto userRepositoryDto2 = new GithubRepositoryDto("RafGra2022","RafGra2022",new ArrayList<>(),0);
		List<GithubRepositoryDto> userRepositoryDtos = List.of(userRepositoryDto1,userRepositoryDto2);

		Mockito.when(githubRepository.getRepositories(Mockito.any())).thenReturn(userRepositoryDtos);

		List<GithubRepositoryDto> repositories = githubService.getRepositories("RafGra2022");

		Assertions.assertEquals(1, repositories.size());
	}
	
	@Test
	public void shouldReturnEmptyList() {

		List<GithubRepositoryDto> userRepositoryDtos = List.of();

		Mockito.when(githubRepository.getRepositories(Mockito.any())).thenReturn(userRepositoryDtos);

		List<GithubRepositoryDto> repositories = githubService.getRepositories("RafGra2022");

		Assertions.assertEquals(0, repositories.size());
	}
}
