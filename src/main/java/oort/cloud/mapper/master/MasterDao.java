package oort.cloud.mapper.master;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterDao {
    String getDataBase();
}
