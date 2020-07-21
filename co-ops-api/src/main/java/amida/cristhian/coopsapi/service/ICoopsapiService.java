package amida.cristhian.coopsapi.service;

import amida.cristhian.coopsapi.model.request.CoopsapiScopeRequest;
import amida.cristhian.coopsapi.model.response.CoopsapiResponse;

public interface ICoopsapiService {
	public CoopsapiResponse findCoopsapiInformation(CoopsapiScopeRequest coopsapiRequest) throws Exception;
}
