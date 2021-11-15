package com.zybooks.lab3;

import java.util.HashMap;
import java.util.Map;

public class MadLib {
    private String text;
    private Map<Integer, MadLibEntry> entries = new HashMap<>();;

    /* constructor
        Precondition: madlib_text is a string containing the text of
                      the madlib to be filled in with answers from
                      the user.  Entries to be filled in are indicated
                      by [clue_string].

                      For example, there is one entry in the following
                      short madlib:
                        "My favorite song is [song]."
                      The entry is [song], where "song" is what you want
                      to ask the user to enter.

                      See TestMadLib.java for an example of how to add
                      answers to entries in the MadLib object.

                      Once all entries have answers, use genCompleteMadLib()
                      to get a string of the madlib with answers filled in.
     */
    public MadLib(String madlib_text) {
        text = madlib_text;
        getCluesFromText();
    }

    public void getCluesFromText() {
        int entryCount = 0;
        int curIndex = 0;
        int startIndex;
        while (text.substring(curIndex).contains("[")) {
            startIndex = text.indexOf("[", curIndex) + 1;
            entries.put(entryCount, getNextEntry(startIndex));
            curIndex = entries.get(entryCount).getEndIndex() + 1;
            entryCount++;
        }
    }

    private MadLibEntry getNextEntry(int startIndex) {
        int endIndex = text.indexOf("]", startIndex);
        String cluestr = text.substring(startIndex, endIndex);
        return new MadLibEntry(startIndex, endIndex, cluestr);
    }

    public int getNumEntries() {
        return entries.size();
    }

    public MadLibEntry getEntry(Integer key) {
        return entries.get(key);
    }

    public void addAnswerToEntry(Integer key, String answer) {
        entries.get(key).setAnswer(answer);
    }

    public String genCompleteMadLib() {
        String madlib = "";
        int lastIndex = text.length();
        for (int i = getNumEntries() - 1; i >= 0; i--) {
            MadLibEntry entry = getEntry(i);
            madlib = entry.getAnswer() + text.substring(entry.getEndIndex()+1, lastIndex) + madlib;
            lastIndex = entry.getStartIndex() - 1;
        }
        madlib = text.substring(0,lastIndex) + madlib;
        return madlib;
    }
}

