package com.example.directorycountry.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files_countries")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileCountryEntity extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "file_id")
    FileEntity fileEntity;

    @OneToOne
    @JoinColumn(name = "country_id")
    CountryEntity countryEntity;
}
