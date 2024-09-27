package org.aodocs.aodocassessmentapp;

import org.aodocs.aodocassessmentapp.logic.service.GoogleAuthorizationService;
import org.aodocs.aodocassessmentapp.logic.service.JwtService;
import org.aodocs.aodocassessmentapp.logic.service.TestSheetSequenceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AodocAssessmentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AodocAssessmentAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(TestSheetSequenceService testSheetSequenceService) throws Exception {
		return (String[] args) -> {
//			testSheetSequenceService.testSequence("p.hamel@cleio.com");
		};
	}

	@Bean
	public CommandLineRunner configureJWTToken(JwtService jwtService) throws Exception {
		return (String[] args) -> {
			jwtService.createJsonWebToken();
		};
	}
}
