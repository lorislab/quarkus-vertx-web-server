package org.lorislab.quarkus.vertx.web.server;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.runtime.RuntimeValue;
import io.quarkus.runtime.annotations.Recorder;
import io.vertx.ext.web.Router;

import io.vertx.ext.web.handler.StaticHandler;

@Recorder
public class WebServerRecorder {

    private static final Logger LOG = LoggerFactory.getLogger(WebServerRecorder.class);
    
    private static volatile Set<String> knownPaths;

	public void init(WebServerRunTimeConfig config, RuntimeValue<Router> router) {
	    if (!config.enabled) {
	        return;
        }
        if (config.dir == null || config.dir.isEmpty()) {
            LOG.warn("The webserver directory is not defined!");
            return;
        }
        if (!Files.exists(Paths.get(config.dir))) {
            LOG.warn("The webserver directory does not exists! Dir: " + config.dir);
            return;
        }
        StaticHandler staticHandler = StaticHandler.create()
        .setWebRoot(config.dir)
        .setAllowRootFileSystemAccess(true)
        .setDefaultContentEncoding(config.defaultContentEncoding);
        knownPaths = paths(config);
        router.getValue().get(config.context).handler(ctx -> {
            String rel = ctx.mountPoint() == null ? ctx.normalisedPath() : ctx.normalisedPath().substring(ctx.mountPoint().length());
            if (knownPaths.contains(rel)) {
                staticHandler.handle(ctx);
            } else {
                ctx.next();
            }
        });            
	}
    
    private Set<String> paths(WebServerRunTimeConfig config) {
        Set<String> tmp = new HashSet<>();
        collectKnownPaths(Paths.get(config.dir), config, tmp);
        return tmp;
    }    

    private void collectKnownPaths(Path resource, WebServerRunTimeConfig config, Set<String> knownPaths) {
        try {
            Files.walkFileTree(resource, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) throws IOException {
                    String simpleName = p.getFileName().toString();
                    String file = resource.relativize(p).toString();
                    if (config.index.contains(simpleName)) {
                        Path parent = resource.relativize(p).getParent();
                        if (parent == null) {
                            knownPaths.add("/");
                        } else {
                            String parentString = parent.toString();
                            if (!parentString.startsWith("/")) {
                                parentString = "/" + parentString;
                            }
                            knownPaths.add(parentString + "/");
                        }
                    }
                    if (!file.startsWith("/")) {
                        file = "/" + file;
                    }
                    // Windows has a backslash
                    file = file.replace('\\', '/');
                    knownPaths.add(file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }    
}
