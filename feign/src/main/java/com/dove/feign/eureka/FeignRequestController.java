package com.dove.feign.eureka;

import com.alibaba.fastjson.JSONObject;

import com.dove.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FeignRequestController {
    @Autowired
    private WebService webService;

    @RequestMapping(value = "/getBooks", method = RequestMethod.POST)
    public JSONObject getBooks(@RequestParam(value = "token") String token,
                               @RequestBody Book book) {
        System.out.println("++++++++++token++++++++++" + token);
        JSONObject jsonObject = new JSONObject();
        List<Book> books = webService.getBooks(token, book);
        jsonObject.put("result", books);
        return jsonObject;
    }

    @RequestMapping(value = "/getBooksForText", method = RequestMethod.POST)
    public JSONObject getBooksForText(@RequestParam(value = "token") String token,
                                      @RequestBody String book) {
        System.out.println("RequestBody" + book);
        Book book1 = JSONObject.parseObject(book, Book.class);
        List<Book> books = webService.getBooksForText(token, book1);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", books);
        return jsonObject;
    }
}
