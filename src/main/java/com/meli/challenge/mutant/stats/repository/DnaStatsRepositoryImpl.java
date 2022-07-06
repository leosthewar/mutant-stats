package com.meli.challenge.mutant.stats.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.stereotype.Repository;

import com.meli.challenge.mutant.stats.domain.model.Dna;
import com.meli.challenge.mutant.stats.domain.model.DnaStats;


/**
 * 
 * DnaStatsRepositoryImpl
 * @Repository Implementation of DnaStatsRepository
 *
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@Repository
public class DnaStatsRepositoryImpl implements DnaStatsRepository {

	MongoTemplate mongoTemplate;
	
	public DnaStatsRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate=mongoTemplate;
	}

	/**
	 * Method to get Counts of DNA mutant and not mutant, use Aggregation type group 
	 */
	@Override
	public DnaStats getCounts() {

		Aggregation aggregation = Aggregation.newAggregation(
		group()
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("mutant").equalToValue(true)).then(1).otherwise(0)).as("countMutant")
			.sum(ConditionalOperators
					.when(ComparisonOperators.valueOf("mutant").equalToValue(false)).then(1).otherwise(0)).as("countHuman")
			.count().as("total")
		);
		AggregationResults<DnaStats> result = this.mongoTemplate.aggregate(aggregation, Dna.class, DnaStats.class);
		return result.getUniqueMappedResult();

	}

}
