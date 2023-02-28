package com.example.demo.dto.contextholder;

import com.example.demo.dto.Dto;
import lombok.Builder;
import org.joda.time.DateTimeZone;

@Builder
public class UserContextHolder implements Dto {
    DateTimeZone timeZone;
}
