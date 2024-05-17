package az.spring.elasticsearch.mapper;

import az.spring.elasticsearch.dto.request.ProductRequest;
import az.spring.elasticsearch.dto.response.ProductResponse;
import az.spring.elasticsearch.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product requestToEntity(ProductRequest request);

    ProductResponse entityToResponse(Product product);

    List<ProductResponse> listEntityToListResponse(List<Product> products);

}