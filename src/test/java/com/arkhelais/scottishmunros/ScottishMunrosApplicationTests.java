package com.arkhelais.scottishmunros;

import static org.assertj.core.api.Assertions.assertThat;

import com.arkhelais.scottishmunros.controller.MunroController;
import com.arkhelais.scottishmunros.dto.MunroListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ScottishMunrosApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private MunroController munroController;

	@Test
	@DisplayName("Test whether all well with application context")
	public void contextLoads() {
		assertThat(munroController).isNotNull();
	}

	@Test
	@DisplayName("Just after app is up, CSV file should be loaded into memory")
	public void csvFileLoads() {
		MunroListResponse result = restTemplate
				.getForObject("http://localhost:" + port + "/v1/munros", MunroListResponse.class);
		assertThat(result.getTotal()).isEqualTo(602);
		assertThat(result.getFiltered()).isEqualTo(509);
		assertThat(result.getLimit()).isEqualTo(10);
	}

}