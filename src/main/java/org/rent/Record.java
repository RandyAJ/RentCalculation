package org.rent;

import java.time.LocalDate;

public class Record {
    Record(
            Double t1, Double t2, Double t3,
            Double hot, Double cold
    ){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;

        this.hot = hot;
        this.cold = cold;
        this.wd = setWd(hot, cold);

        this.prevRecord = setPrevRecord();
        this.dateRecord = setDateRecord();
        this.total = setTotal();
    }

    Double t1;
    Double t2;
    Double t3;

    Double hot;
    Double cold;
    Double wd; // water drainage (водоотвод)

    private Record prevRecord;
    private LocalDate dateRecord;
    private Double total;

    private Double setWd(Double hot, Double cold){
        return hot; // TODO
    }

    private Record setPrevRecord(){
        //return new Record(1.0, 1.0, 1.0,1.0,1.0); // прикольный кейс. тут будет бесконечный вызов конструктора и java.lang.StackOverflowError
        return null; // TODO
    }

    private LocalDate setDateRecord(){
        return LocalDate.now(); // TODO
    }

    private Double setTotal(){
        return 52.0; // TODO
    }

    @Override
    public String toString() {
        return "Запись от " + this.dateRecord + ". ТоталОчка: " + this.total;
    }
}
