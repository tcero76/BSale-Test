package com.bsale.test.leonardo.search;

public interface Specification<T> {
    Boolean isSatisfied(T item);
}
