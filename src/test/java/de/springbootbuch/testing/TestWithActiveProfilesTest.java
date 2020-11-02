package de.springbootbuch.testing;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestWithActiveProfilesTest {
	static class Thing{}
	
	@TestConfiguration
	static class MyConfig {
		@Bean
		@Profile("test") 
		public Thing thing() {
			return new Thing();
		}
	}
	
	@Autowired
	private Thing thing;
	
	@Value("${bar}")
	private String bar;
	
	@Test
	public void contextLoads() {
		assertThat(bar, is(equalTo("some other value")));
	}
}
