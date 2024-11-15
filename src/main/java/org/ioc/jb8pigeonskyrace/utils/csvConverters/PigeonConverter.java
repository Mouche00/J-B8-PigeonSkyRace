package org.ioc.jb8pigeonskyrace.utils.csvConverters;

import com.opencsv.bean.AbstractBeanField;
import org.ioc.jb8pigeonskyrace.dtos.PigeonDTO;
import org.ioc.jb8pigeonskyrace.models.Pigeon;
import org.ioc.jb8pigeonskyrace.services.PigeonService;
import org.ioc.jb8pigeonskyrace.utils.enums.Gender;
import org.ioc.jb8pigeonskyrace.utils.mappers.dtos.PigeonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class PigeonConverter extends AbstractBeanField<Pigeon, String> {


    @Override
    protected Pigeon convert(String value) {

        Pigeon fakePigeon = new Pigeon();
        fakePigeon.setId(value);
        fakePigeon.setBandNumber("F" + Math.random() * 10000);
        fakePigeon.setGender(Gender.MALE);
        fakePigeon.setBirthYear("2020");
        fakePigeon.setColor("Gray");

        return fakePigeon;
    }
}
