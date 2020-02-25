package cn.vector.mybatis.mapper;

import cn.vector.mybatis.entity.Tag;

public interface TagMapper {
    /**
     * 通过主键查找
     *
     * @param id 主键ID
     * @return
     */
    Tag selectByPrimaryKey(Integer id);
}
