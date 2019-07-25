package domain;

import java.time.LocalDateTime;

public class MessageTask extends Task {
    private String mesaj,from,to;
    private LocalDateTime date;

    public MessageTask(String taskID, String descriere, String mesaj, String from, String to) {
        super(taskID, descriere);
        this.mesaj = mesaj;
        this.from = from;
        this.to = to;
        this.date = LocalDateTime.now();
    }
    @Override
    public void execute() {
        System.out.println(mesaj+" "+date);
    }

    @Override
    public String toString() {
        return super.toString()+" "+mesaj+" "+from+" "+to+" "+date;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MessageTask) {
            MessageTask m=(MessageTask)obj;
            return super.getTaskID().equals(m.getTaskID());
        }
        return false;
    }
}
