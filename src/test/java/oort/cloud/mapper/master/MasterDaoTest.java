package oort.cloud.mapper.master;

import oort.cloud.conf.MasterDataBaseConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



class MasterDaoTest {

    @Test
    void test(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MasterDataBaseConfig.class);
    }
}