package az.spring.elasticsearch.repository;

import az.spring.elasticsearch.model.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, Long> {


}