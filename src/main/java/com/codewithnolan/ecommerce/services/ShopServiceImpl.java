package com.codewithnolan.ecommerce.services;

import com.codewithnolan.ecommerce.dto.ShopDTO;
import com.codewithnolan.ecommerce.entities.Shop;
import com.codewithnolan.ecommerce.exception.EntityNotFoundException;
import com.codewithnolan.ecommerce.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void createShop(ShopDTO shopDto) {
        Shop shop = new Shop();
        shop.setName(shopDto.name());
        shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop findById(UUID id) {
        return shopRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Shop Not Found with id: "+id));
    }
}
