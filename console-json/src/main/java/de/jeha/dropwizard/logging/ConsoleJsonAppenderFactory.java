package de.jeha.dropwizard.logging;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.json.JsonLayoutBase;
import ch.qos.logback.contrib.json.classic.JsonLayout;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.encoder.LayoutWrappingEncoder;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.AbstractAppenderFactory;

import javax.validation.constraints.NotNull;
import java.util.TimeZone;

/**
 * Log Json to the console.
 *
 * @author jenshadlich@googlemail.com
 */
@JsonTypeName("console-json")
public class ConsoleJsonAppenderFactory extends AbstractAppenderFactory {

    @SuppressWarnings("UnusedDeclaration")
    public enum ConsoleStream {
        STDOUT("System.out"),
        STDERR("System.err");

        private final String value;

        ConsoleStream(String value) {
            this.value = value;
        }

        public String get() {
            return value;
        }
    }

    @NotNull
    private TimeZone timeZone = TimeZone.getTimeZone("UTC");

    @NotNull
    private ConsoleStream target = ConsoleStream.STDOUT;

    @NotNull
    private Boolean appendLineSeparator = Boolean.TRUE;

    @JsonProperty
    public TimeZone getTimeZone() {
        return timeZone;
    }

    @JsonProperty
    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @JsonProperty
    public ConsoleStream getTarget() {
        return target;
    }

    @JsonProperty
    public void setTarget(ConsoleStream target) {
        this.target = target;
    }

    @JsonProperty
    public boolean isAppendLineSeparator() {
        return appendLineSeparator;
    }

    @JsonProperty
    public void setAppendLineSeparator(boolean appendLineSeparator) {
        this.appendLineSeparator = appendLineSeparator;
    }

    @Override
    public Appender<ILoggingEvent> build(LoggerContext context, String applicationName, Layout<ILoggingEvent> layout) {
        final ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<>();

        appender.setName("console-json-appender");
        appender.setContext(context);
        appender.setTarget(target.get());

        LayoutWrappingEncoder<ILoggingEvent> layoutEncoder = new LayoutWrappingEncoder<>();

        JsonLayoutBase<ILoggingEvent> jsonLayout = new JsonLayout();
        jsonLayout.setAppendLineSeparator(appendLineSeparator);

        layoutEncoder.setLayout(jsonLayout);
        appender.setEncoder(layoutEncoder);

        addThresholdFilter(appender, threshold);
        appender.start();

        return wrapAsync(appender);
    }

}
