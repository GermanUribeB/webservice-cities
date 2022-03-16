package com.epam.travelers.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.travelers.services.CityService;

import io.spring.guides.gs_producing_web_service.GetCitiesRequest;
import io.spring.guides.gs_producing_web_service.GetCitiesResponse;

@Endpoint
public class CityEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private CityService cityService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCitiesRequest")
	@ResponsePayload
	public GetCitiesResponse getCities(@RequestPayload GetCitiesRequest request) {
		GetCitiesResponse response = new GetCitiesResponse();
		response.getCities().addAll(cityService.findCity(request.getCode()));

		return response;
	}
}
