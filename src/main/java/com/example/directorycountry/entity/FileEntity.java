package com.example.directorycountry.entity;

import com.example.directorycountry.enums.FileType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileEntity extends BaseEntity{
    @Column(name = "name")
    String name;

    @Column(name = "url")
    String url;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    FileType fileType;

}
