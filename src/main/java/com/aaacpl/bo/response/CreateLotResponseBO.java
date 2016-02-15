package com.aaacpl.bo.response;

/**
 * Created by Hp on 15-02-2016.
 */
public class CreateLotResponseBO {

    private int id;

    public CreateLotResponseBO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CreateLotResponseBO{" +
                "id=" + id +
                '}';
    }
}
