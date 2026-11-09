package com.telusko.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.telusko.entity.Product;
import com.telusko.repo.IProductRepo;

@Service
public class ProductService implements IProductService {
	
	@Autowired
	private IProductRepo repo;

	@Override
	public Iterable<Product> fetchProductInfoSorting(boolean status, String... properties) {
		Sort sort = Sort.by(status? Sort.Direction.ASC: Sort.Direction.DESC,properties);
		return repo.findAll(sort);
	}
}
