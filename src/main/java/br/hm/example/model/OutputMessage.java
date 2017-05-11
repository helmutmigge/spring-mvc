package br.hm.example.model;

/**
 * Created by helmut.guimaraes on 25/04/2017.
 */
public class OutputMessage {
    private String from;
    private String text;
    private String time;

    public OutputMessage(final String from, final String text, final String time) {

        this.from = from;
        this.text = text;
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

}
