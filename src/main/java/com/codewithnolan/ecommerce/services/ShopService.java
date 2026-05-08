package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.dto.ShopDTO;
import com.codewithnolan.ecommerce.entities.Shop;

import java.util.List;
import java.util.UUID;

public interface ShopService {
    public void createShop(ShopDTO shopDto);

    public List<Shop> getAll();

    public Shop findById(UUID id);
}
