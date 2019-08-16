package com.allqj.virtual_number_administrate.business.repository.elasticsearch;

import com.allqj.virtual_number_administrate.business.repository.elasticsearch.entity.VirtualNumberEsEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface IVirtualNumberEsRepository extends ElasticsearchRepository<VirtualNumberEsEntity, Integer> {
    VirtualNumberEsEntity findByVirtualNumberAndUtypeAndIsdeleteFalse(String virtualNumber, Integer virtualNumberType);

    List<VirtualNumberEsEntity> findAllByIsdeleteIsFalse();
}
