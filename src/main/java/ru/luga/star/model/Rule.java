package ru.luga.star.model;

import jakarta.persistence.*;
import ru.luga.star.model.dto.rule.RuleDto;

import java.util.ArrayList;
import java.util.List;

/**
 * БД: Описание динамического правила
 */
@Entity
@Table(name = "rules")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product; // Продукт, для которого применяется правило

    @OneToMany(mappedBy = "rule", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position")
    private List<Argument> arguments;   // аргументы правила

    @Column(name = "query")
    private String query;   // тип запроса

    @Column(name = "negate")
    private boolean negate = false; // отрицание

    public Rule() {
        arguments = new ArrayList<>();
    }

    public Rule(RuleDto ruleDto) {
        query = ruleDto.getQuery();
        arguments = new ArrayList<>();
        negate = ruleDto.isNegate();

        ruleDto.getArguments().forEach(argumentDto -> {
            Argument argument = new Argument();
            argument.setValue(argumentDto);
            addArgument(argument);
        });

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isNegate() {
        return negate;
    }

    public void setNegate(boolean negate) {
        this.negate = negate;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public void addArgument(Argument argument) {
        argument.setRule(this);
        argument.setPosition(arguments.size());
        arguments.add(argument);
    }
}
