package com.epam.web.entity;

import java.io.Serializable;

/**
 * The Identifiable interface. All entities must implement it.
 */
public interface Identifiable extends Serializable {

    Long getId();
}
