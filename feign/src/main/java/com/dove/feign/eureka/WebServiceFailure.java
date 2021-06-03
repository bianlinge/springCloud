package com.dove.feign.eureka;

import com.dove.entity.Book;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebServiceFailure implements FallbackFactory<WebService> {

    @Override
    public WebService create(Throwable e) {
        return new WebService() {
            @Override
                public String upload(@RequestParam(value = "file", required = true) MultipartFile file) {
                return error("web/vailToken", e);
            }

            @Override
            public String uploadFileByBytes(byte[] bytes) throws Exception {
                return error("web/uploadFileByBytes", e);
            }

            @Override
            public List<Book> getBooks(String token, Book book) {
                return new ArrayList<>();
            }

            @Override
            public List<Book> getBooksForText(String token, Book book) {
                return new ArrayList<>();
            }
        };
    }

    /**
     * 错误提醒及日志打印
     *
     * @return
     */
    private String error(String path, Throwable e) {
        return e.getMessage();
    }
}