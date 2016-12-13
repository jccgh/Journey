package jcc.journey.module.Been;

/**
 * Created by Administrator on 2016/11/20.
 */

public class ProvinceBeen {
    private String id;
    private String name;

    private ProvinceBeen(){}
    public ProvinceBeen(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
