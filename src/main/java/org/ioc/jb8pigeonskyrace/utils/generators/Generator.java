package org.ioc.jb8pigeonskyrace.utils.generators;

public interface Generator<T, R> {
    R generate (T ...payload);
}
