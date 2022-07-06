package com.adrcanfer.hero.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.service.HeroService;

@Controller
@RequestMapping("/hero")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	private final Logger LOGGER = LogManager.getLogger(getClass());
	
	@GetMapping()
	public ResponseEntity<List<Hero>> getHeros(@RequestParam(required = false) String name) {
		LOGGER.info("INI getHeros with name: {}", name);
		List<Hero> res = heroService.getHeros(name);
		
		LOGGER.info("END getHeros with res: {}", res);
		return new ResponseEntity<List<Hero>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hero> getHeroById(@PathVariable Long id) throws CustomException {
		LOGGER.info("INI getHero with id: {}", id);
		Hero res = heroService.getHeroById(id);
		
		LOGGER.info("END getHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Hero> postHero(@RequestBody Hero body) throws CustomException {
		LOGGER.info("INI postHero with body: {}", body);
		Hero res = heroService.postHero(body); 
		
		LOGGER.info("END postHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<Hero> putHero(@RequestBody Hero body) throws CustomException {
		LOGGER.info("END putHero with body: {}", body);
		Hero res = heroService.putHero(body); 
		
		LOGGER.info("END putHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHeroById(@PathVariable Long id) throws CustomException {
		LOGGER.info("END deleteHero with id", id);
		heroService.deleteHeroById(id);
		
		LOGGER.info("END deleteHero");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
