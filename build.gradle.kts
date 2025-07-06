plugins {
	id("org.jetbrains.kotlin.jvm") version "1.9.10"
	id("org.jetbrains.kotlin.plugin.spring") version "1.9.10"
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.lixlim"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

sourceSets {
	named("main") {
		java.srcDirs("src/main/kotlin")
	}
	named("test") {
		java.srcDirs("src/test/kotlin")
	}
}

tasks.test {
	useJUnitPlatform()
}
