package org.lorislab.quarkus.vertx.web.server;

import io.quarkus.runtime.annotations.ConfigItem;
import io.quarkus.runtime.annotations.ConfigPhase;
import io.quarkus.runtime.annotations.ConfigRoot;

import java.util.Set;

@ConfigRoot(name = "lorislab.vertx.web.server", phase = ConfigPhase.BUILD_TIME)
public class WebServerRunTimeConfig {

    /**
     * Enabled.
     */
    @ConfigItem(name = "enabled", defaultValue = "true")
    public boolean enabled = true;


    /**
     * Web directory.
     */
    @ConfigItem(name = "dir", defaultValue = "www")
    public String dir;

    /**
     * Web context.
     */
    @ConfigItem(name = "context", defaultValue = "/*")
    public String context;

    /**
     * Default content encoding.
     */
    @ConfigItem(name = "default-content-encoding", defaultValue = "UTF-8")
    public String defaultContentEncoding = "UTF-8";

    /**
     * Index files.
     */
    @ConfigItem(name = "index", defaultValue = "index.html,index.htm")
    public Set<String> index = Set.of("index.html","index.htm");

}
