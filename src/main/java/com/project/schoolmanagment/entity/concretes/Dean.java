package com.project.schoolmanagment.entity.concretes;

import com.project.schoolmanagment.entity.abstracts.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor

public class Dean extends User {
}