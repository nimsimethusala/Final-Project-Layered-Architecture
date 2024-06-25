package lk.Ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {
    private String empId;
    private String name;
    private String attendance;
    private int contact;
    private String address;
    private double salary;
    private double cost;
}
