package pl.aswit.rest.config.mapper;

import pl.aswit.util.mapper.OrikaMapperFactoryBuilderConfigurer;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestMappingConfiguration implements OrikaMapperFactoryBuilderConfigurer {
    @Override
    public void configure(DefaultMapperFactory factory) {
        // nothing to configure for now (1 to 1 mapping)
    }
}
