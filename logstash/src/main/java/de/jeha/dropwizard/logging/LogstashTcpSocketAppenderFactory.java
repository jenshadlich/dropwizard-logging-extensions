package de.jeha.dropwizard.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Layout;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.AbstractAppenderFactory;

import javax.validation.constraints.NotNull;

@JsonTypeName("logstash-tcp")
class LogstashTcpSocketAppenderFactory extends AbstractAppenderFactory {

    @NotNull
    private String destination;

    @JsonProperty
    public String getDestination() {
        return destination;
    }

    @JsonProperty
    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public Appender<ILoggingEvent> build(LoggerContext context, String applicationName, Layout<ILoggingEvent> layout) {
        // TODO: implement me
        return null;
    }

}