package com.meli.challenge.mutant.stats.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.meli.challenge.mutant.stats.domain.model.DnaStats;
import com.meli.challenge.mutant.stats.repository.DnaStatsRepository;

/**
 * 
 * DnaStatsService
 * Service to get DNA Stats
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@Service
public class DnaStatsService {

	private DnaStatsRepository dnaStatsRepository;
	
	public DnaStatsService(DnaStatsRepository dnaStatsRepository) {
		this.dnaStatsRepository=dnaStatsRepository;
	}
	
	/**
	 * Obtain DNA counts from DB and calculate ratio
	 * @return
	 */
	public DnaStats getDnaStats() {
		DnaStats dnaStats = dnaStatsRepository.getCounts();
		if(dnaStats != null) {
			dnaStats.setRatio(calculateRatio(dnaStats));
		}else{
			dnaStats = new DnaStats();
		}
		return dnaStats;
	}
	
	/**
	 * Method to calculate ratio from  Aggregation
	 * ratio = countMutant / countHuman
	 * @param dnaStats
	 * @return
	 */
	private BigDecimal calculateRatio (DnaStats dnaStats) {
		BigDecimal ratio = BigDecimal.valueOf(0);
		if (dnaStats.getCountMutant() != 0) {
			if (dnaStats.getCountHuman() != 0) {
				BigDecimal countMutant = BigDecimal.valueOf(dnaStats.getCountMutant());
				BigDecimal countHuman = BigDecimal.valueOf(dnaStats.getCountHuman());
				ratio = countMutant.divide(countHuman, 3, RoundingMode.HALF_EVEN);
			} else {
				ratio = BigDecimal.valueOf(-1);
			}
		}
		return ratio;
	}
}
