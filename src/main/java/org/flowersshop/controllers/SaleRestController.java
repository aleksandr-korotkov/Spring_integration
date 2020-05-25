package org.flowersshop.controllers;

import org.flowersshop.entities.Sale;
import org.flowersshop.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sales")
public class SaleRestController {

    private SalesService salesService;

    @Autowired
    public void setSalesService(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Sale>> getAllEmployees(
            @RequestParam(name = "page", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "size", defaultValue = "2") Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy)
    {
        Iterable<Sale> list = salesService.findAll(PageRequest.of(pageNo,pageSize, Sort.by(sortBy)));
        return new ResponseEntity<Iterable<Sale>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}