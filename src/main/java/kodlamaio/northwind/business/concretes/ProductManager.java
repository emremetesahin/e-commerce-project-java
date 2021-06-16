package kodlamaio.northwind.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;
import kodlamaio.northwind.dataAccess.abstracts.ProductDao;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@Service
public class ProductManager implements ProductService {
	private ProductDao productDao;

	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll(), "Data Listelendi");
	}

	@Override
	public Result add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Ürün eklendi");
	}
	@Override
	public DataResult<Product> getByProductName(String productName)
	{
		return new SuccessDataResult<Product>(productDao.getByProductName(productName),"Veri listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameOrCategory_CategoryId(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>
		(productDao.getByProductNameOrCategory_CategoryId(productName, categoryId),"Data Listlendi");
	}

	@Override
	public DataResult<List<Product>> getByCategoryIdIn(List<Integer> categories) {
		
		return new SuccessDataResult<List<Product>>
		(productDao.getByCategory_CategoryIdIn(categories),"Veri Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameContains(String productName) {
		return new SuccessDataResult<List<Product>>(productDao.getByProductNameContains(productName),"Veri Listelendi");
	}

	@Override
	public DataResult<List<Product>> getByProductNameStartsWith(String productName) {
		return new SuccessDataResult<List<Product>>(productDao.getByProductNameStartsWith(productName),"Veri listelendi");
	}

	@Override
	public DataResult<List<Product>> getAll(int pageNo, int pageSize) {
		Pageable pageable=PageRequest.of(pageNo-1, pageSize);
		return new SuccessDataResult<List<Product>>(productDao.findAll(pageable).getContent(),"Sayfa Listelendi");
	}

	@Override
	public DataResult<List<Product>> getAllSorted() {
		Sort sort=Sort.by(Sort.Direction.ASC,"productName");
		return new SuccessDataResult<List<Product>>(productDao.findAll(sort),"Başarılı");
	}

	@Override
	public DataResult<List<Product>> getByNameAndCategory(String productName, int categoryId) {
		return new SuccessDataResult<List<Product>>(productDao.getByNameAndCategory(productName, categoryId),"Data Listelendi");
	}

	@Override
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
		return new SuccessDataResult<List<ProductWithCategoryDto>>(productDao.getProductWithCategoryDetails(),"Data listelendi");
	}
}