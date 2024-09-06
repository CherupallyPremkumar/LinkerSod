package com.sod.doc.contentreader.service.cmds;

import com.sod.doc.contentreader.model.Contentreader;
import org.chenile.stm.action.STMAutomaticStateComputation;

import java.util.Date;

public class ValidateUrlLifeSpan implements STMAutomaticStateComputation<Contentreader> {
    @Override
    public String execute(Contentreader contentreader) throws Exception {
        if (contentreader.getTimePeriod().before(new Date())) {
            return "yes";
        }
        return "no";
    }
}
