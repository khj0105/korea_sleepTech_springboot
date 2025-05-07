package com.example.korea_sleepTech_springboot.시험.service;


import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 1. 제품 등록
    public ResponseDto<ProductResponseDto> createProduct(ProductCreateRequestDto requestDto) {
        try {
            Product newProduct = new Product(
                    null, requestDto.getName(), requestDto.getDescription(), requestDto.getPrice()
            );

            Product savedProduct = productRepository.save(newProduct);

            ProductResponseDto response = new ProductResponseDto(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getDescription(),
                    savedProduct.getPrice()
            );

            return ResponseDto.setSuccess("제품이 정상적으로 등록되었습니다.", response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("제품 등록 중 오류 발생: " + e.getMessage());
        }
    }

    // 2. 전체 제품 조회
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> responseDtos = null;
        try {
            List<Product> products = productRepository.findAll();

            responseDtos = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    ))
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDtos;
    }

    // 3. 제품 단건 조회
    public ProductResponseDto getProductById(Long id) {
        ProductResponseDto responseDto = null;

        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제품을 찾을 수 없습니다: " + id));

            responseDto = new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
    }

    // 4. 제품 수정
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto dto) {
        ProductResponseDto responseDto = null;
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제품이 없습니다: " + id));

            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            Product updatedProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseDto;
    }

    // 5. 제품 삭제
    public void deleteProduct(Long id) {
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 ID의 제품이 없습니다: " + id));

            productRepository.delete(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    }