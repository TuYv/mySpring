package com.max.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author max
 * @date 2021/9/15 13:43
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
