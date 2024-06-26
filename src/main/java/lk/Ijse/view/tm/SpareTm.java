package lk.Ijse.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpareTm {
    private String spareId;
    private String name;
    private int count;
    private double price;
}
