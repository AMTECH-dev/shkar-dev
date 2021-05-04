package com.company.with_spring.annotation.virus;

import com.company.with_spring.models.virus.VirusType;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Plague implements Virus {
    @Override
    public VirusType getVirusType() {
        return VirusType.PLAGUE;
    }
}
