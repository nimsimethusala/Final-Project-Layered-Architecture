package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobDetailDTO {
    private String itemId;
    private int itemCount;
    private String model;
    private String jobId;
    private int spareCount;
}
