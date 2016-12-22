package org.bionic.converter;

import org.bionic.entity.Mark;
import org.bionic.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToMarkConverter implements Converter<String, Mark>{

    @Autowired
    MarkService markService;

    @Override
    public Mark convert(String source) {

        Long id = Long.parseLong((String)source);
        Mark mark = markService.findOne(id);

        return mark;
    }
}
