package de.jeha.dropwizard.logging.logstash.encoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.fieldnames.LogstashFieldNames;

/**
 * @author jenshadlich@googlemail.com
 */
public class EventLogLogstashEncoder extends LogstashEncoder {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public EventLogLogstashEncoder(String applicationName, String applicationVersion) {
        try {
            CustomFields customFields = new CustomFields(applicationName, applicationVersion, "event_log");
            setCustomFields(OBJECT_MAPPER.writeValueAsString(customFields));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Unable to set custom fields", e);
        }
        setTimeZone("UTC");
        setFieldNames(new LogstashFieldNames());
    }

}
