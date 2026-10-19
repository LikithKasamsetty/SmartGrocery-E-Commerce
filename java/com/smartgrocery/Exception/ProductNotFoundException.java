package com.smartgrocery.Exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id){
        super("Product was not Found with this "+id+" id !!!!");
    }
} //It extends RuntimeException (unchecked), so @Transactional rolls back on it and you don't need throws everywhere.

