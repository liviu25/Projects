package utils;


import Domain.Nota;

/**
 * Created by grigo on 11/16/16.
 */
public class NotaEvent implements Event {
    private STEType type;
    private Nota data, oldData;

    public NotaEvent(STEType type, Nota data) {
        this.type = type;
        this.data = data;
    }
    public NotaEvent(STEType type, Nota data, Nota oldData) {
        this.type = type;
        this.data = data;
        this.oldData=oldData;
    }

    public STEType getType() {
        return type;
    }

    public Nota getData() {
        return data;
    }

    public Nota getOldData() {
        return oldData;
    }
}
