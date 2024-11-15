package org.ioc.jb8pigeonskyrace.utils.csvConverters;

import com.opencsv.bean.AbstractBeanField;
import org.ioc.jb8pigeonskyrace.models.Race;

public class RaceConverter extends AbstractBeanField<Race, String> {
    @Override
    protected Race convert(String value) {

        Race race = new Race();
        race.setId(value);
        return race;
    }
}
