package de.jeha.dropwizard.logging.logstash;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author jenshadlich@googlemail.com
 */
class CustomFields {

    @JsonProperty("application_name")
    private final String applicationName;

    @JsonProperty("application_version")
    private final String applicationVersion;

    @JsonProperty("tags")
    private final String tags;

    @JsonCreator
    public CustomFields(@JsonProperty("application_name") String applicationName,
                        @JsonProperty("application_version") String applicationVersion,
                        @JsonProperty("tags") String tags) {
        this.applicationName = applicationName;
        this.applicationVersion = applicationVersion;
        this.tags = tags;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getTags() {
        return tags;
    }

}
