package com.codewithnolan.ecommerce.controllers;

import com.codewithnolan.ecommerce.dto.ShopDTO;
import com.codewithnolan.ecommerce.entities.Shop;
import com.codewithnolan.ecommerce.services.ShopService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/public/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public ResponseEntity<List<Shop>> getAll() {
        List<Shop> shops = shopService.getAll();
        return ResponseEntity.ok(shops);
    }

    @PostMapping
    public ResponseEntity<String> createShop(@Valid @RequestBody ShopDTO shopDto) {
        shopService.createShop(shopDto);
        return ResponseEntity.ok("Created");
    }
}
