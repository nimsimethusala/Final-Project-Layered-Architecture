package lk.Ijse.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private String paymentId;
    private String jobId;
    private double defectTotal;
    private double empTotal;
    private double spareTotal;
    private double totalCost;

    public PaymentDTO(String paymentId, String jobId, double defectTotal, double empTotal, double spareTotal, double totalCost) {
        this.paymentId = paymentId;
        this.jobId = jobId;
        this.defectTotal = defectTotal;
        this.empTotal = empTotal;
        this.spareTotal = spareTotal;
        this.totalCost = totalCost;
    }
}
