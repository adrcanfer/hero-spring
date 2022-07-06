package com.adrcanfer.hero.service;

import java.util.List;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;

public interface HeroService {
	
	List<Hero> getHeros(String name);
	
	Hero getHeroById(Long id) throws CustomException;
	
	Hero postHero(Hero hero) throws CustomException;
	
	Hero putHero(Hero hero) throws CustomException;
	
	void deleteHeroById(Long id) throws CustomException;




}
