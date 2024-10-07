package org.example.Enums;

public enum CellStatus {
    ALIVE("*"),
    DEAD("-");

    private final String symbol;

    CellStatus(String stateString) {
        this.symbol = stateString;
    }

    public String getSymbol() {
        return symbol;
    }
}