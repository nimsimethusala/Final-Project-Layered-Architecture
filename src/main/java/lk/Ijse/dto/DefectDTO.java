package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefectDTO {
    private String defectId;
    private String description;
    private double price;
    private String spareId;
}
