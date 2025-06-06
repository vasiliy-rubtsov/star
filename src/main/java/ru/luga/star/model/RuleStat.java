package ru.luga.star.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * БД: Статитстика использования динамических
 */
@Entity
@Table(name = "rule_stats")
public class RuleStat {
    @Column(name = "id")
    @Id
    private Long id;    // id динмаического правила (->Rule)

    @Column(name = "count") // Кол-во случаев применения динамического правила
    private Long count;

    public RuleStat() {
        this.count = 0L;
    }

    public RuleStat(Long id, Long count) {
        this.id = id;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
