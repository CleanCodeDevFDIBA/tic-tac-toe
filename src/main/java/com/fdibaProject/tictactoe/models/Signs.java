package com.fdibaProject.tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Signs {
    X(1), O(2);

    private Integer value;
}
