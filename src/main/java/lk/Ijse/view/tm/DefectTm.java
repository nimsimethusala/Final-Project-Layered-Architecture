package lk.Ijse.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefectTm {
    private String defectId;
    private String description;
    private double price;
}
