package com.joke.android.entity;


import java.util.List;

public class JokeWrapper extends Meta{

	private List<JokeContent> jokelist;

	public List<JokeContent> getJokelist() {
		return jokelist;
	}

	public void setJokelist(List<JokeContent> jokelist) {
		this.jokelist = jokelist;
	}
}
