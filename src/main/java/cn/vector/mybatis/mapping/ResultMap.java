package cn.vector.mybatis.mapping;

import java.util.List;

/**
 * @author 勤恳的小码农
 * @date 2020/2/24 15:53
 */
public class ResultMap {
    private String id;
    private String type;
    private List<ResultMapping> columns;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ResultMapping> getColumns() {
        return columns;
    }

    public void setColumns(List<ResultMapping> columns) {
        this.columns = columns;
    }
}
