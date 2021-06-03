package com.dove.seriviceImpl;
import com.dove.dao.mapper.ContactsMapper;
import com.dove.service.ConcatsLableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ConcatsLableImpl implements ConcatsLableService {
    @Autowired
    ContactsMapper contactsMapper;
    @Override
    public int addPriInfoLabel(List<Map<String, Object>> lables) {
        int i = contactsMapper.addPriInfoLabel(lables);
        return i;
    }
}
