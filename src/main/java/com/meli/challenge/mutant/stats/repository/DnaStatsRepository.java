package com.meli.challenge.mutant.stats.repository;

import com.meli.challenge.mutant.stats.domain.model.DnaStats;

/**
 * 
 * DnaStatsRepository
 * Interface to DNA Stats Repository
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
public interface  DnaStatsRepository {
	
	DnaStats getCounts();

}
