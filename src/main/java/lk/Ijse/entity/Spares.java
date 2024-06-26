package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Spares {
    private String spareId;
    private String name;
    private int count;
    private double price;
    private String supplierId;
}
