package de.jeha.dropwizard.logging.logstash.encoder;

import ch.qos.logback.access.spi.IAccessEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.logstash.logback.composite.AbstractFieldJsonProvider;
import net.logstash.logback.composite.JsonProviders;
import net.logstash.logback.composite.accessevent.AccessEventFormattedTimestampJsonProvider;
import net.logstash.logback.composite.accessevent.AccessEventJsonProviders;
import net.logstash.logback.encoder.AccessEventCompositeJsonEncoder;
import net.logstash.logback.encoder.LogstashEncoder;
import net.logstash.logback.fieldnames.LogstashFieldNames;

import java.io.IOException;

/**
 * @author jenshadlich@googlemail.com
 */
public class AccessLogLogstashEncoder extends AccessEventCompositeJsonEncoder {

    public AccessLogLogstashEncoder(String applicationName, String applicationVersion) {
        AccessEventJsonProviders providers = new AccessEventJsonProviders();
        {
            AbstractFieldJsonProvider provider = new AccessEventFormattedTimestampJsonProvider();
            provider.setFieldName("timestamp");
            providers.addProvider(provider);
        }
        setProviders(providers);
    }

    @Override
    public void doEncode(IAccessEvent iAccessEvent) throws IOException {
        System.out.println("doEncode");
        super.doEncode(iAccessEvent);
    }

    @Override
    public void start() {
        System.out.println("start");
        super.start();
    }

}
