package com.zybooks.lab3;

import java.util.Objects;

public class MadLibEntry {
    private String clue;
    private String answer;
    private int startIndex;
    private int endIndex;

    public MadLibEntry(int start, int end, String cluestr) {
        startIndex = start;
        endIndex = end;
        clue = cluestr;
        answer = "";
    }

    public void setClue(String cluestr) {
        clue = cluestr;
    }

    public void setAnswer(String ansstr) {
        answer = ansstr;
    }

    public void setStartIndex(int index) {
        startIndex = index;
    }

    public void setEndIndex(int index) {
        endIndex = index;
    }

    public String getClue() {
        return clue;
    }

    public String getAnswer() {
        return answer;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public String toString() {
        String entryStr = "(" + String.valueOf(startIndex);
        entryStr += "," + String.valueOf(endIndex);
        entryStr += "," + clue + ")";
        return entryStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MadLibEntry that = (MadLibEntry) o;
        return getStartIndex() == that.getStartIndex() && getEndIndex() == that.getEndIndex() && Objects.equals(getClue(), that.getClue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClue(), getStartIndex(), getEndIndex());
    }
}

