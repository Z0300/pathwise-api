# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.2.0] - 2026-07-12

### Added
- TestCase management API including Controller, Service, Repository, and DTOs.
- `DuplicateResourceException` implementation with custom API error codes.
- Validation constraints to FlowNode DTOs (`CreateFlowNodeDto`, `UpdateFlowNodeDto`).
- FlowNode management API and DTO reorganization.

### Changed
- Updated `EnumeratedPathService` with better resource retrieval methods.
- Refactored `TestCase` entity and related DTOs.
- Improved global exception handling for duplicate resources.

## [0.1.1] - 2026-07-10

### Changed
- Refactored DTO folder structure for flows to improve project organization. [[e036811](https://github.com/Z0300/pathwise-api/commit/e036811424d38264010e51b317534083ba843f05)]

### Fixed
- Merged pull request #1 from Z0300/flows. [[93b6a2f](https://github.com/Z0300/pathwise-api/commit/93b6a2fdafc0bf4a4f9e956ead683336b007b98d)]

## [0.1.0] - 2026-07-08

### Added
- Initial project setup with Spring Boot.
- Core entities: `Flow`, `FlowNode`, `FlowEdge`, `EnumeratedPath`, and `TestCase`.
- Flow management API (Controller, Service, Repository, Mapper, DTOs).
- Security configuration and global exception handling.
- Maven wrapper and basic project metadata.
- [[820be87](https://github.com/Z0300/pathwise-api/commit/820be87b769c6275e1438e9bc52f51330f234f07)]

[0.1.1]: https://github.com/Z0300/pathwise-api/compare/820be87...93b6a2f
[0.1.0]: https://github.com/Z0300/pathwise-api/releases/tag/0.1.0
