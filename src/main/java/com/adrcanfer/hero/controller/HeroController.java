package com.adrcanfer.hero.controller;

import java.util.List;

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
	
	@GetMapping()
	public ResponseEntity<List<Hero>> getHeros(@RequestParam(required = false) String name) {
		List<Hero> res = heroService.getHeros(name); 
		return new ResponseEntity<List<Hero>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Hero> getHeroById(@PathVariable Long id) throws CustomException {
		Hero res = heroService.getHeroById(id); 
		return new ResponseEntity<Hero>(res, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<Hero> postHero(@RequestBody Hero body) throws CustomException {
		Hero res = heroService.postHero(body); 
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@PutMapping()
	public ResponseEntity<Hero> putHero(@RequestBody Hero body) throws CustomException {
		Hero res = heroService.putHero(body); 
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteHeroById(@PathVariable Long id) throws CustomException {
		heroService.deleteHeroById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}


}
