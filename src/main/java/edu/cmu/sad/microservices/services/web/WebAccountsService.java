package edu.cmu.sad.microservices.services.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Hide the access to the microservice inside this local service.
 */
@Service
public class WebAccountsService {

	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(WebAccountsService.class
			.getName());

	public WebAccountsService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory());
	}

	public edu.cmu.sad.microservices.accounts.Account findByNumber(String accountNumber) {

		logger.info("findByNumber() invoked: for " + accountNumber);
		return restTemplate.getForObject(serviceUrl + "/accounts/{number}",
				edu.cmu.sad.microservices.accounts.Account.class, accountNumber);
	}

	public List<edu.cmu.sad.microservices.accounts.Account> byOwnerContains(String name) {
		logger.info("byOwnerContains() invoked:  for " + name);
		edu.cmu.sad.microservices.accounts.Account[] accounts = null;

		try {
			accounts = restTemplate.getForObject(serviceUrl
					+ "/accounts/owner/{name}", edu.cmu.sad.microservices.accounts.Account[].class, name);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (accounts == null || accounts.length == 0)
			return null;
		else
			return Arrays.asList(accounts);
	}
}
