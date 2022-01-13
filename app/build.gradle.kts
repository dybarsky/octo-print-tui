plugins {
	val kotlinVersion = "1.6.10"
	kotlin("jvm") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	kotlin("plugin.serialization") version kotlinVersion
	application
}

application {
	mainClassName = "App"
}

repositories {
	jcenter()
	mavenCentral()
	maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
	implementation(kotlin("stdlib"))

	implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.4")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.okhttp3:okhttp:4.9.3")
	implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
}
