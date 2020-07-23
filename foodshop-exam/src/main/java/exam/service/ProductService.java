package exam.service;

import exam.model.service.ProductServiceModel;
import exam.model.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllProducts();

    ProductViewModel findById(String id);

}
