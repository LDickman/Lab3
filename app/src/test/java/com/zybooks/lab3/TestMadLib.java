package com.zybooks.lab3;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMadLib {
    @Test
    public void getCluesFromText_isCorrect() {
        String teststring = "Learning to drive is a tricky process. There are a few rules you must follow.\n" +
                "\n" +
                "1. Keep two [plural noun] on the steering wheel at all times.\n" +
                "\n" +
                "2. Step on the [noun] to speed up and the [noun] to slow down.\n" +
                "\n" +
                "3. Your parents will just LOVE if you play [song] on the radio.\n" +
                "\n" +
                "4. Make sure to honk your horn when you see [verb] on a street sign.\n";

        MadLib madlib = new MadLib(teststring);
        int correctNumEntries = 5;
        assertEquals(correctNumEntries, madlib.getNumEntries());

        MadLibEntry clue1 = new MadLibEntry(92,103, "plural noun");
        assertEquals(clue1, madlib.getEntry(0));

        MadLibEntry clue2 = new MadLibEntry(158,162, "noun");
        assertEquals(clue2, madlib.getEntry(1));

        MadLibEntry clue3 = new MadLibEntry(185,189, "noun");
        assertEquals(clue3, madlib.getEntry(2));

        MadLibEntry clue4 = new MadLibEntry(250,254, "song");
        assertEquals(clue4, madlib.getEntry(3));

        MadLibEntry clue5 = new MadLibEntry(316,320, "verb");
        assertEquals(clue5, madlib.getEntry(4));
    }

    @Test
    public void addAnswerToEntry_isCorrect() {
        String teststring = "1. Keep two [plural noun] on the steering wheel at all times.\n";
        MadLib madlib = new MadLib(teststring);
        madlib.addAnswerToEntry(0, "slippers");
        MadLibEntry clue1 = new MadLibEntry(13,24, "plural noun");
        clue1.setAnswer("slippers");
        assertEquals(clue1, madlib.getEntry(0));
    }

    @Test
    public void getCompleteMadLib_isCorrect() {
        String teststring = "Learning to drive is a tricky process. There are a few rules you must follow.\n" +
                "\n" +
                "1. Keep two [plural noun] on the steering wheel at all times.\n" +
                "\n" +
                "2. Step on the [noun] to speed up and the [noun] to slow down.\n" +
                "\n" +
                "3. Your parents will just LOVE if you play [song] on the radio.\n" +
                "\n" +
                "4. Make sure to honk your horn when you see [verb] on a street sign.\n";

        MadLib madlib = new MadLib(teststring);
        madlib.addAnswerToEntry(0,"slippers");
        madlib.addAnswerToEntry(1,"cat");
        madlib.addAnswerToEntry(2,"dog");
        madlib.addAnswerToEntry(3,"Over the Rainbow");
        madlib.addAnswerToEntry(4,"dart");

        String finalMadLib = "Learning to drive is a tricky process. There are a few rules you must follow.\n" +
                "\n" +
                "1. Keep two slippers on the steering wheel at all times.\n" +
                "\n" +
                "2. Step on the cat to speed up and the dog to slow down.\n" +
                "\n" +
                "3. Your parents will just LOVE if you play Over the Rainbow on the radio.\n" +
                "\n" +
                "4. Make sure to honk your horn when you see dart on a street sign.\n";

        assertEquals(finalMadLib, madlib.genCompleteMadLib());
    }
}

