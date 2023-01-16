package com.bazi.fullystocked.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleAnalysisDTO {
    String articlename;
    String locationname;
    String defict;
}
