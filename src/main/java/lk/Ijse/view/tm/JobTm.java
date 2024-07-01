package lk.Ijse.view.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class JobTm {
    private Date date;
    private String vehicleModel;
    private String customerName;
    private String itemName;
    private int itemCount;
    private String spareName;
    private int spareCount;
    private double empCost;
    private String defectDescription;
    private JFXButton btnAction;
}
