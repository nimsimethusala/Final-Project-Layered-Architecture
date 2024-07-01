package lk.Ijse.dto;

import lk.Ijse.entity.Job;
import lk.Ijse.entity.JobDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlaceJobDTO {
    public Job job;
    public List<JobDetailDTO> jobList;
}
