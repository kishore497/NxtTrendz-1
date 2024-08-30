package com.example.nxttrendz1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.example.nxttrendz1.repository.ProductJpaRepository;
import com.example.nxttrendz1.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.nxttrendz1.repository.ReviewJpaRepository;
import com.example.nxttrendz1.model.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductJpaService implements ProductRepository {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private ReviewJpaRepository reviewJpaRepository;

    public ArrayList<Product> getProducts() {
        List<Product> list = productJpaRepository.findAll();
        ArrayList<Product> arr = new ArrayList<>(list);
        return arr;
    }

    public Product getProductById(int productId) {
        try {
            Product product = productJpaRepository.findById(productId).get();
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public Product addProduct(Product product) {
        try {
            productJpaRepository.save(product);
            return product;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public Product updateProduct(int productId, Product product) {
        try {
            Product p = productJpaRepository.findById(productId).get();
            if (p == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            if (product.getProductName() != null) {
                p.setProductName(product.getProductName());
            }
            if (product.getPrice() != 0) {
                p.setPrice(product.getPrice());
            }
            productJpaRepository.save(p);
            return p;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    public void deleteProduct(int productId) {
        try {
            productJpaRepository.deleteById(productId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT);
    }
}