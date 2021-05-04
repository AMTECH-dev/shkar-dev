package com.company.with_spring.annotation.virus;

import com.company.with_spring.annotation.ColdQualifier;
import com.company.with_spring.models.virus.VirusType;
import org.springframework.stereotype.Component;

@Component
@ColdQualifier
public class Cold implements Virus {
    @Override
    public VirusType getVirusType() {
        return VirusType.COLD;
    }
}
