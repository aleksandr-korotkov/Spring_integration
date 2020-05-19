package org.flowersshop.services;

import org.flowersshop.bindings.*;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.repositories.BouquetRepositoryImpl;
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

    private BouquetRepositoryImpl bouquetRepository;

    @Autowired
    public BouquetSoapService(BouquetRepositoryImpl bouquetRepository) {
        this.bouquetRepository = bouquetRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByNameRequest")
    @ResponsePayload
    public GetBouquetByNameResponse getBouquetByName(@RequestPayload  GetBouquetByNameRequest request) throws EmptyResultSetException {
        GetBouquetByNameResponse response = new GetBouquetByNameResponse();
        response.getBouquet().addAll(bouquetRepository.findByName(request.getName()).orElseThrow(EmptyResultSetException::new));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByPriceRequest")
    @ResponsePayload
    public GetBouquetByPriceResponse getBouquetByPrice(@RequestPayload  GetBouquetByPriceRequest request) throws EmptyResultSetException {
        GetBouquetByPriceResponse response = new GetBouquetByPriceResponse();
        response.getBouquet().addAll(bouquetRepository.findByPrice(request.getPrice()).orElseThrow(EmptyResultSetException::new));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBouquetByNameAndPriceRequest")
    @ResponsePayload
    public GetBouquetByNameAndPriceResponse getBouquetByNameAndPrice(@RequestPayload  GetBouquetByNameAndPriceRequest request) throws EmptyResultSetException {
        GetBouquetByNameAndPriceResponse response = new GetBouquetByNameAndPriceResponse();
        response.getBouquet().addAll(bouquetRepository.
                findByNameAndPrice(request.getName(),request.getPrice()).orElseThrow(EmptyResultSetException::new));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllBouquetsRequest")
    @ResponsePayload
    public GetAllBouquetsResponse getAllBouquets() throws EmptyResultSetException {
            GetAllBouquetsResponse response = new GetAllBouquetsResponse();
            response.getBouquet().addAll(bouquetRepository.findAll().orElseThrow(EmptyResultSetException::new));
            return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBouquetRequest")
    @ResponsePayload
    public AddBouquetResponse addBouquet(@RequestPayload AddBouquetRequest request) {
        AddBouquetResponse response = new AddBouquetResponse();
        response.setId(bouquetRepository.createBouquet(request.getName(), request.getPrice()));
        if(response.getId() != 0){
            response.setMessage(SUCCESS_STATUS);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateBouquetRequest")
    @ResponsePayload
    public UpdateBouquetResponse updateBouquet(@RequestPayload UpdateBouquetRequest request) {
        UpdateBouquetResponse response = new UpdateBouquetResponse();
        if(bouquetRepository.updateBouquet(request.getBouquet().getId(),
                request.getBouquet().getName(),request.getBouquet().getPrice())){
            response.setMessage(SUCCESS_STATUS);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteBouquetRequest")
    @ResponsePayload
    public DeleteBouquetResponse deleteBouquet(@RequestPayload DeleteBouquetRequest request) {
        DeleteBouquetResponse response = new DeleteBouquetResponse();
        if(bouquetRepository.deleteBouquet(request.getBouquetId())){
            response.setMessage(SUCCESS_STATUS);
        }
        return response;
    }
}