/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrent;

import java.util.Date;

/**
 *
 * @author george
 */
public class Message {
    private String from;
    private String to;
    private String text;
    private Date date;
    private Status status;
    
    public Message() {}
    
    public Message(String from, String to, String text, Date date, Status status) {
        this.from = from;
        this.to = to;
        this.text = text;
        this.date = date;
        this.status = status;
    }
    
    public void setFrom(String from) {
        this.from = from;
    }
    
    public void setTo(String to) {
        this.to = to;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
    
    public String getFrom() {
        return from;
    }
    
    public String getTo() {
        return to;
    }
    
    public String getText() {
        return text;
    }
    
    public Date getDate() {
        return date;
    }
    
    public Status getStatus() {
        return status;
    }
}
