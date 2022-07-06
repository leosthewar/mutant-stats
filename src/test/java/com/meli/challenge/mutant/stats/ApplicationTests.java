package com.meli.challenge.mutant.stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.meli.challenge.mutant.stats.domain.model.DnaStats;
import com.mongodb.BasicDBObjectBuilder;
/**
 * 
 * ApplicationTests
 * Class with integration tests 
 *
 * @author Leonardo Sthewar Rincon - leo.sthewar.rincon@gmail.com
 * @since 5/07/2022
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	String randomServerPort;

	private URI uri;

	@Before
	public void init() throws URISyntaxException {
		String baseUrl = String.join("", "http://localhost:", randomServerPort, "/stats/");
		this.uri = new URI(baseUrl);
	}

	@Autowired
	private MongoTemplate mongoTemplate;


	/**
	 * Test stats when not exists data
	 */
	@Test
	public void testsStats1Empty() {

		ResponseEntity<DnaStats> result = this.restTemplate.getForEntity(uri, DnaStats.class);
		
		DnaStats dnaStats= result.getBody();
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(0L, dnaStats.getCountHuman().longValue());
		Assert.assertEquals(0L, dnaStats.getCountMutant().longValue());
		Assert.assertEquals(BigDecimal.valueOf(0), dnaStats.getRatio());
	}
	
	/**
	 * Test stats when exists data
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public void testsStats2() {

		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", false).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");
		mongoTemplate.save(BasicDBObjectBuilder.start().add("mutant", true).get(), "dna");

		ResponseEntity<DnaStats> result = this.restTemplate.getForEntity(uri, DnaStats.class);
		DnaStats dnaStats= result.getBody();

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(9, dnaStats.getCountHuman().longValue());
		Assert.assertEquals(6, dnaStats.getCountMutant().longValue());
		Assert.assertEquals(new BigDecimal(0.667).round(new MathContext(3)), dnaStats.getRatio());
	}


}
