package amida.cristhian.coopsapi;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Cristhian
 *
 */
@Configuration
@ConfigurationProperties(prefix="url")
public class ConfigurationsBase {
	
	private String baseUrl;

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

}
