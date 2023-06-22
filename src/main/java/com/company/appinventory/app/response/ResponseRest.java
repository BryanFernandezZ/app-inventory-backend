package com.company.appinventory.app.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseRest {

    private List<Map<String, String>> metadata = new ArrayList<>();

    public List<Map<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String type, String code, LocalDateTime date, String message) {
        Map<String, String> map = new HashMap<>();

        map.put("type", type);
        map.put("code", code);
        map.put("date", date.toString());
        map.put("message", message);

        metadata.add(map);
    }
}
