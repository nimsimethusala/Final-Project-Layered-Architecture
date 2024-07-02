package lk.Ijse.view.tm;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
//@AllArgsConstructor
@Data
public class PaymentTm {
    private String paymentId;
    private String jobId;
    private double defectTotal;
    private double employeeTotal;
    private double spareTotal;
    private double total;

    public PaymentTm(String paymentId, String jobId, double defectTotal, double employeeTotal, double spareTotal, double total) {
        this.paymentId = paymentId;
        this.jobId = jobId;
        this.defectTotal = defectTotal;
        this.employeeTotal = employeeTotal;
        this.spareTotal = spareTotal;
        this.total = total;
    }
}
