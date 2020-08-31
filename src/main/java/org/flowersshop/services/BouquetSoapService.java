package org.flowersshop.services;

import org.flowersshop.bindings.*;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Service
public class BouquetSoapService {
    private static final String NAMESPACE_URI = "http://flowersshop.org/bindings";
    private static final String SUCCESS_STATUS = "successful";

    private BouquetSoapEntityAdapterService bouquetSoapEntityAdapterService;

    @Autowired
    public BouquetSoapService(BouquetSoapEntityAdapterService bouquetSoapEntityAdapterService) {
        this.bouquetSoapEntityAdapterService = bouquetSoapEntityAdapterService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByNameRequest")
    @ResponsePayload
    public GetBouquetByNameResponse getBouquetByName(@RequestPayload  GetBouquetByNameRequest request) throws EmptyResultSetException {
        GetBouquetByNameResponse response = new GetBouquetByNameResponse();
        response.getBouquet().addAll(bouquetSoapEntityAdapterService.findByName(request.getName()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByPriceRequest")
    @ResponsePayload
    public GetBouquetByPriceResponse getBouquetByPrice(@RequestPayload  GetBouquetByPriceRequest request) throws EmptyResultSetException {
        GetBouquetByPriceResponse response = new GetBouquetByPriceResponse();
        response.getBouquet().addAll(bouquetSoapEntityAdapterService.findByPrice(request.getPrice()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByNameAndPriceRequest")
    @ResponsePayload
    public GetBouquetByNameAndPriceResponse getBouquetByNameAndPrice(@RequestPayload  GetBouquetByNameAndPriceRequest request) throws EmptyResultSetException {
        GetBouquetByNameAndPriceResponse response = new GetBouquetByNameAndPriceResponse();
        response.getBouquet().addAll(bouquetSoapEntityAdapterService.
                findByNameAndPrice(request.getName(),request.getPrice()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBouquetsRequest")
    @ResponsePayload
    public GetAllBouquetsResponse getAllBouquets() throws EmptyResultSetException {
            GetAllBouquetsResponse response = new GetAllBouquetsResponse();
            response.getBouquet().addAll(bouquetSoapEntityAdapterService.findAll());
            return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBouquetRequest")
    @ResponsePayload
    public AddBouquetResponse addBouquet(@RequestPayload AddBouquetRequest request) {
        AddBouquetResponse response = new AddBouquetResponse();
        response.setId(bouquetSoapEntityAdapterService.createBouquet(request.getName(), request.getPrice()).getId());
        if(response.getId() != 0){
            response.setMessage(SUCCESS_STATUS);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBouquetRequest")
    @ResponsePayload
    public void deleteBouquet(@RequestPayload DeleteBouquetRequest request) {
        bouquetSoapEntityAdapterService.deleteBouquet(request.getBouquetId());
    }
}