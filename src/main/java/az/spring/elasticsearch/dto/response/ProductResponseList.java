package az.spring.elasticsearch.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseList {

    List<ProductResponse> responseList;

}