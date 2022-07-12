package com.adrcanfer.hero.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.repository.HeroRepository;

@Service
@CacheConfig(cacheNames = {"heros"})
public class HeroServiceImpl implements HeroService{

	@Autowired
	private HeroRepository heroRepository;
	
	private final Logger LOGGER = LogManager.getLogger(getClass());

	
	@Override
	public List<Hero> getHeros(String name) {
		LOGGER.info("INI getHeros with name {}", name);
		List<Hero> res = null;
		
		if(null == name || name.isBlank()) {
			LOGGER.info("Find heros without filter");
			res = heroRepository.findAll();
		} else {
			LOGGER.info("Find heros filtering by name");
			res = heroRepository.findByNameContainsIgnoreCase(name);
		}

		LOGGER.info("END getHeros with res: {}", res);
		return res;
	}

	@Override
	@Cacheable
	public Hero getHeroById(Long id) throws CustomException {
		LOGGER.info("INI getHero with id: {}", id);
		sleep();
		Optional<Hero> res = heroRepository.findById(id);
		
		if(!res.isPresent()) {
			LOGGER.info("Hero not found");
			throw new CustomException("No se ha encontrado al heroe con id: " + id, HttpStatus.NOT_FOUND);
		}

		
		LOGGER.info("END getHero with res: {}", res);
		return res.get();
	}

	@Override
	@CachePut(key = "T(Long).valueOf(#hero.getId())")
	public Hero postHero(Hero hero) throws CustomException {
		LOGGER.info("INI postHero with body: {}", hero);
		
		validateHero(hero, true);		
		hero = heroRepository.save(hero);

		LOGGER.info("END postHero with res: {}", hero);
		return hero;
	}

	@Override
	@CachePut
	public Hero putHero(Hero hero) throws CustomException {
		LOGGER.info("INI putHero with body: {}", hero);
		
		validateHero(hero, false);
		hero = heroRepository.save(hero);
		
		LOGGER.info("END putHero with res: {}", hero);
		return hero;
	}

	@Override
	@CacheEvict
	public void deleteHeroById(Long id) throws CustomException {
		LOGGER.info("END deleteHero with id", id);

		Hero hero = getHeroById(id);
		heroRepository.delete(hero);
		
		LOGGER.info("END deleteHero");
	}
	
	private void validateHero(Hero hero, boolean isNew) throws CustomException {
		if(hero.getName().isBlank()) {
			LOGGER.error("Invalid hero name");
			throw new CustomException("El campo nombre no puede estar vacío", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		if(!isNew) {
			if(null == hero.getId()) {
				LOGGER.error("Invalid hero id");
				throw new CustomException("El campo id no puede estar vacío", HttpStatus.UNPROCESSABLE_ENTITY);				
			}
		} else {
			hero.setId(null);
		}
	}
	
	private void sleep() {
		try {
			long time = 3000L;
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
