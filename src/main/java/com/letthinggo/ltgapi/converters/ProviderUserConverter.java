package com.letthinggo.ltgapi.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
