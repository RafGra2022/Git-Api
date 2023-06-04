package com.github.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRepositoryResponse{
	
	private List<RepositoryResponse> repositories; 

}
