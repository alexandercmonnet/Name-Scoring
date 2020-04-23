package org.monnet.namescoring.entity;

import lombok.Builder;
import lombok.Data;

/**
 * A class representing the name of a person.
 */
@Data
@Builder
public class Name {

    private String firstName;
    private String lastName;
}