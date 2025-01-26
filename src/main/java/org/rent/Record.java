package org.rent;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // говорит о том что класс является сущностью (Entity) для базы данных (Entity(сущность) - объект бизнес логики, обычно сущность представляет собой таблицу в реляционной базе данных, и каждый экземпляр сущности соответствует строке в этой таблице)
@Table(name = "records") // указывает имя таблицы, соответствующей этой сущности
public class Record {

    // конструктор для создания записи
    public Record(
            Double t1, Double t2, Double t3,
            Double hot, Double cold
    ){
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;

        this.hot = hot;
        this.cold = cold;
        this.wd = setWd(hot, cold);

        this.prevRecordId = setPrevRecordId();
        this.dateRecord = setDateRecord();
        this.total = setTotal();
    }

    @Id // Указывает, что это поле является первичным ключом
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматическая генерация ID
    private Long id;

    @Column(name = "t1", nullable = false) // Поле будет колонкой t1 в таблице
    Double t1;
    @Column(name = "t2", nullable = false)
    Double t2;
    @Column(name = "t3", nullable = false)
    Double t3;

    @Column(name = "hot", nullable = false)
    Double hot;
    @Column(name = "cold", nullable = false)
    Double cold;
    @Column(name = "wd", nullable = false)
    Double wd; // water drainage (водоотвод)

    //@Column(name = "t2", nullable = false)
    private final Long prevRecordId; // Если Record prevRecord, то риск StackOverflow. при подгрузке объекта из БД может получаться здоровая портянка с цепочкой объектов
    @Column(name = "dateRecord", nullable = false)
    private final LocalDate dateRecord;
    private final Double total;

    private Double setWd(Double hot, Double cold){
        return hot + cold;
    }

    private Long setPrevRecordId(){
        // link db (postgresql), adding and using dependencie hibenate and HQL (something like Record.createCriteria OR SELECT FROM RECORD)
        // return new Record(1.0, 1.0, 1.0, 1.0, 1.0); // прикольный кейс хоть и очевидный. тут будет бесконечный вызов конструктора и java.lang.StackOverflowError
        return null; // TODO
    }

    private LocalDate setDateRecord(){
        return LocalDate.now(); // TODO
    }

    private Double setTotal(){
        Double t1Result = this.t1 * 8.94;
        Double t2Result = this.t2 * 3.02;
        Double t3Result = this.t3 * 6.15;

        Double hotResult = this.hot * 272.14;
        Double coldResult = this.cold * 59.80;

        Double wdResult = this.wd * 45.91;

        return t1Result + t2Result + t3Result + hotResult + coldResult + wdResult;
    }

    @Override
    public String toString() {
        return "Record(" +
                "id=" + id +
                ", dateRecord=" + dateRecord +
                ", total=" + total +
                ")";
    }
}
