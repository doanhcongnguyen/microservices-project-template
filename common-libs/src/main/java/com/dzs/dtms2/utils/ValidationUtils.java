package com.dzs.dtms2.utils;

import com.dzs.dtms2.exception.BadRequestException;

import java.util.Optional;

public class ValidationUtils {

    public static void isNull(Optional optional, String entity, String parameter, Object value) {
        if (!optional.isPresent()) {
            String msg = entity + "-" + parameter + " { " + value.toString() + " } does not exist";
            throw new BadRequestException(msg);
        }
    }

    public static <T> void isNull(T object, String entity, String parameter, Object value) {
        if (object == null) {
            String msg = entity + "-" + parameter + " { " + value.toString() + " } does not exist";
            throw new BadRequestException(msg);
        }
    }
}
