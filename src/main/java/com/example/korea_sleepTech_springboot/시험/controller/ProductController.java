//package com.example.korea_sleepTech_springboot.시험.controller;
//
//import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
//import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
//import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
//import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
//import com.example.korea_sleepTech_springboot.시험.service.ProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping
//@RequiredArgsConstructor
//public class ProductController {
//
//    private final ProductService productService;
//
//    // 1. 기본 CRUD
//    // 1) CREATE - 제품 생성
//    @PostMapping
//    public ResponseEntity<ResponseDto<Object>> createProduct(@RequestBody ProductCreateRequestDto dto) {
//        try {
//            ResponseDto<ProductResponseDto> product = productService.createProduct(dto);
//            return ResponseEntity.status(HttpStatus.CREATED).body(product);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ResponseDto.setFailed("제품 등록 중 오류 발생: " + e.getMessage()));
//        }
//    }
//
//    // 2) READ - 전체 제품 조회
//    @GetMapping
//    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
//        try {
//            List<ProductResponseDto> products = productService.getAllProducts();
//            return ResponseEntity.status(HttpStatus.OK).body(products);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 좀 더 명확한 실패 처리
//        }
//    }
//
//    // 3) READ - 제품 단건 조회 (특정 ID)
//    @GetMapping("/{id}")
//    public ResponseEntity<com.example.korea_sleepTech_springboot.dto.response.ResponseDto<Object>> getProductById(@PathVariable Long id) {
//        try {
//            ProductResponseDto product = productService.getProductById(id);
//            if (product == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(ResponseDto.setFailed("해당 ID의 제품을 찾을 수 없습니다."));
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.setSuccess("제품 조회 성공", product));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ResponseDto.setFailed("제품 조회 중 오류 발생: " + e.getMessage()));
//        }
//    }
//
//    // 4) UPDATE - 제품 수정 (특정 ID)
//    @PutMapping("/{id}")
//    public ResponseEntity<com.example.korea_sleepTech_springboot.dto.response.ResponseDto<Object>> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequestDto dto) {
//        try {
//            ProductResponseDto product = productService.updateProduct(id, dto);
//            if (product == null) {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(ResponseDto.setFailed("해당 ID의 제품을 찾을 수 없습니다."));
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.setSuccess("제품 수정 성공", product));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ResponseDto.setFailed("제품 수정 중 오류 발생: " + e.getMessage()));
//        }
//    }
//
//    // 5) DELETE - 제품 삭제 (특정 ID)
//    @DeleteMapping("/{id}")
//    public ResponseEntity<com.example.korea_sleepTech_springboot.dto.response.ResponseDto<Object>> deleteProduct(@PathVariable Long id) {
//        try {
//            productService.deleteProduct(id);
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 삭제 성공 시 No Content 응답
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(ResponseDto.setFailed("제품 삭제 중 오류 발생: " + e.getMessage()));
//        }
//    }
//}
