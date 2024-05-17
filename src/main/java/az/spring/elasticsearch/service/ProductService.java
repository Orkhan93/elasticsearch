package az.spring.elasticsearch.service;

import az.spring.elasticsearch.dto.request.ProductRequest;
import az.spring.elasticsearch.dto.response.ProductResponse;
import az.spring.elasticsearch.dto.response.ProductResponseList;
import az.spring.elasticsearch.mapper.ProductMapper;
import az.spring.elasticsearch.model.Product;
import az.spring.elasticsearch.repository.ProductRepository;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ElasticsearchClient elasticSearchClient;
    private final ProductMapper productMapper;

    public ProductResponse save(ProductRequest request) {
        return productMapper.entityToResponse(productRepository.save(productMapper.requestToEntity(request)));
    }

    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found..."));
        return productMapper.entityToResponse(product);
    }

    public ProductResponseList findAll() {
        List<Product> responseList = (List<Product>) productRepository.findAll();
        List<ProductResponse> responses = productMapper.listEntityToListResponse(responseList);
        return ProductResponseList.builder()
                .responseList(responses)
                .build();
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public ProductResponseList searchHits(String name) throws IOException {
        SearchResponse<Product> searchResponse = elasticSearchClient.search(search -> search.index("products")
                .query(query -> query.wildcard(type -> type.field("name").value(".*" + name + ".*"))), Product.class);
        List<Hit<Product>> hits = searchResponse.hits().hits();
        List<Product> products = new ArrayList<>();
        for (Hit<Product> hit : hits) {
            products.add(hit.source());
        }
        List<ProductResponse> responseList = productMapper.listEntityToListResponse(products);
        return ProductResponseList.builder()
                .responseList(responseList)
                .build();
    }

}