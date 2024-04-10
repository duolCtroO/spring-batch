package oort.cloud;

import oort.cloud.mapper.master.MasterDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BatchApplicationTests {
    @Autowired
    private MasterDao dao;

    @Test
    void contextLoads() {
        String dataBases = dao.getDataBase();
        System.out.println(dataBases);
    }

}
