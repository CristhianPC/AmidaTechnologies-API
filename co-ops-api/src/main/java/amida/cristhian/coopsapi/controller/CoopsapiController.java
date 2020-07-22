package amida.cristhian.coopsapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import amida.cristhian.coopsapi.model.request.CoopsapiScopeRequest;
import amida.cristhian.coopsapi.model.response.CoopsapiResponse;
import amida.cristhian.coopsapi.service.ICoopsapiService;

/**
 * @author Cristhian
 *
 */

@RestController
@RequestMapping(value = "/amida")
public class CoopsapiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CoopsapiController.class);

	@Autowired
	ICoopsapiService coopsapiService;
		
	@RequestMapping(value = "/home")
	public String methodTest() {
		return "testDone";
	}
	
	@CrossOrigin(origins = "http://localhost:8083")
	@PostMapping(value = "/monthlyReport")
	public ResponseEntity<?> getResults(@RequestBody CoopsapiScopeRequest coopsapiRequest) throws Exception {
		CoopsapiResponse result = coopsapiService.findCoopsapiInformation(coopsapiRequest);
		if (result != null)
			LOGGER.info("Get Info sucess");
		else {
			LOGGER.info("Get Info is null, returning SERVICE_UNAVAILABLE");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
