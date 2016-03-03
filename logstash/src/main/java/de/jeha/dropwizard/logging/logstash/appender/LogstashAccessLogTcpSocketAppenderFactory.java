package de.jeha.dropwizard.logging.logstash.appender;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.Encoder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import de.jeha.dropwizard.logging.logstash.encoder.AccessLogLogstashEncoder;
import io.dropwizard.logging.AbstractAppenderFactory;
import net.logstash.logback.appender.LogstashTcpSocketAppender;

import javax.validation.constraints.NotNull;

@JsonTypeName("logstash-access-log-tcp")
public class LogstashAccessLogTcpSocketAppenderFactory extends AbstractAppenderFactory {

    @NotNull
    private String destination;

    @NotNull
    private String applicationName;

    @NotNull
    private String applicationVersion;

    @JsonProperty
    public String getDestination() {
        return destination;
    }

    @JsonProperty
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonProperty
    public String getApplicationName() {
        return applicationName;
    }

    @JsonProperty
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @JsonProperty
    public String getApplicationVersion() {
        return applicationVersion;
    }

    @JsonProperty
    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    @Override
    public Appender<ILoggingEvent> build(LoggerContext context,
                                         String dwApplicationName,
                                         Layout<ILoggingEvent> layout) {
        final LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        final Encoder encoder = new AccessLogLogstashEncoder(applicationName, applicationVersion);

        appender.setName("logstash-access-log-tcp-appender");
        appender.setContext(context);
        appender.addDestination(destination);

        appender.setEncoder(encoder);
        addThresholdFilter(appender, threshold);

        encoder.start();
        appender.start();

        return wrapAsync(appender);
    }

}