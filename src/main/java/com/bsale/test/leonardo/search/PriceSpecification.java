package com.bsale.test.leonardo.search;

import com.bsale.test.leonardo.model.Product;
import com.bsale.test.leonardo.util.Precios;

import java.util.HashMap;
import java.util.Map;

public class PriceSpecification implements Specification<Product> {

    final static Map<Precios,float[]> limites = new HashMap<Precios,float[]>();

    static {
        limites.put(Precios.todos,new float[]{-1f,-1f});
        limites.put(Precios.bajo,new float[]{0f,5000f});
        limites.put(Precios.medio,new float[]{5000f,10000f});
        limites.put(Precios.alto,new float[]{10000f,-1f});
    }
    private float max;
    private float min;

    public PriceSpecification(Precios precios) {
        this.min = limites.get(precios)[0];
        this.max = limites.get(precios)[1];
    }

    @Override
    public Boolean isSatisfied(Product item) {
        if(max==-1f && min==-1f) {
            return true;
        }
        if(max==-1f) {
            return item.getPrice()>=min;
        }
        return item.getPrice()<max && item.getPrice()>=min;
    }
}
