package lk.Ijse.view.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierTm {
    private String supplierId;
    private String name;
    private int contact;
    private String location;
}
