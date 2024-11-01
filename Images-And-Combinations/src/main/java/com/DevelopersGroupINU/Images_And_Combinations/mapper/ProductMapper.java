package com.DevelopersGroupINU.Images_And_Combinations.mapper;

import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "productDetail.size", target = "size")
    @Mapping(source = "productDetail.material", target = "material")
    @Mapping(source = "productDetail.brand", target = "brand")
    @Mapping(source = "productDetail.price", target = "price")
    ProductViewDto entityToDto(Product product);

    List<ProductViewDto> entityListToDtoList(List<Product> products);
}
