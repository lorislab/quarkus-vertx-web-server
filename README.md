# quarkus-vertx-web-server

Quarkus Vert.x web-server extension.

[![License](https://img.shields.io/github/license/lorislab/vertx-web-server?style=for-the-badge&logo=apache)](https://www.apache.org/licenses/LICENSE-2.0)
[![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/lorislab/vertx-web-server/build/master?logo=github&style=for-the-badge)](https://github.com/lorislab/vertx-web-server/actions?query=workflow%3Abuild)
[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/lorislab/vertx-web-server?sort=semver&logo=github&style=for-the-badge)](https://github.com/lorislab/vertx-web-server/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/org.lorislab.quarkus/vertx-web-server?logo=java&style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/org.lorislab.quarkus/vertx-web-server)

Maven dependency
```xml
<dependency>
    <groupId>org.lorislab.quarkus</groupId>
    <artifactId>vertx-web-server</artifactId>
    <version>{latest-release-version}</version>
</dependency>
```

## Configuration

### Runtime time
* `quarkus.lorislab.vertx.web.server.enabled` - enable or disable web-server. Default: `true`
* `quarkus.lorislab.vertx.web.server.index` - index resources. Default: `index.html,index.htm`
* `quarkus.lorislab.vertx.web.server.dir` - web-server directory. Default: `www`
* `quarkus.lorislab.vertx.web.server.context` - the context of the web-server. Default: `/*`
* `quarkus.lorislab.vertx.web.server.default-content-encoding` - default content encoding. Default: `UTF-8`

