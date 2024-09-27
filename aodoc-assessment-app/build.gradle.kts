plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "org.aodocs"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.api-client:google-api-client:2.0.0")
	implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
	implementation("com.google.apis:google-api-services-sheets:v4-rev20220927-2.0.0")
	implementation("com.google.apis:google-api-services-drive:v3-rev20220815-2.0.0")
	implementation("org.springframework.boot:spring-boot-starter:3.3.4")
	implementation("org.springframework.boot:spring-boot-starter-validation:3.3.4") // dto validation annotations
	implementation("org.springframework.boot:spring-boot-starter-web:3.3.4") // controller annotations
//	implementation("org.springframework.boot:spring-boot-starter-security:3.3.4")
//	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server:3.3.4")

	implementation("commons-logging:commons-logging:1.3.4")
	implementation("com.auth0:java-jwt:4.4.0")

	implementation("com.googlecode.libphonenumber:libphonenumber:8.13.46") // phone number utils

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
