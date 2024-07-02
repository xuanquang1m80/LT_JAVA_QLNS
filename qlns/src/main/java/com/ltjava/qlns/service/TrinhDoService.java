package com.ltjava.qlns.service;

import com.ltjava.qlns.model.TrinhDo;
import com.ltjava.qlns.repository.TrinhDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrinhDoService {

    @Autowired
    private TrinhDoRepository trinhDoRepository;

    public List<TrinhDo> getAllTrinhDo() {
        return trinhDoRepository.findAll();
    }
}
