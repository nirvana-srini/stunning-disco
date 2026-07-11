# 02 — Business Capability Model

**Status:** Draft for architecture review  
**Document owner:** Chief Architect  
**Reviewers:** Product, Engineering, Security, Platform, Operations, Finance  
**Predecessor:** [01 — Vision and Business Goals](./01-vision-and-business-goals.md)  
**Next document after approval:** 03 — Enterprise Architecture

---

## 1. Purpose

This document translates the approved product vision into a stable business capability model for the Focus Platform.

A business capability describes **what the enterprise must be able to do**, independently of a particular team, service, vendor, framework, or deployment model. The model is used to:

- align product strategy and architecture;
- identify platform capabilities shared by web, mobile, administrators, and integrations;
- distinguish differentiating capabilities from commodity capabilities;
- establish capability ownership;
- assess current and target maturity;
- guide build, buy, adopt, and defer decisions;
- sequence the delivery roadmap;
- prevent premature decomposition into microservices.

This document does not define final service boundaries. Those are addressed in the Domain-Driven Design and Enterprise Architecture documents.

---

## 2. Capability-model principles

1. **Capabilities are technology-independent.** A capability remains valid if the implementation changes from Spring Boot to another platform or from self-hosted to managed infrastructure.
2. **Capabilities are stable; processes and applications may change.**
3. **One capability has one accountable business owner.** Multiple teams may contribute, but accountability must be unambiguous.
4. **A capability is not automatically a microservice.** Several capabilities may initially be implemented inside one modular application.
5. **Differentiating capabilities receive product investment.** Commodity capabilities should preferentially use proven products or managed services.
6. **Enterprise features are introduced when justified by customer demand and commercial value.**
7. **Security, tenancy, privacy, observability, and audit are cross-cutting obligations, not optional add-ons.**
8. **Capability maturity must be evidenced.** A deployed component alone does not mean the capability is mature.

---

## 3. Capability hierarchy

The platform is organized into eight Level-1 capability groups.

```text
Focus Platform
├── 1. Focus Experience
├── 2. Work and Goal Management
├── 3. Collaboration and Organization
├── 4. Identity, Tenant and Access Management
├── 5. Commercial and Product Management
├── 6. Integration and Engagement
├── 7. Data, Insight and Intelligence
└── 8. Platform Operations, Trust and Governance
```

---

## 4. Level-1 capability map

| ID | Level-1 capability | Business outcome | Strategic classification |
|---|---|---|---|
| C1 | Focus Experience | Users can enter, sustain, and complete intentional focus sessions | Differentiating |
| C2 | Work and Goal Management | Focus sessions are connected to meaningful tasks, goals, and routines | Differentiating |
| C3 | Collaboration and Organization | Teams can coordinate focus practices without creating employee surveillance | Emerging differentiator |
| C4 | Identity, Tenant and Access Management | People and workloads access the correct tenant and resources securely | Foundational |
| C5 | Commercial and Product Management | Plans, subscriptions, entitlements, usage limits, and lifecycle are controlled | Foundational business platform |
| C6 | Integration and Engagement | The platform connects to user tools and communicates reliably | Selective differentiator |
| C7 | Data, Insight and Intelligence | Users and organizations receive useful, privacy-respecting insight | Differentiating |
| C8 | Platform Operations, Trust and Governance | The product is secure, reliable, supportable, auditable, and economically sustainable | Foundational |

---

## 5. Detailed capability model

## 5.1 C1 — Focus Experience

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C1.1 | Focus Session Lifecycle | Start, pause, resume, skip, reset, complete, and cancel a focus session | Required | Advanced policies and recovery |
| C1.2 | Timer Policy Management | Configure focus, short-break, long-break, rounds, and auto-start behavior | Required | Tenant and plan policies |
| C1.3 | Cross-Device Session Continuity | Maintain authoritative session state across web and mobile clients | Required | Offline conflict resolution and multi-region continuity |
| C1.4 | Session Presence and Synchronization | Publish session changes to active clients in near real time | Required | Scaled fan-out and presence optimization |
| C1.5 | Distraction Management | Help users reduce interruptions during a session | Basic | OS integrations, app blocking, focus modes |
| C1.6 | Focus Rituals and Cues | Support sounds, visual cues, preparation, break guidance, and completion rituals | Basic | Personalised rituals and accessibility profiles |
| C1.7 | Accessibility and Localization | Make the experience usable across accessibility needs, languages, and locales | Baseline | Full localization and regionalisation |
| C1.8 | Offline Focus | Allow limited session use without continuous network availability | Deferred | Required for mature mobile experience |

**Business owner:** Head of Product / Focus Experience  
**Primary product metric:** Meaningful focus sessions completed per active user  
**Architecture significance:** Server-authoritative state, synchronization, offline semantics, idempotent commands, and client consistency.

---

## 5.2 C2 — Work and Goal Management

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C2.1 | Task Management | Create, prioritize, update, schedule, and complete tasks | Basic | Rich task workflows |
| C2.2 | Session-to-Task Association | Link focus sessions and outcomes to tasks | Required | Automatic attribution and recommendations |
| C2.3 | Goal Management | Define personal or team goals and associate work with them | Deferred | Required for growth stage |
| C2.4 | Routine and Habit Management | Create repeatable focus routines and behavioural commitments | Basic | Adaptive habit programs |
| C2.5 | Planning and Prioritization | Plan daily or weekly work and select the next focus target | Basic | Assisted prioritization |
| C2.6 | Work History | Preserve meaningful task and session history | Required | Long-term lifecycle and archival |
| C2.7 | Calendar-Aware Planning | Use calendar availability to plan focus windows | Deferred | External calendar integration |

**Business owner:** Product Manager, Personal Productivity  
**Primary product metric:** Percentage of sessions linked to planned work  
**Architecture significance:** Clear ownership between task, session, routine, and goal models.

---

## 5.3 C3 — Collaboration and Organization

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C3.1 | Workspace Management | Create and manage shared workspaces or organizations | Foundation only | Full enterprise lifecycle |
| C3.2 | Membership Management | Invite, join, suspend, remove, and restore members | Foundation only | Delegated administration |
| C3.3 | Team Focus Coordination | Coordinate shared focus periods or team rituals | Deferred | Differentiating team feature |
| C3.4 | Shared Goals and Challenges | Run opt-in goals, challenges, or accountability groups | Deferred | Growth capability |
| C3.5 | Privacy-Preserving Team Insight | Provide aggregate trends without exposing inappropriate individual activity | Deferred | Enterprise differentiator |
| C3.6 | Organization Policy | Apply approved organizational defaults and limits | Deferred | Enterprise requirement |
| C3.7 | Administrative Support | Diagnose membership, access, subscription, and integration issues safely | Basic internal | Governed support console |
| C3.8 | Branding and White Labelling | Configure organization identity and selected presentation attributes | Deferred | Premium capability |

**Business owner:** Product Manager, Teams and Enterprise  
**Primary product metric:** Activated teams with sustained weekly participation  
**Architecture significance:** Tenant lifecycle, delegated administration, privacy boundaries, aggregate analytics, and policy inheritance.

---

## 5.4 C4 — Identity, Tenant and Access Management

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C4.1 | User Authentication | Authenticate users with secure sessions and token issuance | Required | Passwordless and adaptive authentication |
| C4.2 | Account Lifecycle | Register, verify, recover, disable, reactivate, and delete accounts | Required | Automated policy-driven lifecycle |
| C4.3 | Tenant Registry | Maintain the authoritative tenant identity, status, tier, region, and isolation policy | Required foundation | Full control-plane authority |
| C4.4 | Organization Membership | Associate users with one or more tenants and roles | Required foundation | Advanced delegation and group mapping |
| C4.5 | Authorization | Evaluate roles, permissions, tenant membership, entitlements, and resource ownership | Required | Policy-based authorization |
| C4.6 | Federation and Identity Brokering | Connect customer OIDC and SAML identity providers | Deferred | Enterprise requirement |
| C4.7 | Multi-Factor Authentication | Enforce additional authentication factors | Optional baseline | Enterprise policy |
| C4.8 | Workload Identity | Give each service, worker, and automation a distinct identity | Required baseline | Federated short-lived identity |
| C4.9 | Privileged Access Management | Govern platform-admin and support access | Required operational baseline | JIT elevation and approvals |
| C4.10 | Identity Audit | Record material identity, role, and access events | Required | Tamper-evident and SIEM-integrated |

**Business owner:** Identity and Security Platform Owner  
**Primary product metric:** Successful secure access with zero cross-tenant authorization defects  
**Architecture significance:** Keycloak is the authentication platform, but the product tenant registry remains authoritative for commercial tenant state.

---

## 5.5 C5 — Commercial and Product Management

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C5.1 | Product Catalogue | Define plans, editions, add-ons, limits, and regional availability | Simple configuration | Governed catalogue |
| C5.2 | Subscription Lifecycle | Trial, activate, renew, upgrade, downgrade, suspend, and terminate subscriptions | Basic | Full automated lifecycle |
| C5.3 | Entitlement Management | Decide which features and limits a tenant may use | Required foundation | Real-time policy service |
| C5.4 | Billing Account Management | Maintain payer, currency, tax, invoicing, and billing-provider references | Deferred | Required for paid scale |
| C5.5 | Usage Metering | Measure commercially relevant consumption | Basic telemetry | Auditable usage ledger |
| C5.6 | Quota and Limit Enforcement | Enforce users, storage, API, integration, and feature limits | Basic | Distributed enforcement |
| C5.7 | Pricing and Promotion Management | Manage offers, coupons, trials, and grandfathered pricing | Deferred | Growth capability |
| C5.8 | Revenue Operations | Reconcile subscriptions, payment state, refunds, and exceptions | Deferred | Operational capability |
| C5.9 | Feature Lifecycle Management | Control release, rollout, experiment, disablement, and retirement | Basic flags | Governed product operations |

**Business owner:** Commercial Product Owner  
**Primary product metric:** Conversion and retained recurring revenue by plan  
**Architecture significance:** Subscription and entitlement are separate concepts. Authorization may require active subscription, feature entitlement, and available quota.

---

## 5.6 C6 — Integration and Engagement

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C6.1 | Notification Delivery | Deliver email, push, and selected in-app notifications | Basic | Multi-channel and preference-aware |
| C6.2 | Notification Preferences | Allow users and tenants to control permitted communication | Basic | Policy and regional consent |
| C6.3 | Calendar Integration | Read or publish focus windows and events | Deferred | Bidirectional integration |
| C6.4 | Task-System Integration | Connect with external task-management tools | Deferred | Connector ecosystem |
| C6.5 | Webhook Management | Configure, sign, deliver, retry, suspend, and audit outbound webhooks | Deferred | Enterprise requirement |
| C6.6 | Public API Management | Expose stable, documented, rate-limited APIs | Minimal internal API | Partner API programme |
| C6.7 | Connector Credential Management | Store and rotate external integration credentials | Deferred | Central governed vault integration |
| C6.8 | Import and Export | Support user and tenant data portability | Basic export | Governed bulk import/export |
| C6.9 | Developer Experience | Provide API documentation, examples, SDKs, and test environments | API documentation | Partner portal |

**Business owner:** Integration Product Owner  
**Primary product metric:** Successful integration transactions and retained connected users  
**Architecture significance:** Standard retry, idempotency, credential protection, webhook signing, rate control, and dead-letter handling.

---

## 5.7 C7 — Data, Insight and Intelligence

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C7.1 | Personal Focus Analytics | Show trends, completion, timing, and focus patterns to a user | Basic | Rich insight and recommendations |
| C7.2 | Team and Tenant Analytics | Provide safe aggregate organizational insight | Deferred | Enterprise capability |
| C7.3 | Product Analytics | Measure adoption, conversion, retention, and feature outcomes | Required | Experimentation platform |
| C7.4 | Operational Analytics | Analyse platform health, incidents, capacity, and cost | Required | Forecasting and optimization |
| C7.5 | Data Governance | Classify, catalogue, retain, protect, and delete data | Required baseline | Formal governance programme |
| C7.6 | Analytical Data Platform | Move governed data from operational sources into analytical stores | Deferred | Required at scale |
| C7.7 | Recommendation and Personalization | Recommend routines, durations, or next actions | Deferred | Differentiating AI capability |
| C7.8 | AI-Assisted Productivity | Provide explainable assistance without undermining user agency or privacy | Deferred | Strategic differentiator |
| C7.9 | Data Subject Rights | Support access, correction, export, restriction, and deletion | Required baseline | Automated compliance workflows |

**Business owner:** Data and Product Insights Owner  
**Primary product metric:** Improvement in user outcomes attributable to actionable insight  
**Architecture significance:** Separation of operational and analytical workloads, privacy controls, consent, lineage, and tenant-aware aggregation.

---

## 5.8 C8 — Platform Operations, Trust and Governance

| ID | Level-2 capability | Description | V1 target | Future target |
|---|---|---|---|---|
| C8.1 | Application Delivery | Build, test, scan, sign, deploy, promote, and roll back changes | Required | Progressive delivery |
| C8.2 | Runtime Platform Management | Operate compute, networking, ingress, service identity, policy, and scaling | Required | Self-service platform product |
| C8.3 | Configuration and Secrets Management | Govern application configuration and sensitive credentials | Required | Dynamic and federated secrets |
| C8.4 | Observability | Collect and correlate logs, metrics, traces, events, and user-impact signals | Required | Tenant and SLO-aware observability |
| C8.5 | Reliability Engineering | Define SLOs, error budgets, resilience patterns, capacity, and failure testing | Baseline | Mature SRE practice |
| C8.6 | Incident and Problem Management | Detect, respond, communicate, learn, and prevent recurrence | Required baseline | Automated response and learning |
| C8.7 | Backup and Disaster Recovery | Protect and restore identity, data, configuration, files, and audit records | Required | Cross-region recovery |
| C8.8 | Security Operations | Detect threats, manage vulnerabilities, coordinate response, and collect evidence | Required baseline | Integrated SOC/SIEM |
| C8.9 | Audit and Compliance | Record material actions and support policy, evidence, and assurance obligations | Required | Formal compliance readiness |
| C8.10 | Customer Support Operations | Provide safe diagnostics, health views, and controlled remediation | Basic | Tenant health and support console |
| C8.11 | Cost and Capacity Management | Forecast demand and manage cost per tenant, workload, and environment | Basic | FinOps and unit economics |
| C8.12 | Architecture Governance | Maintain principles, ADRs, standards, fitness functions, and review gates | Required | Continuous governance |
| C8.13 | Service Ownership | Assign accountable owners, escalation, lifecycle, and support readiness | Required | Service catalogue automation |
| C8.14 | Software Supply Chain Security | Govern dependencies, provenance, images, SBOMs, and release integrity | Required baseline | Signed attestations and policy enforcement |

**Business owner:** VP Engineering / Platform and Operations  
**Primary product metric:** SLO attainment with sustainable cost and acceptable operational risk  
**Architecture significance:** The platform must be operable and governed as a product, not merely deployed infrastructure.

---

## 6. Capability classification

### 6.1 Differentiating capabilities

These directly influence why customers choose and continue using the product:

- Focus Session Lifecycle;
- Cross-Device Session Continuity;
- Session-to-Task Association;
- Routine and Habit Management;
- Privacy-Preserving Team Insight;
- Personal Focus Analytics;
- Recommendation and Personalization;
- AI-Assisted Productivity;
- selected collaboration and integration experiences.

**Investment policy:** Prefer internal product ownership and deliberate design. Avoid outsourcing the core user experience or unique behavioural model.

### 6.2 Foundational capabilities

These are necessary for trust, scale, and enterprise readiness but are not usually the primary reason a customer buys:

- identity and access;
- tenant registry;
- subscriptions and entitlements;
- audit;
- observability;
- deployment automation;
- data governance;
- security operations;
- disaster recovery;
- architecture governance.

**Investment policy:** Standardize aggressively. Use proven open-source or managed products where they reduce undifferentiated engineering effort.

### 6.3 Commodity capabilities

Examples include:

- email transport;
- payment processing;
- object storage;
- managed database infrastructure;
- CDN, WAF, and DDoS protection;
- secrets storage;
- commodity metrics and log storage;
- mobile push transport.

**Investment policy:** Prefer buy or adopt over custom build, while retaining an abstraction only where portability or commercial leverage is justified.

---

## 7. Build, buy, adopt, or defer recommendations

| Capability | Recommendation | Rationale |
|---|---|---|
| Focus session engine | Build | Core product behaviour and differentiator |
| Cross-device synchronization | Build using standard protocols | Product-specific consistency semantics |
| Task and routine model | Build | Central to product value and analytics |
| Authentication and federation | Adopt Keycloak | Mature IAM capability; avoid custom authentication |
| Tenant registry | Build | Product authority for tenant state and isolation policy |
| Authorization enforcement | Build shared platform libraries; adopt standards | Domain rules are product-specific, token handling is standardizable |
| Payments | Buy | Regulatory and operational complexity is not differentiating |
| Subscription state | Build thin internal authority integrated with billing provider | Product must not depend solely on provider-specific state |
| Entitlement management | Build initially; evaluate product at scale | Strong connection to pricing and product capabilities |
| Feature flags | Adopt | Mature tools exist; avoid custom rollout infrastructure |
| Workflow orchestration | Adopt when provisioning becomes multi-step and durable | Reliability complexity warrants a proven engine |
| Notifications | Build orchestration; buy transport | Templates, policy, and audit are product concerns; delivery transport is commodity |
| Observability stack | Adopt | Standards and mature ecosystems exist |
| Audit model | Build event contract; adopt durable storage/search | Audit semantics are product-specific |
| Kubernetes platform | Adopt managed Kubernetes when production economics justify it | Reduce control-plane operations |
| Secrets management | Adopt | Security-critical commodity capability |
| Product analytics | Adopt collection tooling; build governed semantic model | Accelerate learning while preserving data ownership |
| AI recommendations | Defer until sufficient trusted behavioural data exists | Avoid premature AI without evidence, consent, or evaluation |
| Multi-region active-active | Defer | Complexity not justified without contractual or scale requirements |
| Dedicated tenant environments | Defer but preserve architectural option | Premium isolation capability for later enterprise demand |

---

## 8. Current and target maturity model

### 8.1 Maturity definitions

| Level | Name | Definition |
|---|---|---|
| M0 | Absent | Capability is not available |
| M1 | Ad hoc | Capability exists as a manual or fragile implementation |
| M2 | Repeatable | Documented and consistently executed, with basic ownership |
| M3 | Managed | Measured, automated, secured, and operationally owned |
| M4 | Optimized | Continuously improved using evidence, policy, and economic feedback |

### 8.2 Initial maturity assessment

The assessment below reflects the current minimal repository and the approved target direction. It must be updated after proof-of-architecture work.

| Capability group | Current | MVP target | Enterprise target | Main gap |
|---|---:|---:|---:|---|
| C1 Focus Experience | M1 | M2 | M4 | Durable state, user ownership, offline and sync semantics |
| C2 Work and Goal Management | M0–M1 | M2 | M4 | Persistent task/routine model and analytics |
| C3 Collaboration and Organization | M0 | M1 | M3 | Tenant, membership, privacy, administration |
| C4 Identity, Tenant and Access | M1 | M2 | M4 | Enforced authentication, authoritative tenancy, workload identity |
| C5 Commercial and Product Management | M0 | M1 | M3 | Subscription, entitlement, metering, billing integration |
| C6 Integration and Engagement | M0 | M1 | M3 | Notification orchestration, APIs, webhooks, credentials |
| C7 Data, Insight and Intelligence | M0 | M1–M2 | M4 | Governance, analytical model, privacy-preserving insight |
| C8 Platform Operations, Trust and Governance | M1 | M2 | M4 | CI/CD, SLOs, audit, DR, security operations, ownership |

---

## 9. Capability heat map

| Capability group | Strategic importance | Current risk | Investment priority |
|---|---|---|---|
| Focus Experience | Very high | High | Immediate |
| Work and Goal Management | High | High | Immediate |
| Collaboration and Organization | Medium now / High later | Medium | Foundation now, expand later |
| Identity, Tenant and Access | Very high | Critical | Immediate |
| Commercial and Product Management | High | High | Foundation in MVP |
| Integration and Engagement | Medium | Medium | Selective |
| Data, Insight and Intelligence | High | High | Governance now, advanced insight later |
| Platform Operations, Trust and Governance | Very high | Critical | Immediate |

---

## 10. Capability ownership model

The following ownership model is the target. Early-stage delivery may combine roles, but accountabilities must remain explicit.

| Capability group | Accountable owner | Product/data owner | Technology owner | Operational owner |
|---|---|---|---|---|
| C1 Focus Experience | Head of Product | Focus Product Manager | Application Engineering Lead | Product Operations |
| C2 Work and Goal Management | Head of Product | Productivity Product Manager | Application Engineering Lead | Product Operations |
| C3 Collaboration and Organization | Enterprise Product Lead | Teams Product Manager | Enterprise Application Lead | Customer Operations |
| C4 Identity, Tenant and Access | CISO or delegated Identity Owner | Identity Product Owner | Identity Platform Lead | Identity Operations |
| C5 Commercial and Product Management | Commercial Lead | Monetization Product Manager | Commercial Platform Lead | Revenue Operations |
| C6 Integration and Engagement | Product/Partnerships Lead | Integration Product Owner | Integration Platform Lead | Integration Operations |
| C7 Data, Insight and Intelligence | Data/Product Executive | Data Product Owner | Data Platform Lead | Data Operations |
| C8 Platform Operations, Trust and Governance | VP Engineering | Platform Product Owner | Platform Engineering Lead | SRE/Operations Lead |

### Ownership rules

- Every production capability must have a named accountable owner.
- Every authoritative dataset must have a data owner.
- Every deployable service must have an operational owner and escalation path.
- Security accountability cannot be delegated entirely to a central security team.
- A capability owner approves policy; an implementation team owns delivery within approved standards.
- Capability ownership must be recorded in a service and capability catalogue.

---

## 11. Capability dependencies

```text
Focus Experience
 ├── depends on Identity and Access
 ├── depends on Task and Routine Management
 ├── emits data to Insight and Intelligence
 └── depends on Platform Operations

Collaboration and Organization
 ├── depends on Tenant Registry and Membership
 ├── depends on Entitlements
 ├── depends on Privacy and Data Governance
 └── depends on Notifications and Integrations

Commercial Management
 ├── drives Entitlements and Quotas
 ├── consumes Usage Metering
 └── controls Tenant commercial state

All business capabilities
 ├── depend on Audit, Observability and Security
 ├── use standard deployment and configuration capabilities
 └── comply with data lifecycle and architecture governance
```

### Critical dependency decisions

1. Focus APIs must not become dependent on a synchronous billing-provider call.
2. Entitlements should be locally available with a defined consistency model.
3. Authentication availability and tenant authorization are Tier-0 dependencies.
4. Analytics failure must not prevent completion of a focus session.
5. Notification-provider failure must degrade gracefully and be retried asynchronously.
6. Support tooling must use governed APIs rather than unrestricted database updates.
7. Identity-provider state must not be the sole authority for tenant commercial lifecycle.

---

## 12. Capability-to-value-stream alignment

| Value stream | Trigger | Outcome | Main capabilities |
|---|---|---|---|
| Discover and Activate | New visitor or invitation | Activated user with first meaningful session | C4, C1, C6, C7 |
| Plan and Focus | User selects work | Completed focus session linked to useful work | C2, C1, C7 |
| Review and Improve | Sufficient history exists | User understands patterns and adapts routines | C7, C2, C1 |
| Form and Operate a Team | Customer creates workspace | Governed tenant with members and policies | C3, C4, C5, C8 |
| Subscribe and Entitle | Trial or purchase event | Correct plan, features, quotas, and billing state | C5, C4, C8 |
| Integrate Customer Tools | Integration configured | Reliable governed data exchange | C6, C4, C8 |
| Support and Recover | Incident or support request | Service restored safely with complete audit | C8, C4, C7 |
| Offboard and Delete | User or tenant terminates | Access revoked and data handled according to policy | C4, C5, C7, C8 |

---

## 13. Minimum viable capability slice

The first production-oriented slice should prove an end-to-end capability chain rather than maximize feature breadth.

### Required slice

1. User registration and authentication.
2. Authoritative user and tenant identity.
3. One tenant and one membership for each activated account, with an upgrade path to multi-user tenants.
4. Persistent task creation.
5. Persistent focus-session lifecycle.
6. Session-to-task association.
7. Server-authoritative timer state.
8. Web and mobile synchronization.
9. Basic personal session history.
10. Basic entitlement decision.
11. Material audit events.
12. Structured logs, metrics, traces, health, and correlation identifiers.
13. Automated build, test, security scan, and deployment.
14. Backup and tested restore of persistent state.
15. A second-tenant test proving cross-tenant access is denied.

### Capabilities explicitly not required in the first slice

- customer SAML federation;
- active-active multi-region deployment;
- advanced team analytics;
- AI recommendations;
- white labelling;
- dedicated tenant environments;
- usage-based billing;
- broad connector marketplace;
- complex microservice decomposition.

---

## 14. Capability release waves

### Wave 1 — Product and trust foundation

- C1.1–C1.4 core focus session capabilities;
- C2.1, C2.2 and C2.6 task association and history;
- C4.1–C4.5 user, tenant, membership and authorization;
- C5.1–C5.3 simple catalogue, subscription state and entitlement;
- C7.3 and C7.5 product analytics and data governance baseline;
- C8.1–C8.5 delivery, runtime, secrets, observability and reliability;
- C8.7, C8.9, C8.12 and C8.13 backup, audit, architecture governance and ownership.

### Wave 2 — Product enablement

- richer routines and planning;
- notifications and preferences;
- feature lifecycle management;
- usage metering and quota enforcement;
- support diagnostics;
- improved personal analytics;
- operational SLOs and incident management.

### Wave 3 — Teams and commercial scale

- workspace and membership administration;
- paid subscription integration;
- privacy-preserving team insight;
- webhooks and selected integrations;
- tenant policy and delegated administration;
- mature data-subject workflows.

### Wave 4 — Enterprise readiness

- OIDC and SAML federation;
- MFA and enterprise authentication policy;
- advanced workload identity;
- privileged access controls;
- enterprise audit and compliance evidence;
- dedicated data-isolation options;
- tested regional disaster recovery.

### Wave 5 — Intelligent and ecosystem platform

- governed analytical platform;
- personalization and explainable recommendations;
- partner APIs and connector ecosystem;
- regional tenant placement;
- premium deployment and data-residency models.

---

## 15. Capability measures

| Capability | Example measure |
|---|---|
| Focus Session Lifecycle | Session command success rate; completion rate |
| Cross-Device Continuity | State convergence time; conflict rate |
| Task Association | Percentage of completed sessions linked to tasks |
| Tenant Isolation | Cross-tenant security test pass rate; confirmed leakage incidents |
| Authentication | Login success rate; p95 login latency; account takeover rate |
| Authorization | Denied invalid access; policy-evaluation latency; authorization defects |
| Subscription | State reconciliation accuracy; activation time |
| Entitlements | Decision latency; stale-decision rate; enforcement accuracy |
| Notifications | Delivery success; retry age; opt-out compliance |
| Analytics | Data freshness; completeness; insight engagement |
| Observability | Trace coverage; actionable alert ratio; time to detect |
| Reliability | SLO attainment; error-budget burn; change failure rate |
| Disaster Recovery | Achieved RTO/RPO; restore-test success |
| Support Operations | Time to diagnosis; privileged-action audit completeness |
| Cost Management | Cost per active user, tenant, and completed focus session |

---

## 16. Key business rules derived from the capability model

1. A tenant is a product and commercial concept, not merely a Keycloak organization.
2. A user may eventually belong to multiple tenants; tenant context must be explicit.
3. Tenant-owned data must have an immutable tenant identifier or equivalent physical isolation.
4. A user authorization decision may require membership, role, resource ownership, subscription status, entitlement, and quota.
5. A focus session is a business record, not only transient UI timer state.
6. Timer commands must be idempotent or safely deduplicated.
7. Analytics collection must not block core focus operations.
8. Team analytics must follow approved privacy and aggregation policies.
9. Subscription provider state must be reconciled into an internal authoritative model.
10. Customer-specific requirements should be satisfied by configuration, entitlement, policy, integration, or extension—not code forks.
11. Support access must be temporary, scoped, visible where appropriate, and fully audited.
12. User and tenant offboarding must revoke access and invoke a governed data lifecycle.

---

## 17. Risks and capability gaps

| Risk | Capability impact | Required response |
|---|---|---|
| Product remains only a timer utility | C1, C2, C7 | Validate task context, routines, outcomes, and insight |
| Identity is configured but not enforced | C4, C8 | Make protected-by-default security part of the first slice |
| Tenant model is added too late | C3, C4, C5, C7 | Introduce stable tenant identity before significant persistence |
| Keycloak becomes tenant system of record | C4, C5 | Maintain an authoritative product tenant registry |
| Premature microservices slow delivery | All | Use modular boundaries first and split based on evidence |
| Shared data causes cross-tenant exposure | C3, C4, C7, C8 | Repository controls, tests, database policies and audit |
| Team analytics becomes surveillance | C3, C7 | Consent, aggregation, minimum group size, policy and transparency |
| Billing-provider outage blocks product use | C5 | Internal state, asynchronous reconciliation and grace policy |
| Commodity services consume core team capacity | C6, C8 | Adopt managed or proven solutions |
| AI is introduced without trusted data or evaluation | C7 | Defer until governance, consent, data quality and metrics exist |
| No accountable owner exists for platform capabilities | C8 | Establish named ownership before production |
| Logging and tracing costs grow without control | C8 | Sampling, retention, classification and unit-cost measures |

---

## 18. Decisions requested from reviewers

Reviewers are asked to approve or amend the following positions:

1. The eight Level-1 capability groups provide sufficient coverage of the target platform.
2. Focus Experience, Work and Goal Management, and privacy-respecting insight are the primary differentiators.
3. Identity, tenancy, commercial control, and platform operations are mandatory foundations rather than later operational additions.
4. A capability does not imply a separate microservice.
5. Keycloak is adopted for identity, while the tenant registry remains the authoritative product source for tenant state.
6. Subscription, entitlement, and usage are separate capabilities.
7. Authentication, authorization, tenant isolation, audit, observability, backup, and automated delivery belong in the first production-oriented slice.
8. Enterprise federation, dedicated tenant environments, active-active multi-region, and AI recommendations are deferred until justified.
9. Commodity delivery mechanisms such as payments, email transport, secrets storage, and infrastructure control planes should generally be adopted or purchased.
10. Capability ownership and measurable maturity are required before declaring enterprise readiness.

---

## 19. Review checklist

### Scope and completeness

- [ ] The capability model covers the approved business vision.
- [ ] Important customer, administrator, commercial, operational, data, and security capabilities are not missing.
- [ ] The capability descriptions express what the business needs rather than prescribing services or technologies.

### Strategic classification

- [ ] Differentiating capabilities are correctly identified.
- [ ] Foundational and commodity capabilities are correctly classified.
- [ ] Build, buy, adopt, and defer recommendations are commercially and technically reasonable.

### Ownership and governance

- [ ] Each Level-1 capability has a clear accountable owner role.
- [ ] Data and operational ownership expectations are acceptable.
- [ ] The model will not force unnecessary centralized control or architecture bottlenecks.

### Sequencing

- [ ] The minimum viable capability slice is sufficient to prove product value and architecture risk.
- [ ] Deferred enterprise capabilities are appropriate.
- [ ] Release waves reflect realistic dependencies.

### Architecture implications

- [ ] Tenant identity is introduced early enough.
- [ ] Subscription, entitlement, and usage are correctly separated.
- [ ] Core operations do not synchronously depend on billing, analytics, or notification providers.
- [ ] The model supports modular-first implementation and later service decomposition.
- [ ] Privacy-preserving collaboration is an explicit capability requirement.

### Approval

- [ ] **Approved**
- [ ] **Approved with changes listed below**
- [ ] **Not approved; revision required**

Reviewer comments:

```text

```

---

## 20. Approval record

| Item | Value |
|---|---|
| Decision | Pending |
| Decision date | Pending |
| Reviewers | Pending |
| Required changes | Pending |
| Approved version/commit | Pending |
