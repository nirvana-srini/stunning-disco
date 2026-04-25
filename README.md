# Stunning Disco Pomodoro Monorepo

Minimal V1 scaffold for a Pomodoro product with:

- Java Spring Boot backend API (`backend/`)
- React web client (`frontend-web/`)
- React Native mobile app via Expo (`mobile-app/`)

## Why this shape

- API-first contract under `/api/v1` for long-term compatibility with web + mobile.
- In-memory repositories now, replaceable with PostgreSQL later without changing endpoints.
- Keycloak-ready resource server configuration via JWT issuer URI.
- WebSocket updates for live timer sync across clients.

## Run backend

```bash
cd backend
mvn spring-boot:run
```

Health endpoint:

```bash
curl http://localhost:8080/actuator/health
```

## Run web

```bash
cd frontend-web
npm install
npm run dev
```

## Run mobile (Expo)

```bash
cd mobile-app
npm install
npm start
```

Set `EXPO_PUBLIC_WEB_APP_URL` if the web app is not on `http://localhost:5173`.

## API endpoints (V1)

- `GET /api/v1/public/timer`
- `POST /api/v1/public/timer/action` with `{ "action": "start|pause|resume|reset|skip" }`
- `POST /api/v1/public/timer/settings`

## Keycloak notes

Configure `KEYCLOAK_ISSUER_URI`, for example:

```bash
export KEYCLOAK_ISSUER_URI=http://localhost:8081/realms/pomodoro
```

Currently, `/api/v1/public/**`, `/actuator/health`, and `/ws/**` are open for rapid V1 shipping.
You can lock these down once your Keycloak realm/client is fully configured.
