package com.mini_project.productservice.productservice.service;

import com.mini_project.productservice.productservice.model.dto.ProductDTO;
import com.mini_project.productservice.productservice.model.Product;
import com.mini_project.productservice.productservice.repository.ProductRepository;
import com.mini_project.productservice.productservice.service.interfaces.ProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private ModelMapper modelMapper;

    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {

        Page<Product> productPage = productRepository.findAll(pageable);

        List<ProductDTO> productDTOList = productPage.getContent().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
    }

    @Override
    public Page<ProductDTO> getProductsByCategory(String category, Pageable pageable) {
        try {
            Page<Product> productPage = productRepository.findByCategory_CategoryName(category, pageable);
            List<ProductDTO> productDTOList = productPage.getContent().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving products by category: " + e.getMessage(), e);
        }
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        try {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isPresent()) {
                return convertToDTO(productOptional.get());
            } else {
                throw new RuntimeException("Product not found with ID: " + productId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving product by ID: " + e.getMessage(), e);
        }
    }


    @Override
    public Page<ProductDTO> getProductsBySupplierId(String supplierId, Pageable pageable) {
        try {
            Page<Product> productPage = productRepository.findBySupplierId(supplierId, pageable);
            List<ProductDTO> productDTOList = productPage.getContent().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving products by supplier ID: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductDTO> searchProductsByCategoryName(String categoryName, Pageable pageable) {
        try {
            Page<Product> productPage = productRepository.searchByCategoryName(categoryName, pageable);
            List<ProductDTO> productDTOList = productPage.getContent().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException("Error searching products by category name: " + e.getMessage(), e);
        }
    }

    @Override
    public Page<ProductDTO> searchProductsByProductName(String productName, Pageable pageable) {
        try {
            Page<Product> productPage = productRepository.searchByPartialName(productName, pageable);
            List<ProductDTO> productDTOList = productPage.getContent().stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return new PageImpl<>(productDTOList, pageable, productPage.getTotalElements());
        } catch (Exception e) {
            throw new RuntimeException("Error searching products by product name: " + e.getMessage(), e);
        }
    }

    public ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        if (product.getCategory() != null) {
            productDTO.setCategoryName(product.getCategory().getCategoryName());
        }

        return productDTO;
    }
}