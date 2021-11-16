package com.bsale.test.leonardo.search;

import com.bsale.test.leonardo.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class FilterImpl implements Filter<Product> {

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
        return items.stream().filter(i -> spec.isSatisfied(i));
    }
}
