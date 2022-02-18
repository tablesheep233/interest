package org.table.aop.expose.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Async log.
 *
 * @author: ZhuoYang
 */
@Data
@Entity(name = "log")
@Table
public class Log {

    @Id
    private Long id;

    @Column(length = 100)
    private String info;
}
