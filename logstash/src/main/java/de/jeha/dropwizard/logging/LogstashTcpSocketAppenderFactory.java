package de.jeha.dropwizard.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.AbstractAppenderFactory;
import net.logstash.logback.appender.LogstashTcpSocketAppender;
import net.logstash.logback.encoder.LogstashEncoder;

import javax.validation.constraints.NotNull;

@JsonTypeName("logstash-tcp")
public class LogstashTcpSocketAppenderFactory extends AbstractAppenderFactory {

    @NotNull
    private String destination;

    @NotNull
    private String tag;

    @JsonProperty
    public String getDestination() {
        return destination;
    }

    @JsonProperty
    public void setDestination(String destination) {
        this.destination = destination;
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
        final LogstashTcpSocketAppender appender = new LogstashTcpSocketAppender();
        final LogstashEncoder encoder = new LogstashEncoder();

        appender.setName("logstash-tcp-appender");
        appender.setContext(context);
        appender.addDestination(destination);

        encoder.setCustomFields("{\"tags\":\"" + tag + "\"}");
        encoder.setTimeZone("UTC");

        appender.setEncoder(encoder);
        addThresholdFilter(appender, threshold);
        encoder.start();
        appender.start();

        return wrapAsync(appender);
    }

}