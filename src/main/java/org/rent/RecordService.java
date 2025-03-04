package org.rent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class RecordService {

    private static final Logger logger = LoggerFactory.getLogger(RecordService.class);
    private final RecordLogicService recordLogicService;

    RecordService(RecordLogicService recordLogicService){
        this.recordLogicService = recordLogicService;
    }

    Record get(Long id) {
        if(id == null){
            logger.error(" >>> Произошла ошибка!", new RuntimeException("Отсутсвует id"));
        }

        return recordLogicService.get(id);
    }

    Record save(HashMap<String, Double> params){
        if(params.get("t1") == null || params.get("t2") == null || params.get("t3") == null ||
            params.get("hot") == null || params.get("cold") == null
        ){
            logger.error(" >>> Произошла ошибка!", new RuntimeException("Отсутствует значение показателя"));
        }

        return recordLogicService.save(params);
    }

    Record update(){
        // не подразумевается изменение записей
        return null;
    }

    Boolean delete(Long id){
        return recordLogicService.delete(id);
    }
}
