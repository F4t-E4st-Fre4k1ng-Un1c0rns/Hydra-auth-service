run:
	docker compose unpause app

stop:
	docker compose pause app

update-api:
	docker compose down
	mvnw clean package
	docker compose up --build -d

start:
	mvnw clean package
	docker compose up --build -d