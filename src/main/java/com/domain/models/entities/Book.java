package com.domain.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "tbl_books")
@SQLDelete(sql = "UPDATE tbl_books SET deleted = TRUE WHERE id = ?")
// @Where(clause = "deleted=false")
@FilterDef(name = "deletedBookFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedBookFilter", condition = "deleted = :isDeleted")
public class Book {

    @Id
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(length = 12)
    private double price;

    private boolean deleted = Boolean.FALSE; // FALSE == not deleted, TRUE == deleted

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
