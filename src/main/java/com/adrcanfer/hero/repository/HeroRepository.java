package com.adrcanfer.hero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adrcanfer.hero.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {
	
	List<Hero> findByNameContainsIgnoreCase(String name);
	
}
