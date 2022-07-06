package com.adrcanfer.hero.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.repository.HeroRepository;

@Service
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
	public Hero getHeroById(Long id) throws CustomException {
		LOGGER.info("INI getHero with id: {}", id);
		
		Optional<Hero> res = heroRepository.findById(id);
		
		if(!res.isPresent()) {
			LOGGER.info("Hero not found");
			throw new CustomException("No se ha encontrado al heroe con id: " + id, 404);
		}

		
		LOGGER.info("END getHero with res: {}", res);
		return res.get();
	}

	@Override
	public Hero postHero(Hero hero) throws CustomException {
		LOGGER.info("INI postHero with body: {}", hero);
		
		validateHero(hero, true);		
		hero = heroRepository.save(hero);

		LOGGER.info("END postHero with res: {}", hero);
		return hero;
	}

	@Override
	public Hero putHero(Hero hero) throws CustomException {
		LOGGER.info("INI putHero with body: {}", hero);
		
		validateHero(hero, false);
		hero = heroRepository.save(hero);
		
		LOGGER.info("END putHero with res: {}", hero);
		return hero;
	}

	@Override
	public void deleteHeroById(Long id) throws CustomException {
		LOGGER.info("END deleteHero with id", id);

		Hero hero = getHeroById(id);
		heroRepository.delete(hero);
		
		LOGGER.info("END deleteHero");
	}
	
	private void validateHero(Hero hero, boolean isNew) throws CustomException {
		if(hero.getName().isBlank()) {
			LOGGER.error("Invalid hero name");
			throw new CustomException("El campo nombre no puede estar vacío", 422);
		}
		
		if(!isNew) {
			if(null == hero.getId()) {
				LOGGER.error("Invalid hero id");
				throw new CustomException("El campo id no puede estar vacío", 422);				
			}
		} else {
			hero.setId(null);
		}
	}

}
