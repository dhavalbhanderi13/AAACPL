package com.aaacpl.dto.lots;

public class CreateLotResponseDTO {
    private int id;

    public CreateLotResponseDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CreateLotResponseDTO{" +
                "id=" + id +
                '}';
    }

}
