plugins {
	val kotlinVersion = "1.9.20"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion
	id("com.google.devtools.ksp") version "1.9.20-1.0.14"
	application
}

application {
	mainClass.set("App")
}

repositories {
	mavenCentral()
	maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
	implementation(kotlin("stdlib"))

	implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.1")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")
	implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
}
