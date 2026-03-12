# Deployment Guide

## Local Development (H2 Database)

The app uses **H2 (in-memory/file-based)** for local development:

```bash
# Build
./mvnw clean package -DskipTests

# Run locally
./mvnw spring-boot:run
```

The development database file is stored in `./data/roombooking.h2.db` and persists across restarts.

## Production Deployment on Render.com

### Prerequisites
- GitHub account (push code to a public/private repo)
- Render.com account (free tier available)

### Steps

1. **Push code to GitHub**
   ```bash
   git add .
   git commit -m "Add database persistence and Render config"
   git push origin main
   ```

2. **Create a Render account** and log in to [render.com](https://render.com)

3. **Create a new Web Service:**
   - Connect GitHub repo
   - Select branch (e.g., `main`)
   - Build command: `./mvnw clean package -DskipTests`
   - Start command: `java -jar target/roombooking-0.0.1-SNAPSHOT.jar`
   - Environment variables:
     - `SPRING_PROFILES_ACTIVE=prod`
     - `JAVA_OPTS=-Xmx512m -Xms256m` (optional, for memory limit)

4. **Create a PostgreSQL Database on Render:**
   - Name: `roombooking-db`
   - PostgreSQL 15+
   - Free tier is available
   - Copy the connection string from Render (looks like `postgres://user:password@host:port/database`)

5. **Link the database to the web service:**
   - In the web service environment, add:
     - `DATABASE_URL` = (paste Postgres connection string from Render)
     - `SPRING_PROFILES_ACTIVE=prod`

6. **Deploy:**
   - Render auto-deploys on git push, or manually trigger from dashboard
   - App initializes the schema and seeds sample rooms on first launch
   - Bookings and rooms are persisted in Postgres

### Monitoring

- View logs: Render dashboard → Web Service → Logs
- Database connect via Render console or `psql` CLI
- Sample rooms auto-seed if DB is empty

### Switching Databases

- **Dev (H2):** Set `spring.profiles.active=dev` (default)
- **Prod (Postgres):** Set `spring.profiles.active=prod` + `DATABASE_URL` env var

