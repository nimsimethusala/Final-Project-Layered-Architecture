package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item {
    private String itemID;
    private String itemName;
    private int itemCount;
    private String defectId;
}
