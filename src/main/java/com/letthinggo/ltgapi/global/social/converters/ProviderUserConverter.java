package com.letthinggo.ltgapi.global.social.converters;

public interface ProviderUserConverter<T,R> {
    R convert(T t);
}
