/**
 */
package com.meli.challenge.mutant.stats.domain.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DnaStats
 * Class to save DNA Stats
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 * 
 */
public class DnaStats {

	@JsonProperty("count_mutant_dna")
	private Long countMutant;
	@JsonProperty("count_human_dna")
	private Long countHuman;
	@JsonIgnore
	private Long  total;
	
	private BigDecimal ratio;
	
	public DnaStats() {
		this.countHuman=0L;
		this.countMutant=0L;
		this.total=0L;
		this.ratio=BigDecimal.valueOf(0);
	}
	

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}


	/**
	 * @return the ratio
	 */
	public BigDecimal getRatio() {
		return ratio;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(BigDecimal ratio) {
		this.ratio = ratio;
	}

	/**
	 * @return the countMutant
	 */
	public Long getCountMutant() {
		return countMutant;
	}

	/**
	 * @param countMutant the countMutant to set
	 */
	public void setCountMutant(Long countMutant) {
		this.countMutant = countMutant;
	}

	/**
	 * @return the countHuman
	 */
	public Long getCountHuman() {
		return countHuman;
	}

	/**
	 * @param countHuman the countHuman to set
	 */
	public void setCountHuman(Long countHuman) {
		this.countHuman = countHuman;
	}

	@Override
	public String toString() {
		return "DnaStats [countMutant=" + countMutant + ", countHuman=" + countHuman + ", total=" + total
				+ ", ratio=" + ratio + "]";
	}
	
	
	
}
