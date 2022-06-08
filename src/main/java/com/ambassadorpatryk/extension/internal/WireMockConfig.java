package com.ambassadorpatryk.extension.internal;

import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Sources(WireMockListener.class)
public class WireMockConfig {

  @Parameter
  @Optional(defaultValue = "8081")
  private int port;

  @Parameter
  @Optional(defaultValue = "0.0.0.0")
  private String host;

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }
}
