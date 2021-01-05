package com.ibar.redisapp.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "testUsers")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "PersonSequence")
    @JsonProperty
    @Column(name = "id")
    @Id
    private long id;

    @JsonProperty
    @Column(name = "name")
    private String name;

    @JsonProperty
    @Column(name = "surname")
    private String surname;

    @JsonProperty
    @Column(name = "age")
    private int age;

    @JsonProperty
    @Column(name = "password")
    private String password;
}
