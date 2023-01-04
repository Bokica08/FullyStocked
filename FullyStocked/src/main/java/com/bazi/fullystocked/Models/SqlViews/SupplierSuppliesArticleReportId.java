package com.bazi.fullystocked.Models.SqlViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class SupplierSuppliesArticleReportId implements Serializable {
    private Integer articleid;
    private Integer userid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierSuppliesArticleReportId that = (SupplierSuppliesArticleReportId) o;
        return articleid.equals(that.articleid) && userid.equals(that.userid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleid, userid);
    }
}
