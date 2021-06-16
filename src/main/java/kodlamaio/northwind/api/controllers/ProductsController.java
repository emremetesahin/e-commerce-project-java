package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@CrossOrigin
@RequestMapping("/api/products/")
public class ProductsController {

	@Autowired
	private ProductService productservice;

	public ProductsController(ProductService productservice) {
		super();
		this.productservice = productservice;
	}

	@GetMapping("getall")
	public DataResult<List<Product>> getAll() {
		return this.productservice.getAll();
	}

	@PostMapping("add")
	public Result add(@RequestBody Product product) {
		return this.productservice.add(product);
	}

	@GetMapping("getByProductName")
	public DataResult<Product> getByProductName(@RequestParam("productName") String productName) {
		return this.productservice.getByProductName(productName);
	}

	@GetMapping("getByProductNameOrCategoryId")
	public DataResult<List<Product>> getByProductNameOrCategory_CategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId) {
		
		return this.productservice.getByProductNameOrCategory_CategoryId(productName, categoryId);
	}

	@GetMapping("getByCategoryIn")

	public DataResult<List<Product>> getByCategoryIn(@RequestParam List<Integer> categories) {
		return this.productservice.getByCategoryIdIn(categories);
	}
	@GetMapping("getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName) {
		return this.productservice.getByProductNameContains(productName);
	}
	
	@GetMapping("getByProductNameStartsWith")
	public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName) {
		return this.productservice.getByProductNameStartsWith(productName);
	}
	@GetMapping("getAllByPage")
	public DataResult<List<Product>> getAll(@RequestParam("pageNo") int pageNo, int pageSize) {
		return this.productservice.getAll(pageNo, pageSize);
	}
	@GetMapping("getAllSorted")
	public DataResult<List<Product>> getAllSorted() {
		return this.productservice.getAllSorted();
	}
	@GetMapping("getByNameAndCategory")
	public DataResult<List<Product>> getByNameAndCategory(@RequestParam("productName") String productName,@RequestParam("categoryId") int categoryId) {
		return this.productservice.getByNameAndCategory(productName, categoryId);	}
	 
	@GetMapping("getProductWithCategoryDetails")
	public 	DataResult<List<ProductWithCategoryDto>>getProductWithCategoryDetails()
	{
		return this.productservice.getProductWithCategoryDetails();
	}

}
