package com.telusko.service;



import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Iterable<Product> fetchProductByPagination(int pgNo, int pgSize, boolean status, String ...properties) {
		Sort sort = Sort.by(status? Sort.Direction.ASC: Sort.Direction.DESC,properties);

		PageRequest pageable =  PageRequest.of(pgNo,pgSize,sort);
		Page<Product> page = repo.findAll(pageable);

		return page.getContent();
	}


}
