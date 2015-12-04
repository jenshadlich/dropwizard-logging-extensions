package de.jeha.dropwizard.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.AbstractAppenderFactory;
import net.logstash.logback.appender.LogstashSocketAppender;

import javax.validation.constraints.NotNull;

@JsonTypeName("logstash")
public class LogstashSocketAppenderFactory extends AbstractAppenderFactory {

    @NotNull
    private String host;

    @NotNull
    private Integer port;

    @NotNull
    private String tag;

    @JsonProperty
    public Integer getPort() {
        return port;
    }

    @JsonProperty
    public void setPort(Integer port) {
        this.port = port;
    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public void setHost(String host) {
        this.host = host;
    }

    @JsonProperty
    public String getTag() {
        return tag;
    }

    @JsonProperty
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public Appender<ILoggingEvent> build(LoggerContext context, String applicationName, Layout<ILoggingEvent> layout) {
        final LogstashSocketAppender appender = new LogstashSocketAppender();

        appender.setName("logstash-appender");
        appender.setContext(context);
        appender.setHost(host);
        appender.setPort(port);
        appender.setTimeZone("UTC");
        appender.setCustomFields("{\"tags\":\"" + tag + "\"}");

        addThresholdFilter(appender, threshold);
        appender.start();

        return wrapAsync(appender);
    }

}