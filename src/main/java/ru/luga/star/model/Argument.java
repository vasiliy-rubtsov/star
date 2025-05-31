package ru.luga.star.model;

import jakarta.persistence.*;

/**
 * БД: Описание аргумента правила
 */
@Entity
@Table(name = "arguments")
public class Argument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_id")
    private Rule rule;  // Правило, к которому относится аргумент

    @Column(name = "position")
    private Integer position = 0;   // Порядковый номер в описании правила (от этого зависит назначение аргумента)

    @Column(name = "value")
    private String value; // значение

    public Argument() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }
}
