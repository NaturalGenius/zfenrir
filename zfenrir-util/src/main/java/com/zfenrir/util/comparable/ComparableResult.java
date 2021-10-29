package com.zfenrir.util.comparable;

import java.util.List;

public class ComparableResult {

    private boolean equals;

    List<ComparableFiledDetail> filedDetails;

    public ComparableResult() {
    }
    
    public ComparableResult(boolean equals) {
        this.equals = equals;
    }
    public boolean isEquals() {
        return equals;
    }

    public void setEquals(boolean equals) {
        this.equals = equals;
    }

    public List<ComparableFiledDetail> getFiledDetails() {
        return filedDetails;
    }

    public void setFiledDetails(List<ComparableFiledDetail> filedDetails) {
        this.filedDetails = filedDetails;
    }

    @Override
    public String toString() {
        if (equals) {
            return super.toString();
        }
        StringBuilder builder = new StringBuilder();
        for (ComparableFiledDetail detail : filedDetails) {
            builder.append(detail.fieldName).append(":").append(detail.getOldFiledVale()).append("-->")
                .append(detail.getNewFiledVale()).append(";");
        }
        return builder.toString();
    }

    public static class ComparableFiledDetail {
        private String fieldName;
        private Object oldFiledVale;
        private Object newFiledVale;

        public ComparableFiledDetail() {}

        public ComparableFiledDetail(String fieldName, Object oldFiledVale, Object newFiledVale) {
            this.fieldName = fieldName;
            this.oldFiledVale = oldFiledVale;
            this.newFiledVale = newFiledVale;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Object getOldFiledVale() {
            return oldFiledVale;
        }

        public void setOldFiledVale(Object oldFiledVale) {
            this.oldFiledVale = oldFiledVale;
        }

        public Object getNewFiledVale() {
            return newFiledVale;
        }

        public void setNewFiledVale(Object newFiledVale) {
            this.newFiledVale = newFiledVale;
        }
    }
}
