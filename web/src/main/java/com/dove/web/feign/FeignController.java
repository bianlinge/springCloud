package com.dove.web.feign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@RestController
@Api("requestHeader_test")
public class FeignController {
    @RequestMapping(value = "/getBooks", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Book> getBooks(HttpServletRequest request,
                               @RequestHeader(value = "token") String token,
                               @RequestBody Book book) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            String header = request.getHeader(s);
            System.out.println(s + ":" + header);
        }
        System.out.println("*********token************" + token);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }
    @ApiOperation(value = "getBooksForText")
    @RequestMapping(value = "/getBooksForText", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Book> getBooksForText(@RequestHeader(value = "token") String token,
                                      @RequestBody Book book) {
        System.out.println("*********token************" + token);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        return books;
    }
}
