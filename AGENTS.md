# Repository Guidelines

## Project Structure & Module Organization
This is a multi-module Gradle build (`settings.gradle.kts`) anchored by `build.gradle.kts`. Domain enums live in `core/core-enum/src/main/kotlin`, shared HTTP/API surfaces and adapters in `core/core-api/src/main/kotlin`, and persistence logic in `storage/src/main/kotlin`. Resources follow the usual Spring layout; for instance, database settings live in `storage/src/main/resources/datasource.yml`. Keep module-specific assets beside their code and mirror package structures under both `src/main/kotlin` and `src/test/kotlin` so Gradle finds them automatically.

## Build, Test, and Development Commands
Use `./gradlew clean build` for a full verification cycle. `./gradlew :core:core-api:bootRun` starts the API layer against the in-memory H2 datasource defined above, while `./gradlew :storage:test` focuses on persistence changes. Dedicated test tasks exist: `./gradlew unitTest` (excludes `develop` and `context` tags), `./gradlew contextTest` (only integration-style `@Tag("context")` suites), and `./gradlew developTest` for fast feedback suites marked `@Tag("develop")`.

## Coding Style & Naming Conventions
Target Java 21/Kotlin 1.9 (see `gradle.properties`). Favor Kotlin idioms: immutability by default, data classes for DTOs, and extension functions for shared utilities. Use 4-space indentation, `lowerCamelCase` for functions/vals, `UpperCamelCase` for classes/enums, and `SCREAMING_SNAKE_CASE` for enum constants. Align package names with `com.jiujitsu` namespaces and keep module prefixes (`storage`, `core.api`) in the directory tree. Run your editor’s Kotlin formatter or ktlint before committing—files should compile lint-free.

## Testing Guidelines
Write JUnit 5 tests beside the code they cover (`src/test/kotlin`). Prefer descriptive method names like `shouldPersistSubmissionWhen...`. Unit tests should mock Spring beans; context tests should lean on `@SpringBootTest` and be tagged `context`. Keep coverage high for entity mappings and Jackson serialization because `core-api` reuses `storage` models via direct project dependencies.

## Commit & Pull Request Guidelines
Adopt Conventional Commit summaries (e.g., `feat(storage): add academy repository`). Keep bodies concise but explain intent, constraints, and follow-up work. Every PR should include: purpose, testing evidence (`./gradlew unitTest` output or logs), impacted modules, and screenshots or sample payloads when API contracts change. Reference related issues and call out schema or configuration updates so reviewers can run migrations or refresh local H2 state.

## Security & Configuration Tips
Never hard-code credentials; rely on `datasource.yml` overrides or environment variables. H2 is fine for local work, but guard production-only settings under a separate profile before pushing. Scrub test data containing personal info, and rotate secrets immediately if accidentally committed.
