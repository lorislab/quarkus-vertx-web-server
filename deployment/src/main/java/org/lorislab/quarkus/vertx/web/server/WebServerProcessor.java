package org.lorislab.quarkus.vertx.web.server;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.vertx.http.deployment.VertxWebRouterBuildItem;

public class WebServerProcessor {

    static final String FEATURE_NAME = "vertx-web-server";

    @BuildStep
    public FeatureBuildItem build() {
        return new FeatureBuildItem(FEATURE_NAME);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void configureRuntimeProperties(WebServerRecorder recorder,
                                    WebServerRunTimeConfig logRuntimeTimeConfig,
                                    VertxWebRouterBuildItem vertxWebRouterBuildItem) {
        recorder.init(logRuntimeTimeConfig, vertxWebRouterBuildItem.getRouter());
    }    
}
