package com.pru.aswit.util.mapper;

import ma.glasnost.orika.impl.DefaultMapperFactory;

public interface OrikaMapperFactoryBuilderConfigurer {
    void configure(DefaultMapperFactory factory);
}
