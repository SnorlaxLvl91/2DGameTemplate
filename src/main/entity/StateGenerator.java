package main.entity;

import main.state.entity.EntityState;

public interface StateGenerator {

    public EntityState generate();
}
