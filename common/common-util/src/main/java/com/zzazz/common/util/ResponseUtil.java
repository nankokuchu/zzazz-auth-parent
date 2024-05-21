package com.zzazz.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzazz.common.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: ResponseUtil
 * Package: com.zzazz.common.util
 *
 * @Author: zzazz
 * @Create: 2024/5/21 - 10:03
 * @Description: ResponseUtil
 * @Version: v1.0
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
