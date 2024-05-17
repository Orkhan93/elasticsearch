package az.spring.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@Setter
@Document(indexName = "products")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(type = FieldType.Long)
    @Column(name = "id")
    private Long id;

    @Field(type = FieldType.Text)
    @Column(name = "name")
    private String name;

    @Field(type = FieldType.Double)
    @Column(name = "price")
    private Double price;

    @Field(type = FieldType.Text)
    @Column(name = "description")
    private String description;

    @Field(type = FieldType.Text)
    @Column(name = "category_name")
    private String categoryName;

}