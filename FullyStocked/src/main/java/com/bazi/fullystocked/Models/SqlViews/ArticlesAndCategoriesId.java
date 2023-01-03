package com.bazi.fullystocked.Models.SqlViews;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class ArticlesAndCategoriesId implements Serializable {
    private Integer categoryid;
    private Integer articleid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlesAndCategoriesId that = (ArticlesAndCategoriesId) o;
        return categoryid.equals(that.categoryid) && articleid.equals(that.articleid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryid, articleid);
    }
}
