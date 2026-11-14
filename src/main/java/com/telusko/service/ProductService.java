package com.telusko.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@Override
	public void fetchProductByPagination(int pgSize) {

		long count = 6l;
		long pagecount = count/pgSize;

		pagecount = count % pgSize == 0 ? pagecount : ++pagecount;

		for(int i=0; i < pagecount; i++){

			PageRequest pageable = PageRequest.of(i,pgSize);
			Page<Product> page = repo.findAll(pageable);

			page.getContent().forEach(p-> System.out.println(p));

			System.out.println("******************");
		}



	}


}
