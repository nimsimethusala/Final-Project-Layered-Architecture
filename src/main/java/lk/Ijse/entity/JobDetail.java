package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobDetail {
    private String itemId;
    private int itemCount;
    private String model;
    private String jobId;
    private int spareCount;
}
