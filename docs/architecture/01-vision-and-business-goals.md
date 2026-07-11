# 01 — Vision and Business Goals

| Attribute | Value |
|---|---|
| Status | **Draft for architecture review** |
| Review gate | Gate 1 — Vision and product direction |
| Decision owner | Product Sponsor / Chief Architect |
| Authors | Chief Architecture function |
| Target repository | `nirvana-srini/stunning-disco` |
| Last updated | 2026-07-11 |
| Next document | 02 — Business Capability Model, only after this document is approved |

## 1. Executive architecture position

Stunning Disco will evolve from a minimal cross-platform Pomodoro timer into a secure, extensible focus-management product platform.

The target is:

> A multi-tenant, API-first focus and productivity SaaS platform that synchronizes intentional work across web and mobile, protects user and tenant data, supports individual and organizational usage, and provides reusable identity, subscription, analytics, integration, and operational capabilities.

The platform is not defined merely by Spring Boot, React, Expo, Keycloak, WebSockets, or Kubernetes. Those are implementation choices. The product is defined by the outcomes it enables:

- helping an individual start, sustain, and learn from focused work;
- maintaining a trustworthy timer and task state across devices;
- enabling teams or organizations to adopt focus practices without intrusive employee surveillance;
- supporting paid plans, entitlements, integrations, and enterprise identity;
- operating predictably as a secure multi-tenant service;
- allowing the architecture to grow incrementally without premature distribution.

## 2. Problem statement

People commonly struggle to convert intentions and task lists into uninterrupted periods of meaningful work. Basic timers help create a work/break rhythm, but users lose continuity when state is fragmented across devices, tasks are disconnected from focus sessions, insights are shallow, or the product becomes distracting itself.

Organizations face a related problem: they may want to encourage healthier focus habits and understand aggregate adoption, but must avoid turning a wellbeing tool into covert activity monitoring.

Stunning Disco should solve these problems through a calm, reliable and privacy-respecting product that connects planning, focus execution, reflection and improvement.

## 3. Market and product observations

The Pomodoro method is based on planned work intervals, short breaks, tracking, recording and reflection. The product should therefore cover more than a countdown timer.

Current focus and time-management products commonly compete through combinations of:

- cross-device synchronization;
- task and project context;
- goals, streaks and visual motivation;
- distraction reduction or blocking;
- personal analytics and reports;
- integrations with calendars and task systems;
- lightweight team capabilities;
- free and paid subscription tiers.

This suggests that a timer-only product is easy to imitate. Sustainable differentiation must come from the quality of the focus workflow, trustworthy synchronization, useful insights, privacy, integration and extensibility.

## 4. Product vision

### 4.1 Vision statement

**Make focused work easy to begin, satisfying to sustain, and useful to learn from—on any device, for individuals and teams, without compromising privacy.**

### 4.2 Product promise

A user should be able to choose meaningful work, begin a focus session within seconds, continue reliably across devices, recover from interruptions, and understand patterns that improve future focus.

An organization should be able to provide this capability to its members with managed identity, policy, entitlements and aggregate insights, while preserving individual dignity and transparent data use.

### 4.3 North-star outcome

The primary product outcome is **completed intentional focus**, not screen time, application open time, raw session count, or employee activity volume.

A candidate north-star metric is:

> Weekly users who complete at least three intentional focus sessions linked to a declared task or goal.

This metric remains provisional until product analytics and user research validate that it correlates with retention and perceived value.

## 5. Target customer segments

### 5.1 Initial segment — individual knowledge workers and learners

Examples include software professionals, students, creators, freelancers and people building consistent study or work habits.

Primary needs:

- fast timer controls;
- task-linked sessions;
- web and mobile continuity;
- configurable work and break intervals;
- history, goals and simple insights;
- reminders that remain supportive rather than noisy.

### 5.2 Growth segment — small teams and learning cohorts

Primary needs:

- team or cohort membership;
- optional shared focus sessions;
- projects or shared goals;
- aggregate progress views;
- role-based administration;
- invitations and subscription management.

### 5.3 Enterprise segment — organizations offering a focus platform

Primary needs:

- customer-managed OIDC or SAML identity;
- tenant isolation and regional options;
- policy and entitlement administration;
- auditable privileged operations;
- integration APIs and webhooks;
- contractual availability, recovery and security controls;
- transparent, privacy-preserving organizational analytics.

Enterprise capabilities are a target state, not a requirement to build every enterprise service in the first release.

## 6. Jobs to be done

### Individual user

- When I need to make progress on something important, help me begin without setup friction.
- When I work across a laptop and phone, preserve one consistent timer and task state.
- When I am interrupted, help me recover without corrupting my history.
- When I complete sessions, show patterns that help me improve rather than merely producing vanity statistics.
- When I use the product regularly, let me control notifications, data retention and privacy.

### Team member

- When my group is working toward a goal, let me participate in shared focus practices without exposing unnecessary personal activity.
- When I switch between private and team work, keep the scopes and data clearly separated.

### Tenant administrator

- When I onboard members, let me manage identity, roles, plans and policies safely.
- When I review adoption, provide transparent aggregate insights with privacy safeguards.
- When I integrate other tools, provide stable APIs, webhooks and auditable credentials.

### Platform operator

- When the service fails or degrades, let me detect user impact, isolate affected tenants and recover predictably.
- When a privileged action is performed, provide sufficient evidence to understand who did what, why and with which scope.

## 7. Business goals

### BG-01 — Validate a differentiated focus workflow

Deliver an experience that combines task selection, timer execution, interruption handling, session completion and reflection.

**Evidence of success**

- users can complete the core journey without assistance;
- meaningful weekly focus completion improves over successive product iterations;
- qualitative research confirms that the workflow is calmer and more useful than a basic timer.

### BG-02 — Establish trustworthy cross-platform continuity

Provide a consistent experience across web and mobile, with server-authoritative session state and safe reconnection.

**Evidence of success**

- one active logical timer is represented consistently on signed-in devices;
- duplicate, stale and out-of-order client actions do not corrupt session state;
- a user can reconnect after network loss and recover the authoritative session.

### BG-03 — Build a viable subscription product

Support a free entry experience and paid value through clearly defined plans and entitlements rather than customer-specific code forks.

**Evidence of success**

- plan capabilities and limits are centrally defined;
- product features can be enabled by entitlement;
- billing-provider integration does not become the source of truth for runtime authorization;
- trial, renewal, grace, suspension and cancellation states are explicit.

### BG-04 — Prepare for secure multi-tenancy

Allow the same platform to serve individuals, teams and enterprise tenants with verifiable isolation.

**Evidence of success**

- every tenant-owned resource has an immutable tenant identity;
- tenant context is validated in gateway, service and persistence paths;
- automated tests prove that Tenant A cannot retrieve or mutate Tenant B resources;
- the platform supports shared-first isolation with a path to stronger enterprise isolation.

### BG-05 — Enable enterprise identity without making identity the business system of record

Use Keycloak for authentication, federation, sessions and organization representation while retaining tenant commercial state in platform-owned services.

**Evidence of success**

- local, social or passwordless login can evolve without changing domain APIs;
- customer OIDC/SAML can be associated with a tenant through controlled onboarding;
- tenant suspension and entitlement decisions are governed outside Keycloak;
- human, administrative, workload and external-integration identities are distinguishable.

### BG-06 — Create an extensible integration foundation

Enable calendars, task managers, notifications and partner systems through stable contracts and asynchronous patterns.

**Evidence of success**

- public APIs follow a versioning and compatibility policy;
- outbound webhooks have signature verification, retries and dead-letter handling;
- integrations use scoped credentials and do not require direct database access;
- domain events have owners and versioned schemas.

### BG-07 — Operate the product with measurable reliability

Define service objectives from user journeys and provide the telemetry, runbooks and recovery mechanisms required to meet them.

**Evidence of success**

- availability, latency, queue delay and recovery objectives are approved;
- critical requests are traceable across gateway, service, database and messaging components;
- backups are restored in scheduled exercises;
- alerts describe user impact rather than only infrastructure utilization.

### BG-08 — Protect trust and privacy as product features

Collect only data required for the declared product purpose and make organizational use transparent.

**Evidence of success**

- personal focus content is private by default;
- team and enterprise analytics use clearly documented aggregation rules;
- the product does not provide hidden surveillance or unrestricted support impersonation;
- export, retention, deletion and consent behavior are testable.

### BG-09 — Maintain delivery speed through a paved engineering path

Give product teams reusable security, tenancy, observability, API, deployment and testing patterns.

**Evidence of success**

- a new service can be created using approved templates;
- architecture fitness checks run in CI and platform policy;
- common concerns are delivered through reusable libraries or platform capabilities;
- architecture governance focuses on consequential decisions rather than routine implementation.

### BG-10 — Control unit economics and avoid premature complexity

Scale architecture investment according to verified demand and criticality.

**Evidence of success**

- major infrastructure costs are attributable by environment and, where practical, tenant;
- modular deployment is preferred until independent scaling, ownership or risk justifies extraction;
- dedicated tenant infrastructure is treated as a priced product option;
- logging, tracing, messaging and storage retention have explicit cost controls.

## 8. Strategic design principles

1. **Focus first.** Every major feature must strengthen planning, focusing, recovery, reflection or sustainable habit formation.
2. **Server-authoritative time.** Clients display and request state changes; the backend protects the logical session timeline.
3. **Privacy by default.** Personal activity is not exposed merely because a user belongs to an organization.
4. **Tenant isolation at every layer.** The gateway is not the only security boundary.
5. **Authentication is centralized; business authorization is contextual.** Domain services enforce roles, tenant membership, entitlement, ownership and business rules.
6. **Human and workload identities are separate.** Background work never depends on replaying a human access token.
7. **Modular first, distributed by evidence.** Deployment boundaries follow scaling, resilience, ownership or compliance needs—not fashion.
8. **Durable workflows for multi-system change.** Provisioning, billing transitions and deletion must survive partial failure.
9. **Contracts over coupling.** APIs and events are versioned products; direct cross-domain database access is prohibited.
10. **Observability and audit are designed in.** Critical behavior must be explainable in production.
11. **Configuration over forks.** Plans, branding, identity and tenant variation use governed configuration and extension points.
12. **Evidence before promotion.** Production readiness requires isolation, load, recovery and security test evidence.

## 9. Product scope

### 9.1 Foundation release scope

- configurable focus/break timer;
- task or goal association;
- session history;
- signed-in user identity;
- authoritative backend session state;
- web/mobile synchronization;
- basic preferences and notifications;
- initial usage analytics;
- API, deployment, security and observability baseline.

### 9.2 Product-platform expansion scope

- teams/tenants and invitations;
- plans, subscriptions and entitlements;
- tenant-aware authorization;
- provisioning workflow;
- calendar/task integrations;
- audit trail;
- exports and retention controls;
- feature management and usage metering.

### 9.3 Enterprise target scope

- customer OIDC/SAML;
- configurable tenant policies and branding;
- stronger isolation tiers;
- regional tenant placement where justified;
- webhooks and enterprise connectors;
- SLO/DR commitments;
- privileged-access governance;
- compliance evidence and data-subject workflows.

## 10. Explicit non-goals

The following are not assumed to be initial-release goals:

- active-active multi-region operation;
- a microservice for every capability;
- invasive device monitoring, screenshots or keystroke tracking;
- clinical diagnosis or treatment claims;
- unrestricted manager visibility into individual focus activity;
- customer-specific application forks;
- an AI assistant making autonomous high-impact decisions;
- running all stateful infrastructure inside Kubernetes;
- building a proprietary identity provider or billing processor.

## 11. Business model hypothesis

The initial commercial hypothesis is a tiered SaaS model:

- **Free:** dependable timer, basic history and limited insights;
- **Personal Pro:** advanced analytics, goals, synchronization options and premium integrations;
- **Team:** tenant workspace, shared goals, administration and aggregate insights;
- **Enterprise:** federation, audit, advanced policy, integration, stronger isolation, regional and support commitments.

This is a hypothesis requiring validation. Architecture must support plan and entitlement evolution without hard-coding pricing or assuming that billing state alone authorizes feature access.

## 12. Candidate outcome scorecard

The exact targets will be approved in the NFR and product analytics documents.

| Dimension | Candidate measure | Initial intent |
|---|---|---|
| Activation | New users completing a first focus session | Validate low-friction onboarding |
| Core value | Weekly users completing 3+ intentional sessions | Candidate north-star behavior |
| Retention | Week-4 retained activated users | Validate recurring value |
| Reliability | Successful timer state transitions | Protect trust in core workflow |
| Continuity | Cross-device recovery success | Validate synchronization |
| Engagement quality | Sessions linked to a task/goal | Favor purposeful use over raw volume |
| Privacy | Confirmed unauthorized tenant disclosures | Target zero |
| Conversion | Eligible active users moving to paid plans | Validate monetizable value |
| Operations | User-impacting incidents and recovery time | Improve reliability maturity |
| Delivery | Lead time for compliant product changes | Measure paved-road effectiveness |
| Economics | Infrastructure and third-party cost per active user/tenant | Keep growth sustainable |

Metrics must be segmented carefully and interpreted ethically. Higher time spent in the application is not automatically a positive outcome for a product whose purpose is focused work.

## 13. Key assumptions requiring validation

| ID | Assumption | Risk if false | Validation approach |
|---|---|---|---|
| A-01 | Users value continuity across web and mobile | Platform complexity provides little benefit | Prototype tests and retention comparison |
| A-02 | Task-linked focus is more valuable than a timer alone | Product lacks differentiation | User interviews and funnel analysis |
| A-03 | Individuals accept accounts for synchronization | Sign-in friction harms activation | Anonymous-to-account conversion experiment |
| A-04 | Team buyers value privacy-preserving aggregate insights | Team plan has weak willingness to pay | Design-partner interviews |
| A-05 | Keycloak can support the required identity lifecycle operationally | Identity platform becomes a bottleneck | Proof of architecture, upgrade and recovery test |
| A-06 | Shared-first data isolation meets early customer needs | Early enterprise deals require dedicated isolation | Customer discovery and contract review |
| A-07 | Subscription tiers can fund operational complexity | Unit economics are unsustainable | Pricing and cost model |
| A-08 | A modular architecture can serve early scale | Premature service extraction or late bottlenecks | Load model and architecture fitness tests |
| A-09 | Users trust transparent focus analytics | Analytics reduces trust or retention | Consent research and privacy review |
| A-10 | AI-enhanced planning can be added later without becoming core infrastructure | Competitive differentiation depends on AI earlier | Market and prototype evaluation |

## 14. Constraints and current-state implications

The repository currently establishes:

- a Spring Boot backend;
- a React web client;
- an Expo/React Native mobile application;
- `/api/v1` as the API compatibility boundary;
- in-memory repositories intended for later persistence replacement;
- Keycloak-ready JWT resource-server configuration;
- WebSocket-based live timer updates;
- open public timer and WebSocket routes for rapid V1 development.

Architecture implications:

1. The existing monorepo is an appropriate foundation for a modular-first product.
2. The in-memory repository boundary should be preserved while PostgreSQL persistence is introduced.
3. The current public timer routes are a temporary development posture, not a production security model.
4. WebSocket synchronization requires an explicit server-authoritative state, reconnection and concurrency design.
5. Keycloak readiness does not yet constitute a complete identity, tenant or authorization architecture.
6. The API-first direction should be retained and expanded through formal contract governance.

## 15. Principal risks

| Risk | Business impact | Initial treatment |
|---|---|---|
| Product remains a commodity timer | Low differentiation and weak retention | Validate task-focus-reflection workflow |
| Cross-device state becomes unreliable | Loss of user trust | Server authority, versioning and concurrency tests |
| Enterprise features distort the consumer experience | Product becomes complex and distracting | Separate personas, progressive disclosure and phased delivery |
| Team analytics becomes surveillance | Reputational, legal and adoption risk | Privacy principles, aggregation and tenant transparency |
| Premature microservices slow delivery | Higher cost and operational burden | Modular-first decision and extraction criteria |
| Shared tenancy defect exposes data | Critical trust and compliance failure | Defense-in-depth, RLS option and adversarial testing |
| Keycloak is treated as all business state | Fragile coupling and lifecycle errors | Dedicated tenant/subscription authority |
| Observability cost grows without control | Poor unit economics | Sampling, retention tiers and cost allocation |
| Subscription design is coupled to one provider | Commercial lock-in | Internal subscription and entitlement model |
| Architectural scope exceeds team capacity | Delayed product validation | Architectural runway aligned to delivery waves |

## 16. Delivery principles

- Prove the smallest end-to-end slice before broad platform construction.
- Add the second tenant early and continuously test isolation.
- Prioritize product and architecture risks over feature count.
- Treat temporary compromises as recorded decisions with exit criteria.
- Require measurable evidence at review gates.
- Keep enterprise target-state compatibility without paying every enterprise cost on day one.

## 17. Review decisions requested

Reviewers are asked to approve, reject or amend the following:

1. The product is positioned as a focus-management platform, not only a Pomodoro timer.
2. The first customer segment is individual knowledge workers and learners, followed by teams and enterprise tenants.
3. Completed intentional focus is the primary outcome; time spent in the app is not the north star.
4. Cross-device, server-authoritative continuity is a core differentiator.
5. Privacy-preserving team use is mandatory; hidden workforce surveillance is out of scope.
6. The architecture follows modular-first evolution with evidence-based extraction.
7. Multi-tenancy, subscriptions, entitlements and enterprise identity are staged platform capabilities.
8. The proposed business goals BG-01 through BG-10 are sufficient to guide the architecture package.
9. The initial non-goals and business-model hypothesis are acceptable.
10. The assumptions requiring validation are complete enough to begin the Business Capability Model.

## 18. Review checklist

Mark each item as **Approved**, **Approved with changes**, or **Rejected**.

- [ ] Vision statement
- [ ] Target customer sequence
- [ ] Jobs to be done
- [ ] Business goals BG-01 through BG-10
- [ ] Strategic design principles
- [ ] Foundation and target scope
- [ ] Explicit non-goals
- [ ] Business model hypothesis
- [ ] Candidate north-star outcome
- [ ] Assumptions and validation plan
- [ ] Principal risks
- [ ] Authorization to proceed to Document 02

## 19. Change log

| Version | Date | Change | Author |
|---|---|---|---|
| 0.1 | 2026-07-11 | Initial Chief Architect draft for review | Chief Architecture function |
