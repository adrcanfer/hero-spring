package com.adrcanfer.hero.util;

import java.util.ArrayList;
import java.util.List;

import com.adrcanfer.hero.model.Hero;

public class DAOUtil {
	
	private static Long heroCounter = 0L;
	
	private DAOUtil() { }
	
	public static Hero getHero() {
		Hero hero  = new Hero();
		hero.setId(++heroCounter);
		hero.setName("Hero " + heroCounter);
		
		return hero;
	}
	
	public static List<Hero> getHeros() {
		List<Hero> res = new ArrayList<>();
		
		res.add(getHero());
		res.add(getHero());
		res.add(getHero());
		
		return res;
	}
 
}
