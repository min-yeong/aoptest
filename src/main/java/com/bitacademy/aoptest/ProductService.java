package com.bitacademy.aoptest;

import org.springframework.stereotype.Service;

// Component Scan
@Service
public class ProductService {
	public ProductVo findProduct(String keyword) {
		System.out.println("상품 ["+keyword+"]를 찾고 있습니다.");
		// 로직진행 (Dao 등)
		
		// 에러발생 시뮬레이션
		if(1 < 2) {
			throw new RuntimeException("Exception 발생!");
		}
		return new ProductVo(keyword);
	}
}
