package com.mini_project.service.interfaces;

import com.mini_project.model.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    /**
     * Retrieve all products with pagination.
     *
     * @param pageable Pagination information (page number, size, sorting).
     * @return A page of products.
     */
    Page<ProductDTO> getAllProducts(Pageable pageable);

    /**
     * Retrieve products by category with pagination.
     *
     * @param category The category of products to retrieve.
     * @param pageable Pagination information (page number, size, sorting).
     * @return A page of products in the specified category.
     */
    Page<ProductDTO> getProductsByCategory(String category, Pageable pageable);

    /**
     * Retrieve a product by its unique product ID.
     *
     * @param productId The unique ID of the product.
     * @return The product with the specified ID.
     */
    ProductDTO getProductById(Long productId);

    /**
     * Retrieve products by supplier ID with pagination.
     *
     * @param supplierId The supplier ID to filter products by.
     * @param pageable Pagination information (page number, size, sorting).
     * @return A page of products from the specified supplier.
     */
    Page<ProductDTO> getProductsBySupplierId(String supplierId, Pageable pageable);

    /**
     * Search for products by category name with pagination.
     *
     * @param categoryName The category name or partial name to search for.
     * @param pageable Pagination information (page number, size, sorting).
     * @return A page of products that match the category name.
     */
    Page<ProductDTO> searchProductsByCategoryName(String categoryName, Pageable pageable);

    /**
     * Search for products by product name with pagination.
     *
     * @param productName The product name or partial name to search for.
     * @param pageable Pagination information (page number, size, sorting).
     * @return A page of products that match the product name.
     */
    Page<ProductDTO> searchProductsByProductName(String productName, Pageable pageable);
}
