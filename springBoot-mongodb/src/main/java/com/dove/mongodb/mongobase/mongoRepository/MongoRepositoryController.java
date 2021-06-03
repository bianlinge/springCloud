package com.dove.mongodb.mongobase.mongoRepository;

import com.alibaba.fastjson.JSONObject;
import com.dove.mongodb.mongobase.common.BaseController;
import com.dove.mongodb.mongobase.common.Person;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by E1041 on 2020/3/9.
 */
@RestController
@Api(tags = "MongoDb")
@RequestMapping("/repository")
public class MongoRepositoryController extends BaseController {
	@Autowired
	private PersonRepository personRepository;

	@ApiOperation("save")
	@PostMapping("/save")
	public JSONObject save(@RequestBody Person person) {
		try {
			Person save = personRepository.save(person);
			return isSuccess(save);
		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}
	}

	@ApiOperation("delete")
	@PostMapping("/delete")
	public JSONObject delete(@RequestBody @ApiParam("和根据 ID删除一样,person需要提供id") Person person) {
		try {
			personRepository.delete(person);
			return isSuccess();
		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}
	}
	@ApiOperation("update")
	@PostMapping("/update")
	public JSONObject update(@RequestBody Person person) {
		person.setName("AAAAA");
		person.setPhone("1111111111111");
		Person save = personRepository.save(person);
		return isSuccess(save);
	}
	@ApiOperation("findAll")
	@GetMapping("/findAll")
	public JSONObject search() {
		try {
			List<Person> list = personRepository.findAll();
			return isSuccess(list);
		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}
	}

	@ApiOperation("findByName")
	@GetMapping("/findByName")
	public JSONObject findByName(@RequestParam(value = "name", required = true) String name) {
		try {
			List<Person> byName = personRepository.findByName(name);
			return isSuccess(byName);
		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}
	}

	@ApiOperation("findById")
	@GetMapping("/findById")
	public JSONObject findById(@RequestParam(value = "id", required = true) String id) {
		try {
			Optional<Person> byId = personRepository.findById(id);
			if (byId.isPresent()) {
				Person person = byId.get();
				return isSuccess(person);
			}
			return isError("查询为空");

		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}

	}

	@ApiOperation("findPersonByNameAndSex")
	@GetMapping("/findPersonByNameAndSex")
	public JSONObject findPersonByNameAndSex(@RequestParam(value = "name", required = true) String name,
											 @RequestParam(value = "sex",required = true) String sex) {
		try {
			Optional<Person> personByNameAndSex = personRepository.findPersonByNameAndSex(name, sex);
			if (personByNameAndSex.isPresent()) {
				Person person = personByNameAndSex.get();
				return isSuccess(person);
			}
			return isError("查询为空");

		} catch (Exception e) {
			e.printStackTrace();
			return isError();
		}

	}
}