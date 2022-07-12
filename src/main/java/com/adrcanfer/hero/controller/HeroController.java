package com.adrcanfer.hero.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrcanfer.hero.annotation.TimeLogger;
import com.adrcanfer.hero.exception.CustomException;
import com.adrcanfer.hero.exception.dto.ErrorResponse;
import com.adrcanfer.hero.model.Hero;
import com.adrcanfer.hero.service.HeroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/hero")
@Tag(name = "Hero", description = "Api de Heroes")
public class HeroController {
	
	@Autowired
	private HeroService heroService;
	
	private final Logger LOGGER = LogManager.getLogger(getClass());
	
	@Operation(summary = "Devuelve el listado de los heroes", tags = { "Hero" })
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Se devuelven los heroes") })
	@PreAuthorize("hasRole('READ')")
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@TimeLogger
	public ResponseEntity<List<Hero>> getHeros(
			@Parameter(description = "Nombre del heroe para filtrar") @RequestParam(required = false) String name) {
		LOGGER.info("INI getHeros with name: {}", name);
		List<Hero> res = heroService.getHeros(name);
		
		LOGGER.info("END getHeros with res: {}", res);
		return new ResponseEntity<List<Hero>>(res, HttpStatus.OK);
	}
	
	@Operation(summary = "Devuelve el detalle del heroe a partir de un id", tags = { "Hero" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Se devuelven el detalle"), 
			@ApiResponse(responseCode = "404", description = "No se ha encontrado ningún heroe con ese id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) 
			})
	@PreAuthorize("hasRole('READ')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	@TimeLogger
	public ResponseEntity<Hero> getHeroById(@Parameter(description = "Identificador del heroe") @PathVariable Long id) throws CustomException {
		LOGGER.info("INI getHero with id: {}", id);
		Hero res = heroService.getHeroById(id);
		
		LOGGER.info("END getHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.OK);
	}
	
	@Operation(summary = "Crea un nuevo heroe", tags = { "Hero" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Se crea el nuevo heroe"),
			@ApiResponse(responseCode = "422", description = "Se ha introducido algún dato incorrecto del heroe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
		})
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@PreAuthorize("hasRole('WRITE')")
	@TimeLogger
	public ResponseEntity<Hero> postHero(@Parameter(description = "Contenido del heroe a persistir") @RequestBody Hero body) throws CustomException {
		LOGGER.info("INI postHero with body: {}", body);
		Hero res = heroService.postHero(body); 
		
		LOGGER.info("END postHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Modifica un heroe existente", tags = { "Hero" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Se modifica el heroe existente"),
			@ApiResponse(responseCode = "422", description = "Se ha introducido algún dato incorrecto del heroe", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
		})
	@RequestMapping(method = RequestMethod.PUT, produces = "application/json")
	@PreAuthorize("hasRole('WRITE')")
	@TimeLogger
	public ResponseEntity<Hero> putHero(@Parameter(description = "Contenido del heroe a persistir") @RequestBody Hero body) throws CustomException {
		LOGGER.info("END putHero with body: {}", body);
		Hero res = heroService.putHero(body); 
		
		LOGGER.info("END putHero with res: {}", res);
		return new ResponseEntity<Hero>(res, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Elimina un heroe existente", tags = { "Hero" })
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Se elimina el heroe existente"),
			@ApiResponse(responseCode = "404", description = "No se ha encontrado ningún heroe con ese id", content = @Content(schema = @Schema(implementation = ErrorResponse.class))) 
		})
	@RequestMapping(value ="/{id}",  method = RequestMethod.DELETE, produces = "application/json")
	@PreAuthorize("hasRole('WRITE')")
	@TimeLogger
	public ResponseEntity<Void> deleteHeroById(@Parameter(description = "Identificador del heroe") @PathVariable Long id) throws CustomException {
		LOGGER.info("END deleteHero with id", id);
		heroService.deleteHeroById(id);
		
		LOGGER.info("END deleteHero");
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
