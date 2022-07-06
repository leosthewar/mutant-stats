/**
 */
package com.meli.challenge.mutant.stats.domain.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Dna
 * Class to reference Collection dna 
 * 
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 * 
 */
@Document(collection = "dna")
public abstract class  Dna {

	private boolean mutant;
	private List<String> dna;
}
