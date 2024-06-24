package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Defect {
    private String defectId;
    private String description;
    private double price;
    private String spareId;
}
