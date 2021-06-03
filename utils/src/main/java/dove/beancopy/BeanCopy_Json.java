package dove.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BeanCopy_Json {

	private static final Logger logger = LoggerFactory.getLogger(BeanCopy_Json.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		mapper.registerModule(new JavaTimeModule());
	}

	public static String toString(Object object) {
		if (Objects.isNull(object)) {
			return null;
		}
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("json序列化出错:{}", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T json2Object(String json, Class<T> clazz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("json反序列化出错:{}", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <T> List<T> jsonConverList(String json, Class<T> clz) {
		if (StringUtils.isBlank(json)) {
			return new ArrayList<>();
		}
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, clz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("json反序列化出错:{}", e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static <T> Set<T> jsonConverSet(String json, Class<T> clz) {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		JavaType javaType = mapper.getTypeFactory().constructParametricType(Set.class, clz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("json反序列化出错:{}", e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public static <K, V> Map<K, V> jsonConverMap(String json, Class<K> kClass, Class<V> vClass) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructMapType(Map.class, kClass, vClass));
		} catch (IOException e) {
			logger.error("json反序列化出错:{}", e.getMessage());
			return null;
		}
	}

	public static JsonNode readTree(String json) {
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
