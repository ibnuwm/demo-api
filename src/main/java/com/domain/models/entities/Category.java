package com.domain.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Category
 */
@Entity
@Table(name = "tbl_categories")
// @Getter
// @Setter
// @NoArgsConstructor
public @Data class Category extends BaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    // @Builder
    // public Category(Long id, String name) {
    // this.id = id;
    // this.name = name;
    // }

}