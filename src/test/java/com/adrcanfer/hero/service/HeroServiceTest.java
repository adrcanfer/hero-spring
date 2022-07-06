package com.adrcanfer.hero.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.repository.HeroRepository;
import com.adrcanfer.hero.util.DAOUtil;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

	@Mock
	private HeroRepository heroRepository;

	@InjectMocks
	private HeroServiceImpl heroService;

	@Test
	void getHeros_withoutFilterTest() {
		Mockito.when(heroRepository.findAll()).thenReturn(DAOUtil.getHeros());

		List<Hero> res = heroService.getHeros(null);
		
		assertNotNull(res, "No se ha obtenido la respuesta esperada");
		assertEquals(3, res.size(), "La respuesta no tiene el tamaño adecuado");
	}
	
	@Test
	void getHeros_withFilterTest() {
		String name = "Hero";
		Mockito.when(heroRepository.findByNameContainsIgnoreCase(name)).thenReturn(DAOUtil.getHeros());

		List<Hero> res = heroService.getHeros(name);
		
		assertNotNull(res, "No se ha obtenido la respuesta esperada");
		assertEquals(3, res.size(), "La respuesta no tiene el tamaño adecuado");
	}
	
	@Test
	void getHeroById_test() throws CustomException {
		Long id = 1L;
		Mockito.when(heroRepository.findById(id)).thenReturn(Optional.of(DAOUtil.getHero()));

		Hero res = heroService.getHeroById(id);
		
		assertNotNull(res, "No se ha obtenido la respuesta esperada");
	}
	
	@Test
	void getHeroById_notFoundTest() throws CustomException {
		Long id = 1L;
		
		Mockito.when(heroRepository.findById(id)).thenReturn(Optional.empty());

		Hero res;
		try {
			res = heroService.getHeroById(id);
		} catch (CustomException e) { 
			res = null;
		}
		
		assertNull(res, "No se ha obtenido la respuesta esperada");
	}
	
	@Test
	void postHero_test() throws CustomException {
		Hero hero = DAOUtil.getHero();
		
		Mockito.when(heroRepository.save(hero)).thenReturn(hero);
		
		Hero res = heroService.postHero(hero);
		assertNotNull(res, "No se ha obtenido la respuesta esperada");
		assertEquals(hero, res, "No se ha obtenido el mismo heroe");
	}
	
	@Test
	void postHero_invalidNameTest() throws CustomException {
		Hero hero = DAOUtil.getHero();
		hero.setName("");
		
		Hero res;
		try {
			res = heroService.postHero(hero);
		} catch (CustomException e) {
			res = null;
		}
		assertNull(res, "No se ha obtenido la respuesta esperada");
	}
	
	@Test
	void putHero_test() throws CustomException {
		Hero hero = DAOUtil.getHero();
		Mockito.when(heroRepository.save(hero)).thenReturn(hero);
		
		Hero res = heroService.putHero(hero);
		assertNotNull(res, "No se ha obtenido la respuesta esperada");
	}
	
	@Test
	void putHero_invalidIdTest() throws CustomException {
		Hero hero = DAOUtil.getHero();
		hero.setId(null);
				
		Hero res;
		try {
			res = heroService.putHero(hero);
		} catch (CustomException e) {
			res = null;
		}
		assertNull(res, "No se ha obtenido la respuesta esperada");
	}
	
	@Test
	void deleteHero_test() {
		Long id = 1L;
		Mockito.when(heroRepository.findById(id)).thenReturn(Optional.of(DAOUtil.getHero()));
		
		try {
			heroService.deleteHeroById(id);
		} catch (CustomException e) {
			id = null;
		}
		assertNotNull(id, "No se ha obtenido la respuesta esperada");
	}


}
