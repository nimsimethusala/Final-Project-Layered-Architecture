package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SparesDTO {
    private String spareId;
    private String name;
    private int count;
    private double price;
    private String supplierId;
}
