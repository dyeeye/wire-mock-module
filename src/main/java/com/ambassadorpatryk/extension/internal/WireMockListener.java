package com.ambassadorpatryk.extension.internal;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.mule.runtime.api.exception.MuleException;
import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.source.EmitsResponse;
import org.mule.runtime.extension.api.runtime.source.Source;
import org.mule.runtime.extension.api.runtime.source.SourceCallback;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.io.InputStream;
import java.util.logging.Logger;

@Alias("listener")
@MediaType("application/json")
@EmitsResponse
public class WireMockListener extends Source<InputStream, WireMockAttributes> {
    private WireMockServer wireMock;

    @Config
    private WireMockConfig config;

    private static Logger logger = Logger.getLogger("WireMockListener");

    @Override
    public void onStart(SourceCallback<InputStream, WireMockAttributes> sourceCallback) throws MuleException {
        wireMock = new WireMockServer(options()
                .extensions(new ResponseTemplateTransformer(true))
                .port(config.getPort())
                .bindAddress(config.getHost()));
        wireMock.start();
        logger.info("Wire Mock started");
    }

    @Override
    public void onStop() {
        wireMock.stop();
        logger.info("Wire Mock stopped");
    }
}
