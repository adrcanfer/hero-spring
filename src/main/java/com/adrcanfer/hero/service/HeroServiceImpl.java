package com.adrcanfer.hero.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.repository.HeroRepository;

@Service
public class HeroServiceImpl implements HeroService{

	@Autowired
	private HeroRepository heroRepository;
	
	@Override
	public List<Hero> getHeros(String filter) {
		List<Hero> res = null;
		
		if(null == filter || filter.isBlank()) {
			res = heroRepository.findAll();
		}

		return res;
	}

	@Override
	public Hero getHeroById(Long id) throws CustomException {
		Optional<Hero> res = heroRepository.findById(id);
		
		if(!res.isPresent()) {
			throw new CustomException("No se ha encontrado al heroe con id: " + id, 404);
		}

		return res.get();
	}

	@Override
	public Hero postHero(Hero hero) throws CustomException {
		validateHero(hero, true);
		
		hero = heroRepository.save(hero);
		
		return hero;
	}

	@Override
	public Hero putHero(Hero hero) throws CustomException {
		validateHero(hero, false);
		
		hero = heroRepository.save(hero);
		
		return hero;
	}

	@Override
	public void deleteHeroById(Long id) throws CustomException {
		Hero hero = getHeroById(id);
		
		heroRepository.delete(hero);
	}
	
	private void validateHero(Hero hero, boolean isNew) throws CustomException {
		if(hero.getName().isBlank()) {
			throw new CustomException("El campo nombre no puede estar vacío", 422);
		}
		
		if(!isNew) {
			if(null == hero.getId()) {
				throw new CustomException("El campo id no puede estar vacío", 422);				
			}
		} else {
			hero.setId(null);
		}
	}

}
