package oort.cloud.mapper.slave;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SlaveDao {
    String getDataBase();
}
