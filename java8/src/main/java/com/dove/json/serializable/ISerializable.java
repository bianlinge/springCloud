package com.dove.json.serializable;

import java.io.IOException;

public interface ISerializable {

    //序列化
    byte[] enSerializable(Object object) throws IOException;

    //反序列化
    <T> Object deSerializable(byte[] bytes, Class<T> tClass);


}
