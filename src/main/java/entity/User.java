package entity;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String email;
}
