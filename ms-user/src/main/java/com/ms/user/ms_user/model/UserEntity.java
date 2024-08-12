package com.ms.user.ms_user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data // to add the getter and setter implicit
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "user")

public class UserEntity {
    @Id
    private String id;
    private String document;
    private String name;
    private String email;
    private String information;
}
