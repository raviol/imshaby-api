package by.imsha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@ApiModel
public class LocalizedBaseInfo {
    @JsonIgnore
    private String lang;
    @JsonIgnore
    private String originObjId;

    public LocalizedBaseInfo() {
    }

    public LocalizedBaseInfo(String lang, String originObjId) {
        this.lang = lang;
        this.originObjId = originObjId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getOriginObjId() {
        return originObjId;
    }

    public void setOriginObjId(String originObjId) {
        this.originObjId = originObjId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LocalizedBaseInfo that = (LocalizedBaseInfo) o;

        return new EqualsBuilder()
                .append(getLang(), that.getLang())
                .append(getOriginObjId(), that.getOriginObjId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getLang())
                .append(getOriginObjId())
                .toHashCode();
    }
}
