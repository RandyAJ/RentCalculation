package org.rent;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity // говорит о том что класс является сущностью (Entity) для базы данных (Entity(сущность) - объект бизнес логики, обычно сущность представляет собой таблицу в реляционной базе данных, и каждый экземпляр сущности соответствует строке в этой таблице)
@Table(name = "records") // указывает имя таблицы, соответствующей этой сущности
public class Record {

    public Record() {}

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
    }

    @Id
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
    Double wd;

    @Column(name = "prevRecordId", nullable = false)
    Long prevRecordId; // Если поле будет типа Record, то есть риск StackOverflow, т. к. при подгрузке объектов из БД будут здоровые портянки
    @Column(name = "dateRecord", nullable = false)
    LocalDate dateRecord;
    @Column(name = "total", nullable = false)
    Double total;

    public Long getId(){
        return this.id;
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
