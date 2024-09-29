package org.example.Enums;

public enum CellStatus {
    ALIVE("*"), DEAD("-");
    public final String symbol;
    private CellStatus(String symbol){
        this.symbol = symbol;
    }

}
