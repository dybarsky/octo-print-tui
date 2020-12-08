plugins {
	kotlin("jvm") version "1.4.21"
	kotlin("kapt") version "1.4.21"
	application
}

application {
	mainClassName = "Main"
}

repositories {
	jcenter()
	mavenCentral()
	maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
	implementation(kotlin("stdlib"))

	implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.moshi:moshi-kotlin:1.11.0")

	kapt("com.squareup.moshi:moshi-kotlin-codegen:1.11.0")
}
