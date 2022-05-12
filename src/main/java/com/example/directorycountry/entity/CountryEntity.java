package com.example.directorycountry.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "countries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryEntity extends BaseEntity{
    @Column(name = "country_name",nullable = false,unique = true)
    String name;

    @Column(name = "alpha_code",nullable = false,unique = true)
    String alphaCode;

    @Column(name = "is_active",nullable = false)
    Boolean isActive;
}
