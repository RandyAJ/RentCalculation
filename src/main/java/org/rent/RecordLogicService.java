package org.rent;

import java.util.HashMap;
import java.time.LocalDate;

// Сервис для методов, содержащий обработку, завязанную на бизнес-логику
public class RecordLogicService {
    public static final Double t1coefficient = 8.94;
    public static final Double t2coefficient = 3.02;
    public static final Double t3coefficient = 6.15;
    public static final Double hotCoefficient = 272.14;
    public static final Double coldCoefficient = 59.8;
    public static final Double wdCoefficient = 45.91;

    private final RecordRepository recordRepository;

    public RecordLogicService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    Record get(Long id){
        return recordRepository.findById(id);
    }

    // создание новой записи в БД с вычислением значений
    Record save(HashMap<String, Double> params){
        Record record = new Record(
            params.get("t1"), params.get("t2"), params.get("t3"),
            params.get("hot"), params.get("cold")
        );

        record.prevRecordId = this.getLatestRecord().getId();
        record.wd = this.setWd(record);
        record.dateRecord = setDateRecord();
        record.total = setTotal(record);

        recordRepository.save(record);

        return record;
    }

    Boolean delete(Long id){
        return recordRepository.delete(id);
    }

    public Double setWd(Record newRecord){
        Record latestRecord = this.get(newRecord.prevRecordId);
        return ((newRecord.cold - latestRecord.cold) + (newRecord.hot - latestRecord.hot));
    }

    // Метод для получения самой свежей записи по дате
    public Record getLatestRecord() {
        return recordRepository.findLatestRecordByDate();
    }

    public LocalDate setDateRecord(){
        return LocalDate.now();
    }

    // высчитывание конечной суммы
    public Double setTotal(Record newRecord){
        Record latestRecord = this.get(newRecord.prevRecordId);

        Double t1Result = (newRecord.t1 - latestRecord.t1) * t1coefficient;
        Double t2Result = (newRecord.t2 - latestRecord.t2) * t2coefficient;
        Double t3Result = (newRecord.t3 - latestRecord.t3) * t3coefficient;
        Double hotResult = (newRecord.hot - latestRecord.hot) * hotCoefficient;
        Double coldResult = (newRecord.cold - latestRecord.cold) * coldCoefficient;
        Double wdResult = newRecord.wd * wdCoefficient;

        return t1Result + t2Result + t3Result + hotResult + coldResult + wdResult;
    }
}
