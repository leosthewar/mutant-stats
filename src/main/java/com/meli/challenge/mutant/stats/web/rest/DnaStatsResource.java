/**
 */
package com.meli.challenge.mutant.stats.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.challenge.mutant.stats.domain.model.DnaStats;
import com.meli.challenge.mutant.stats.service.DnaStatsService;

/**
 * MutantStatsResource
 * @RestController to expose /stats/  endpoint
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 * 
 */
@RestController
@RequestMapping("/")
public class DnaStatsResource {
	
	private static final Logger logger = LoggerFactory.getLogger(DnaStatsResource.class);

	private DnaStatsService dnaStatsService;
	
	public DnaStatsResource(DnaStatsService dnaStatsService) {
		this.dnaStatsService= dnaStatsService;
	}
	
	/**
	 * Expose /stats/ endpoint to obtaint DNA Stats 
	 * @return
	 */
	@GetMapping("/stats/")
    public ResponseEntity<DnaStats> getStats() {
		DnaStats dnaStats= dnaStatsService.getDnaStats();
		logger.info("{}",dnaStats);
		return ResponseEntity.ok().body(dnaStats); 
    }
	
}
