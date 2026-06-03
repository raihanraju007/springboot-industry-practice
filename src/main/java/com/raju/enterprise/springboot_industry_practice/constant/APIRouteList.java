package com.raju.enterprise.springboot_industry_practice.constant;

public final class APIRouteList {

    private APIRouteList() {
    }

    public static final String PRODUCT_LIST = "/list";
    public static final String PRODUCT_SAVE = "/save";
    public static final String PRODUCT_UPDATE = "/update/{id}";
    public static final String PRODUCT_DELETE = "/delete/{id}";
    public static final String PRODUCT_BY_ID = "/{id}";
}