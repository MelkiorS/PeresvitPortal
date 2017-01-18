package ua.peresvit.converter;

import ua.peresvit.entity.Mark;
import ua.peresvit.service.MarkService;
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
