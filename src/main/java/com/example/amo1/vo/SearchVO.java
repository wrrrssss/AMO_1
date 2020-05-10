package com.example.amo1.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchVO {

    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
