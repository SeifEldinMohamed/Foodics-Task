package com.example.foodicstask.di


import com.example.foodicstask.data.data_sources.remote.FoodRemoteDataSource
import io.ktor.client.HttpClient
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.definition
import org.koin.test.verify.injectedParameters
import org.koin.test.verify.verify

class VerifyKoinConfiguration {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun checkKoinModule() {
        appModule.verify(
            injections = injectedParameters(
                definition<FoodRemoteDataSource>(HttpClient::class)
            )
        )
    }
}

/**
 * Why Test Koin Modules?
 * Catch Configuration Errors Early:
 *
 * Mistakes like missing or misconfigured dependencies can cause runtime crashes.
 * Testing ensures all dependencies are properly declared and resolvable.
 * Verify Dependency Graph:
 *
 * Confirms that the dependencies defined in your modules are complete and have no circular dependencies.
 * Ensure Maintainability:
 *
 * As your application grows and modules change, this test ensures that new configurations don't break the existing setup.
 * Avoid Runtime Surprises:
 *
 * Koin resolves dependencies at runtime, so errors in your dependency graph might not surface until those dependencies are actually needed.
 * Testing catches these issues beforehand.
 *
 * How This Test Works
 * Loads the modules you want to test. These should include all the modules used in your app.
 *
 * What verify() does:
 * It performs a static check of the modules to ensure all classes and their constructors can be properly instantiated.
 * It checks all declared dependencies and validates their bindings and resolutions.
 * If any of the dependencies can't be resolved or there's any issue in the dependency graph (like missing definitions or circular dependencies),
 * the verify() function will throw an exception, helping you identify problems early.
 *
 * (optional) Declare list of ParameterTypeInjection - in order to help define parameter injection types to allow in verify
 *
 * What Happens During the Test
 * Koin simulates the resolution of all declared dependencies without running the app.
 * If there’s an issue (e.g., a missing dependency or a misconfigured factory),
 * the test will fail with an error message pointing to the problematic dependency.
 *
 * **/