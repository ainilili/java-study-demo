package org.nico.design.mode.filter;

import java.util.List;

public abstract class AbstractFilter<T> {
    public abstract void filter(List<T> list);
}
