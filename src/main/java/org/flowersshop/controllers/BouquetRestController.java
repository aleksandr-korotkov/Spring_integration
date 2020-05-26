package org.flowersshop.controllers;

import org.flowersshop.entities.Bouquet;
import org.flowersshop.exceptions.EmptyResultSetException;
import org.flowersshop.services.BouquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "api/bouquets")
public class BouquetRestController {

    private BouquetService bouquetService;

    @Autowired
    public void setBouquetService(BouquetService bouquetService) {
        this.bouquetService = bouquetService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Bouquet>> getResponse(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "price", required = false) BigDecimal price,
            @RequestParam(value = "min_price", required = false) BigDecimal minPrice,
            @RequestParam(value = "max_price", required = false) BigDecimal maxPrice
    ) throws EmptyResultSetException {

        if(name!=null && price == null){
            return ResponseEntity.ok().body(bouquetService .findByName(name));
        }
        else if(name==null && price != null){
            return ResponseEntity.ok().body(bouquetService .findByPrice(price));
        }
        else if(name!=null && price != null){
            return ResponseEntity.ok().body(bouquetService.findByNameAndPrice(name, price));
        }
        else {
            return ResponseEntity.ok().body(bouquetService .findAll());
        }
    }

    @PostMapping
    @ResponseBody
    public Long createBouquet(@RequestBody @Valid Bouquet bouquet) {
        return bouquetService.createBouquet(bouquet);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public boolean updateBouquet( @PathVariable("id") Long id, @RequestBody @Valid Bouquet bouquet){
        return bouquetService.updateBouquet(id, bouquet);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable("id") Long id) throws EmptyResultSetException {
        return bouquetService.deleteBouquet(id);
    }
}