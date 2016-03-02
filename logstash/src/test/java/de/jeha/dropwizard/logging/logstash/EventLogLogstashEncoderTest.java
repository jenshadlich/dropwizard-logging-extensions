package de.jeha.dropwizard.logging.logstash;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.encoder.LogstashEncoder;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author jenshadlich@googlemail.com
 */
public class EventLogLogstashEncoderTest {

    @Test
    public void test() throws IOException {
        final LogstashEncoder encoder = new EventLogLogstashEncoder("appName", "appVersion");

        // need to start the encoder to populate the custom fields
        encoder.start();

        assertEquals("UTC", encoder.getTimeZone());

        final CustomFields customFields = new ObjectMapper().readValue(encoder.getCustomFields(), CustomFields.class);
        assertEquals("appName", customFields.getApplicationName());
        assertEquals("appVersion", customFields.getApplicationVersion());
        assertEquals("event_log", customFields.getTags());
    }
}
