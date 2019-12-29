package com.imodule.seriviceImpl;
import com.imodule.dao.mapper.ContactsMapper;
import com.imodule.service.ConcatsLableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConcatsLableImpl implements ConcatsLableService {
    @Autowired
    ContactsMapper contactsMapper;
    @Override
    public int addPriInfoLabel(List<Map<String, Object>> lables) {
        int i = contactsMapper.addPriInfoLabel(lables);
        return i;
    }
}
