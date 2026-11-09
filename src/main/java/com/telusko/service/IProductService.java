package com.telusko.service;

import java.util.List;

import com.telusko.entity.Product;

public interface IProductService 
{
	Iterable<Product> fetchProductInfoSorting(boolean status, String ...properties);

	Iterable<Product> fetchProductByPagination();

}
