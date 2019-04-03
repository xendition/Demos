package com.michael.demo.spring.cache.caffeine.base.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michael
 * @date 2018/6/4.
 */
public abstract class BaseIdModel<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    protected static Logger logger = LoggerFactory.getLogger(BaseIdModel.class);


    @Id
    @Column(
            name = "id"
    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    protected T id;

    public BaseIdModel() {
    }

    public T getId() {
        return this.id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public String toString() {
        try {
            return (new ObjectMapper()).writeValueAsString(this);
        } catch (Exception var2) {
            logger.error("Entity 解析为 JSON 串出错..." + this, var2);
            return "";
        }
    }
}

