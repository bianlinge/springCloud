package com.dove.feign.eureka;

import com.dove.config.FeignInterceptor;
import com.dove.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @FeignClient用于通知Feign组件对该接口进行代理(不需要编写接口实现)， 使用者可直接通过@Autowired注入; 该接口通过value定义了需要调用的App服务（通过服务中心自动发现机制会定位具体URL）;
 * @RequestMapping定义了Feign需要访问的App服务的URL（本例中为根“/hello”）
 */
@FeignClient(value = "app-service-web", fallback = WebServiceFailure.class, configuration = FeignInterceptor.class)
public interface WebService {
    @RequestMapping(value = "/upload", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public String upload(@RequestParam(value = "file", required = true) MultipartFile file) throws Exception;

    @RequestMapping(value = "/uploadbytes", method = RequestMethod.POST)
    public String uploadFileByBytes(@RequestBody byte[] bytes) throws Exception;

    @RequestMapping(value = "/getBooks", method = RequestMethod.POST)
    public List<Book> getBooks(@RequestHeader(value = "token") String token,
                               @RequestBody Book book);

    @RequestMapping(value = "/getBooksForText", method = RequestMethod.POST)
    public List<Book> getBooksForText(@RequestHeader(value = "token") String token,
                                      @RequestBody Book book);
}
