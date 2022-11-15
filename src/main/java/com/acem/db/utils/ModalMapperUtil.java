package com.acem.db.utils;

import org.modelmapper.ModelMapper;

public class ModalMapperUtil {

    private static ModelMapper modelMapper = null;


    static {
        modelMapper = new ModelMapper();
    }

    public static <T> T map(Object object, Class<T> clazz) {
        return modelMapper.map(object, clazz);
    }

}
