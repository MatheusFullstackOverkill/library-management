run_migrations:
	./mvnw clean flyway:migrate -Dflyway.configFiles=flyway.conf