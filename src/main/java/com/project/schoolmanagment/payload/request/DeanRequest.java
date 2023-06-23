package com.project.schoolmanagment.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Negative;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class DeanRequest extends  BaseUserRequest{
}
